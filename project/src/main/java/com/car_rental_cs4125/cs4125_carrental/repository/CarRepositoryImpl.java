package com.car_rental_cs4125.cs4125_carrental.repository;

import com.car_rental_cs4125.cs4125_carrental.model.Car;
import org.springframework.stereotype.Service;
import org.apache.commons.csv.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service



public class CarRepositoryImpl {
    private static final String CSV_CARFILE = "C:\\Users\\ameen\\CS4125(2)\\CS4125-Project\\project\\src\\main\\resources\\carList.csv";
    List<Car> availableCars = new ArrayList<>();

    public List<Car> getAllCars() throws IOException {
        try (Reader reader = new FileReader(CSV_CARFILE)) {
            try (CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
                for (CSVRecord record : csvParser) {
                    int id = Integer.parseInt(record.get("id"));
                    String brand = record.get("make");
                    String type = record.get("model");
                    String model = record.get("type");
                    String regNumber = record.get("regNumber");
                    int year = Integer.parseInt(record.get("year"));
                    String fuelType = record.get("fuelType");
                    String transmission = record.get("transmission");
                    int mileage = Integer.parseInt(record.get("mileage"));
                    double pricePerDay = Double.parseDouble(record.get("pricePerDay"));

                    Car car = new Car(id, brand, type, model, regNumber, year, fuelType, transmission, mileage, pricePerDay);
                    availableCars.add(car);
                }
            }
            for (Car c : availableCars) {
                System.out.println(c.toString());
            }
            return availableCars;
        }
    }

    public Car findByCarID(int carID) throws IOException {
        if ((carID >= 0)) {
            for (Car c : getAllCars()) {
                if (c.getId() == carID) {
                    System.out.println(c.toString());
                    return c;
                }
            }
        }
        return null;
    }
}



