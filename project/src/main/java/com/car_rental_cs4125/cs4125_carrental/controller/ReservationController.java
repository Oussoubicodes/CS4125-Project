package com.car_rental_cs4125.cs4125_carrental.controller;

import com.car_rental_cs4125.cs4125_carrental.model.Car;
import com.car_rental_cs4125.cs4125_carrental.model.Reservation;
import com.car_rental_cs4125.cs4125_carrental.repository.CarRepositoryImpl;
import com.car_rental_cs4125.cs4125_carrental.repository.ReservationRepositoryImpl;
import com.car_rental_cs4125.cs4125_carrental.service.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ReservationController {

    private ReservationRepositoryImpl reservationRepositoryImpl;

    private ReservationServiceImpl reservationServiceImpl;

    private CarRepositoryImpl carRepositoryImpl;

    String selectedCarAttributeName = "selectedCar";
    String reservationAttributeName = "reservation";

    @Autowired
    public ReservationController(
        ReservationRepositoryImpl reservationRepositoryImpl,
        ReservationServiceImpl reservationServiceImpl,
        CarRepositoryImpl carRepositoryImpl) {

        this.reservationRepositoryImpl = reservationRepositoryImpl;
        this.reservationServiceImpl = reservationServiceImpl;
        this.carRepositoryImpl = carRepositoryImpl;

    }


    @PostMapping("/reservation")
    public String reserveForm(@RequestParam int carId, Model model) throws IOException {
        Car selectedCar = carRepositoryImpl.findByCarID(carId);
        model.addAttribute(selectedCarAttributeName, selectedCar);

        return reservationAttributeName;
    }

    @PostMapping("/reservations")
    public String reserveCar(@ModelAttribute("reservation") Reservation reservation,
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate,
            @RequestParam("customerName") String customerName,
            @RequestParam("customerEmail") String customerEmail,
            RedirectAttributes redirectAttributes) throws IOException {

        // Check availability using startDate and endDate
        boolean isAvailable = reservationServiceImpl.checkAvailability(reservation.getCarId(), startDate, endDate);

        if (isAvailable) {
            // Create a new reservation using the repository method
            Reservation newReservation = reservationRepositoryImpl.createReservation(
                    reservation.getCarId(), startDate, endDate, customerName, customerEmail, 0.0);

            // Set the attributes for the reservation object
            newReservation.setStartDate(startDate);
            newReservation.setEndDate(endDate);
            newReservation.setCustomerName(customerName);
            newReservation.setCustomerEmail(customerEmail);

            // Retrieve the selected car
            Car selectedCar = carRepositoryImpl.findByCarID(reservation.getCarId());

            // Calculate total cost
            double pricePerDay = selectedCar.getPricePerDay();
            double totalCost = reservationServiceImpl.calculateTotalCost(startDate, endDate, pricePerDay);
            newReservation.setTotalCost(totalCost);

            // Add the new reservation
            reservationRepositoryImpl.addReservation(newReservation);

            // Set attributes for the view
            redirectAttributes.addFlashAttribute(reservationAttributeName, newReservation);
            redirectAttributes.addFlashAttribute(selectedCarAttributeName, selectedCar);

            return "redirect:/resDetails"; // Redirect to reservation details page
        } else {
            // Set availability error message
            redirectAttributes.addAttribute("availabilityError", "Vehicle is not available for the selected dates.");
            return "browse"; // Return to the reservation form page
        }
    }

    @GetMapping("/resDetails")
    public String showReservationDetails(@ModelAttribute("reservation") Reservation reservation,
            @ModelAttribute("selectedCar") Car selectedCar, Model model) {

        // Add reservation and selected car details to the model for rendering in the
        // view
        model.addAttribute(reservationAttributeName, reservation);
        model.addAttribute(selectedCarAttributeName, selectedCar);

        return "resDetails"; // Return the Thymeleaf template for reservation details
    }

    @PostMapping("/resDetails")
    public String confirmReservation(@ModelAttribute("reservation") Reservation reservation,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("successMessage", "Reservation confirmed successfully!");
        redirectAttributes.addFlashAttribute("reservation", reservation);

        return "redirect:/confirmReservation";
    }

    @GetMapping("/confirmReservation")
    public String showConfirmationPage(@ModelAttribute("reservation") Reservation reservation, Model model) {

        model.addAttribute(reservationAttributeName, reservation);

        return "confirmReservation"; // Assuming "confirmReservation" is the name of your Thymeleaf template for the
                                     // confirmation page
    }

    @GetMapping("/cancelReservation")
    public String showCancelReservationForm(Model model) {
        model.addAttribute(reservationAttributeName, new Reservation()); // Use the Reservation class
        return "cancelReservation"; // Assuming you have a Thymeleaf template named cancelReservation.html
    }

    @GetMapping("/searchReservations")
    public String searchReservations(@RequestParam(name = "reservationId", required = false) Integer reservationId,
            @RequestParam(name = "customerName", required = false) String customerName,
            Model model) throws IOException {
        // Get the list of reservations based on the search criteria
        List<Reservation> reservations = reservationRepositoryImpl.searchReservation(reservationId, customerName);

        // Add the search criteria and results to the model
        model.addAttribute("reservationId", reservationId);
        model.addAttribute("customerName", customerName);
        model.addAttribute("reservations", reservations);

        // Return the view for displaying search results
        return "cancelReservation";
    }

    @PostMapping("/cancelReservation")
    public String cancelReservation(@RequestParam("reservationId") int reservationId) throws IOException {
        reservationServiceImpl.cancelReservation(reservationId);
        return "redirect:/searchReservations"; // Redirect to the search results page
    }

}
