package com.car_rental_cs4125.cs4125_carrental.service;

import com.car_rental_cs4125.cs4125_carrental.model.Car;
import com.car_rental_cs4125.cs4125_carrental.repository.CarRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Service

public class CarServiceImpl implements CarService{

    private CarRepositoryImpl carRepositoryImpl;

    @Autowired
    public CarServiceImpl(CarRepositoryImpl carRepositoryImpl){
        this.carRepositoryImpl = carRepositoryImpl;
    }

    @Override
    public void addCar(Car car) throws IOException {
        List<Car> cars = carRepositoryImpl.getAllCars();
        cars.add(car);
        carRepositoryImpl.writeCarsToCSV(cars);
    }

    @Override
    public void removeCar(int id) throws IOException {
        List<Car> cars = carRepositoryImpl.getAllCars();
        Iterator<Car> iterator = cars.iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            if (car.getId() == id) {
                iterator.remove();
                carRepositoryImpl.writeCarsToCSV(cars);
                return;
            }
        }
    }

    @Override
    public void updateCar(Car updatedCar) throws IOException {
        List<Car> cars = carRepositoryImpl.getAllCars();
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getId() == updatedCar.getId()) {
                cars.set(i, updatedCar);
                carRepositoryImpl.writeCarsToCSV(cars);
                return;
            }
        }
    }
}
