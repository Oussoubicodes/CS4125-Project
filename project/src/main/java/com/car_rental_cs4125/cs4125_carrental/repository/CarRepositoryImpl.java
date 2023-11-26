package com.car_rental_cs4125.cs4125_carrental.repository;

import com.car_rental_cs4125.cs4125_carrental.model.Car;

import org.springframework.stereotype.Service;
import org.apache.commons.csv.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarRepositoryImpl implements CarRepository {
    private static final String CSV_CARFILE = "project\\src\\main\\resources\\carList.csv";
    List<Car> availableCars = new ArrayList<>();

    @Override
    public List<Car> getAllCars() throws IOException {
        try (Reader reader = new FileReader(CSV_CARFILE)) {
            try (CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
                for (CSVRecord record : csvParser) {
                    Car car = new Car.Builder()
                            .setId(Integer.parseInt(record.get("id")))
                            .setMake(record.get("make"))
                            .setModel(record.get("model"))
                            .setType(record.get("type"))
                            .setRegNumber(record.get("regNumber"))
                            .setYear(record.get("year"))
                            .setFuelType(record.get("fuelType"))
                            .setTransmission(record.get("transmission"))
                            .setMileage(record.get("mileage"))
                            .setPricePerDay(Double.parseDouble(record.get("pricePerDay")))
                            .build();

                    availableCars.add(car);
                }
                for (Car c : availableCars) {
                    System.out.println(c.toString());
                }
                return availableCars;
            }
        }
    }

    @Override
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

    @Override
    public void addCar(Car car) throws IOException {
        List<Car> cars = getAllCars();
        cars.add(car);
        writeCarsToCSV(cars);
    }

    @Override
    public void removeCar(int id) throws IOException {
        List<Car> cars = getAllCars();
        Iterator<Car> iterator = cars.iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            if (car.getId() == id) {
                iterator.remove();
                writeCarsToCSV(cars);
                return;
            }
        }
    }

    @Override
    public void updateCar(Car updatedCar) throws IOException {
        List<Car> cars = getAllCars();
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getId() == updatedCar.getId()) {
                cars.set(i, updatedCar);
                writeCarsToCSV(cars);
                return;
            }
        }
    }

    private void writeCarsToCSV(List<Car> cars) throws IOException {
        try (Writer writer = new FileWriter(CSV_CARFILE);
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader())) {
            for (Car car : cars) {
                csvPrinter.printRecord(
                        car.getId(),
                        car.getMake(),
                        car.getModel(),
                        car.getType(),
                        car.getRegNumber(),
                        car.getFuelType(),
                        car.getTransmission(),
                        car.getMileage(),
                        car.getPricePerDay());
            }
        }
    }
}
