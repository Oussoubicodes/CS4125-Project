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

    public static List<Car> getAvailableCars(String brand,String type, String transmission,String fuelType,int minYear,int maxYear, int minMileage, int maxMileage) throws IOException{
        List<Car> availableCars = new ArrayList<>();
        try (Reader reader = new FileReader(CSV_CARFILE)) {
            try (CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
                for (CSVRecord record : csvParser) {
                    String csvBrand = record.get("Car Brand");
                    String csvType = record.get("Car Type");
                    String model = record.get("Car Model");
                    String regNumber = record.get("RegNumber");
                    int csvYear = Integer.parseInt(record.get("Year"));
                    String csvFuelType = record.get("Fuel Type");
                    String csvTransmission = record.get("Transmission");
                    int csvMileage = Integer.parseInt(record.get("Mileage"));

                    //Apply Filters
                    if(csvBrand.equalsIgnoreCase(brand) &&
                            csvType.equalsIgnoreCase(type) &&
                            csvYear >= minYear && csvYear <= maxYear &&
                            csvFuelType.equalsIgnoreCase(fuelType) &&
                            csvTransmission.equalsIgnoreCase(transmission) &&
                            csvMileage >= minMileage && csvMileage <= maxMileage
                    ){
                        availableCars.add(new Car(csvBrand,csvType,model,regNumber,csvYear,csvFuelType,csvTransmission,csvMileage));
                    }
                }
            }
        }
        return availableCars;


    }
}