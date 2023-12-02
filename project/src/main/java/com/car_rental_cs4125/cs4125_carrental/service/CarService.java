package com.car_rental_cs4125.cs4125_carrental.service;

import com.car_rental_cs4125.cs4125_carrental.model.Car;

import java.io.IOException;

public interface CarService {
    void addCar(Car car) throws IOException;
    void removeCar(int id) throws IOException;
    void updateCar(Car updatedCar) throws IOException;
}
