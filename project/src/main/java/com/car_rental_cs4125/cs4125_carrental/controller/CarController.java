package com.car_rental_cs4125.cs4125_carrental.controller;

import org.springframework.stereotype.Controller;
import com.car_rental_cs4125.cs4125_carrental.model.Car;

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

    public void reserveCar(/*Customer customer,*/ Car car, String pickupDate, String returnDate){

    }

    public void cancelReservation(){

    }

    public List<Car> getAvailableCars() {
        return availableCars;
    }

   // public List<CarReservation> getReservations() {        return reservations;    }


}
