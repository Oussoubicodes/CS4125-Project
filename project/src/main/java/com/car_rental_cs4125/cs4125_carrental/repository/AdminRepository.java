package com.car_rental_cs4125.cs4125_carrental.repository;

import org.springframework.stereotype.Repository;

import com.car_rental_cs4125.cs4125_carrental.model.Admin;
import com.car_rental_cs4125.cs4125_carrental.model.Car;

@Repository
public interface AdminRepository {
    Admin removeCar(Car car);
    Admin updateCar();
    
    
}
