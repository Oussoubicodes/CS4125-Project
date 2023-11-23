package com.car_rental_cs4125.cs4125_carrental.controller;

import com.car_rental_cs4125.cs4125_carrental.model.Car;
import com.car_rental_cs4125.cs4125_carrental.model.Reservation;
import com.car_rental_cs4125.cs4125_carrental.repository.CarRepositoryImpl;
import com.car_rental_cs4125.cs4125_carrental.repository.ReservationRepository;
import com.car_rental_cs4125.cs4125_carrental.repository.ReservationRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class ReservationController {

    @Autowired
    private ReservationRepositoryImpl reservationRepositoryImpl;

    @Autowired
    private CarRepositoryImpl carRepositoryImpl;

    @PostMapping("/reservation")
    public String reserveForm(@RequestParam int carId, Model model) throws IOException {
        Car selectedCar = carRepositoryImpl.findByCarID(carId);
        model.addAttribute("selectedCar", selectedCar);

        return "reservation";
    }

    @PostMapping("/reservations")
    public String reserveCar(@ModelAttribute("reservation") Reservation reservation,
                             @RequestParam("startDate") String startDate,
                             @RequestParam("endDate") String endDate,
                             RedirectAttributes redirectAttributes) throws IOException {
        // Check availability using startDate and endDate
        boolean isAvailable = reservationRepositoryImpl.checkAvailability(reservation.getCarId(), startDate, endDate);

        if (isAvailable) {
            reservation.setStartDate(startDate);
            reservation.setEndDate(endDate);

            Car selectedCar = carRepositoryImpl.findByCarID(reservation.getCarId());
            double pricePerDay = selectedCar.getPricePerDay();

            double totalCost = reservationRepositoryImpl.calculateTotalCost(startDate, endDate, pricePerDay);
            reservation.setTotalCost(totalCost);

            redirectAttributes.addFlashAttribute("reservation", reservation);
            redirectAttributes.addFlashAttribute("selectedCar", selectedCar);
            return "redirect:/resDetails";  // Redirect to reservation details page
        } else {
            // Set availability error message
            redirectAttributes.addAttribute("availabilityError", "Vehicle is not available for the selected dates.");
            return "browse";  // Return to the reservation form page
        }
    }

    @GetMapping("/resDetails")
    public String showReservationDetails(@ModelAttribute("reservation") Reservation reservation,
                                         @ModelAttribute("selectedCar") Car selectedCar,Model model) {


        // Add reservation and selected car details to the model for rendering in the view
        model.addAttribute("reservation", reservation);
        model.addAttribute("selectedCar", selectedCar);

        return "resDetails";  // Return the Thymeleaf template for reservation details
    }
}




