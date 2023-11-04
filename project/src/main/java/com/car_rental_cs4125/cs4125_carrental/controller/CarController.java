package com.car_rental_cs4125.cs4125_carrental.controller;

import com.car_rental_cs4125.cs4125_carrental.service.carService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.car_rental_cs4125.cs4125_carrental.model.Car;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@RestController
public class CarController {
    //private List<Car> availableCars;
    //private List<CarReservation> reservation;
    private final carService arService;

    @Autowired
    public CarController(carService arService) {
        this.arService = arService;
    }

    /*
        public void addCar(Car car){
            availableCars.add(car);
        }

        public void removeCar(Car car){
            availableCars.remove(car);
        }

        /*public void editCar(Car car){
            Car newCar;
            this.car = newCar;
        }

       @GetMapping("/browse")
        public ModelAndView viewCar(Car car){
            ModelAndView modelAndView = new ModelAndView();
        }
    */

@GetMapping("/available-cars")
public List<Car>getAvailableCars() throws IOException{
return arService.getAllAvailableCars();
}
}

