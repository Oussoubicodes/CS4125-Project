package com.car_rental_cs4125.cs4125_carrental.controller;

import ch.qos.logback.core.model.Model;
import com.car_rental_cs4125.cs4125_carrental.service.SearchForm;
import com.car_rental_cs4125.cs4125_carrental.service.carService;
import org.springframework.stereotype.Controller;
import com.car_rental_cs4125.cs4125_carrental.model.Car;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class CarController {
    private List<Car> availableCars;
    //private List<CarReservation> reservation; 
    public void addCar(Car car){
        availableCars.add(car);
    }

    public void removeCar(Car car){
        availableCars.remove(car);
    }

    /*public void editCar(Car car){
        Car newCar;
        this.car = newCar;
    }*/

    @GetMapping("/browse")
    public ModelAndView viewCar(Car car) {
        ModelAndView modelAndView = new ModelAndView();
        // Set the view name
        modelAndView.setViewName("carDetailsView"); // Replace "carDetailsView" with your actual view name
        // Add the 'Car' object to the model if needed
        modelAndView.addObject("car", car); // Assuming 'car' is the attribute name
        return modelAndView;
    }

    @PostMapping("/browseAvailableCars")
    public String searchCars(Model model, SearchForm searchForm) {
        carService csvReader = new carService();
        try {
            List<Car> filteredCars = csvReader.getAvailableCars(
                    searchForm.getBrand(),
                    searchForm.getType(),
                    searchForm.getTransmission(),
                    searchForm.getFuel(),
                    searchForm.getMinMileage(),
                    searchForm.getMaxMileage(),
                    searchForm.getMinYear(),
                    searchForm.getMaxYear()
            );
            model.addAttribute("cars", filteredCars);
        } catch (IOException e) {
            // Handle the exception (e.g., log it)
        }
        return "search-results"; // The name of your result page (e.g., search-results.html)
    }
}

