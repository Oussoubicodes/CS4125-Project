package com.car_rental_cs4125.cs4125_carrental.controller;

import com.car_rental_cs4125.cs4125_carrental.model.Car;
import com.car_rental_cs4125.cs4125_carrental.model.Reservation;
import com.car_rental_cs4125.cs4125_carrental.repository.CarRepositoryImpl;
import com.car_rental_cs4125.cs4125_carrental.repository.ReservationRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class CarController {
    private final CarRepositoryImpl carService;




    @Autowired
    public CarController(CarRepositoryImpl carRepositoryImpl) {
        this.carService = carRepositoryImpl;
    }

    @GetMapping("/search")
    public String searchCars(@RequestParam(name = "make", required = false) String make,
                             @RequestParam(name = "type", required = false) String type,
                             Model model) {
        // Get the list of cars based on the search criteria
        List<Car> cars = carService.searchCars(make, type);

        // Add the search criteria and results to the model
        model.addAttribute("make", make);
        model.addAttribute("type", type);
        model.addAttribute("cars", cars);

        // Return the view for displaying search results
        return "browse";
    }


    @GetMapping("/browse")
    public String browseVehicles(Model model) throws IOException {
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        return "browse.html"; // The name of your view (e.g., browse-vehicles.html)
    }

    // Add more controller methods as needed
}