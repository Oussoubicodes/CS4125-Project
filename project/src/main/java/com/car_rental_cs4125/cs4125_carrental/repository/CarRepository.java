package Repository;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.car_rental_cs4125.cs4125_carrental.model.Car;

import jakarta.transaction.Transactional;


@Transactional
public class CarRepository {
    private static final String CSV_CARFILE = "C:\\Users\\oussn\\CS4125 coding\\CS4125-Project\\project\\src\\main\\resources\\carList.csv";
    private List<Car> availableCars;
    
    public List<Car> getById(int id) {
        //for loop that checks for vehicle with id that is specified
        for (Car car : availableCars) {
            if (car.getId() == id) {
                return car;
            }
        }
        return null;      
    }

    @PutMapping("/browseCars/{id}")
    public static List<Car> addCar(int id, String brand, String type, String model, String regNumber, int year, String fuelType, String transmission, int mileage){
        Car car = new Car(id, brand, type, model, regNumber, year, fuelType, transmission, mileage);
        availableCars.add(car);
    }

    @DeleteMapping("browseCars/{id}")
    public void deleteCar(Car car){
        availableCars.remove(car);
    }

    public Car updateCar(Car car){
        Car newCar = getById(id);
        if(newCar == null){
            return null;
        }
        
        newCar.setBrand(CSV_CARFILE);
        newCar.setType(CSV_CARFILE);
        newCar.setModel(CSV_CARFILE);
        newCar.setRegNum(CSV_CARFILE);
        newCar.setYear(0);
        newCar.setFuelType(CSV_CARFILE);
        newCar.setTransmission(CSV_CARFILE);
        newCar.setMileage(0);
        

    }
}
