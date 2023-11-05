package com.car_rental_cs4125.cs4125_carrental.controller;


import com.car_rental_cs4125.cs4125_carrental.model.Car;
import com.car_rental_cs4125.cs4125_carrental.service.carService;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Controller;;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class CarController {
    private List<Car> availableCars;
    //private List<CarReservation> reservation;
    private final carService carService;
  /*
    public void addCar(Car car){
        availableCars.add(car);
    }

    public void removeCar(Car car){
        availableCars.remove(car);
    }
*/
    /*public void editCar(Car car){
        Car newCar;
        this.car = newCar;
    }*/
public CarController(carService carService){
    this.carService = carService;
}

    public List<Car> getAvailableCars() {
        return availableCars;
    }

    @GetMapping("/browse")
    public ModelAndView viewAvailableCars(Car car) throws CsvValidationException, IOException {
        availableCars = carService.getAllCars();
        ModelAndView modelAndView = new ModelAndView();
        // Set the view name
        modelAndView.setViewName("browse"); // Replace "carDetailsView" with your actual view name
        // Add the 'Car' object to the model if needed
        modelAndView.addObject("availableCars", availableCars); // Assuming 'car' is the attribute name
        return modelAndView;
    }



}

