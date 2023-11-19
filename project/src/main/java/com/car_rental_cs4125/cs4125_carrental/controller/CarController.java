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

    @GetMapping("/browse")
    public String browseVehicles(Model model) throws IOException {
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        return "browse.html"; // The name of your view (e.g., browse-vehicles.html)
    }


  @PostMapping("/reservation")
    public String reserveCar(@RequestParam int carId, Model model) throws IOException {
     Car selectedCar = carService.findByCarID(carId);
     model.addAttribute("selectedCar",selectedCar);


      return "reservation";
  }
    // Add more controller methods as needed
}