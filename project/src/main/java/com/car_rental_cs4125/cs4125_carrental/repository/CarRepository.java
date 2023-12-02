package com.car_rental_cs4125.cs4125_carrental.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.car_rental_cs4125.cs4125_carrental.model.Car;

@Repository
public interface CarRepository {
    List<Car> getAllCars() throws IOException;
    Car findByCarID(int carId) throws IOException;
}
