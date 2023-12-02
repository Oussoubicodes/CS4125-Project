package com.car_rental_cs4125.cs4125_carrental.service;

import com.car_rental_cs4125.cs4125_carrental.model.Car;
import com.car_rental_cs4125.cs4125_carrental.repository.CarRepositoryImpl;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Service

public class CarServiceImpl implements CarService{

    public CarRepositoryImpl carRepository;

    public CarServiceImpl(CarRepositoryImpl carRepository){
        this.carRepository = carRepository;
    }

    @Override
    public void addCar(Car car) throws IOException {
        List<Car> cars = carRepository.getAllCars();
        cars.add(car);
        carRepository.writeCarsToCSV(cars);
    }

    @Override
    public void removeCar(int id) throws IOException {
        List<Car> cars = carRepository.getAllCars();
        Iterator<Car> iterator = cars.iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            if (car.getId() == id) {
                iterator.remove();
                carRepository.writeCarsToCSV(cars);
                return;
            }
        }
    }

    @Override
    public void updateCar(Car updatedCar) throws IOException {
        List<Car> cars = carRepository.getAllCars();
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getId() == updatedCar.getId()) {
                cars.set(i, updatedCar);
                carRepository.writeCarsToCSV(cars);
                return;
            }
        }
    }
}
