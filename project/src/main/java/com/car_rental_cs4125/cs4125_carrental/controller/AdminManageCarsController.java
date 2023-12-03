package com.car_rental_cs4125.cs4125_carrental.controller;

import com.car_rental_cs4125.cs4125_carrental.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.car_rental_cs4125.cs4125_carrental.model.Car;
import com.car_rental_cs4125.cs4125_carrental.repository.CarRepositoryImpl;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/manageCars")
public class AdminManageCarsController {

    @Autowired
    private CarRepositoryImpl carRepoImpl;
    @Autowired
    private CarServiceImpl carServiceImpl;

    // Constructor injection
    public AdminManageCarsController(CarRepositoryImpl carRepoImpl) {
        this.carRepoImpl = carRepoImpl;
    }

    @GetMapping
    public String getAllCars(Model model) {
        try {
            List<Car> cars = carRepoImpl.getAllCars();
            model.addAttribute("cars", cars);
            model.addAttribute("car", new Car()); // Add an empty Car object for the form
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }
        return "manageCars";
    }

    @GetMapping("/viewCar")
    public String viewCar(@RequestParam("id") int carId, Model model) {
        try {
            Car car = carRepoImpl.findByCarID(carId);
            model.addAttribute("car", car);
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }
        return "viewCar";
    }

    @PostMapping
    public String handleCarFormSubmit(@ModelAttribute("car") Car car, @RequestParam String action) {
        try {
            if ("add".equals(action)) {
                carServiceImpl.addCar(car);
            } else if ("update".equals(action)) {
                carServiceImpl.updateCar(car);
            } else if ("remove".equals(action)) {
                carServiceImpl.removeCar(car.getId());
            }
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }
        return "redirect:/manageCars";
    }

    @GetMapping("/updateCar")
    public String updateCarForm(@RequestParam("id") int carId, Model model) {
        try {
            Car car = carRepoImpl.findByCarID(carId);
            model.addAttribute("car", car);
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }
        return "updateCar";
    }

    @GetMapping("/addCar")
    public String addCarForm() {
        return "addCar";
    }

    @GetMapping("/removeCar")
    public String removeCar(@RequestParam("id") int carId, Model model) {
        try {
            carServiceImpl.removeCar(carId);
            model.addAttribute("carId", carId);
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
            // add error handling here
        }
        return "removeCar";
    }
}
