package com.car_rental_cs4125.cs4125_carrental.service;


import com.car_rental_cs4125.cs4125_carrental.model.Car;
import org.springframework.stereotype.Service;
import org.apache.commons.csv.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class carService {

    private static final String CSV_CARFILE = "C:\\Users\\ameen\\CS4125(2)\\CS4125-Project\\project\\src\\main\\resources\\carList.csv";

    public static List<Car> getAllCars() throws IOException{
        List<Car> availableCars = new ArrayList<>();
        try (Reader reader = new FileReader(CSV_CARFILE)) {
            try (CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
                for (CSVRecord record : csvParser) {
                    String brand = record.get("Car Brand");
                    String type = record.get("Car Type");
                    String model = record.get("Car Model");
                    String regNumber = record.get("RegNumber");
                    int year = Integer.parseInt(record.get("Year"));
                    String fuelType = record.get("Fuel Type");
                    String transmission = record.get("Transmission");
                    int mileage = Integer.parseInt(record.get("Mileage"));

                    Car car = new Car(brand, type, model, regNumber, year, fuelType, transmission, mileage);
                    availableCars.add(car);
                }
            }
        }
        for (Car c:availableCars) {
            System.out.println(c);
        }
        return availableCars;


            }
        }
