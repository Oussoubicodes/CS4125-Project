package com.car_rental_cs4125.cs4125_carrental.model;

import jakarta.persistence.*;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carId")
    private int id;

    private String make;
    private String model;
    private String type;
    private String regNumber;
    private String year;
    private String fuelType;
    private String transmission;
    private String mileage;
    private double pricePerDay;

    //constructor to use the builder pattern
    public Car() {
        
    }

    public static class Builder {
        private Car car;

        public Builder() {
            car = new Car();

        }
        public Builder setId(int id) {
            car.id = id;
            return this;
        }

        public Builder setMake(String make) {
            car.make = make;
            return this;
        }

        public Builder setModel(String model) {
            car.model = model;
            return this;
        }

        public Builder setType(String type) {
            car.type = type;
            return this;
        }

        public Builder setRegNumber(String regNumber) {
            car.regNumber = regNumber;
            return this;
        }

        public Builder setYear(String year) {
            car.year = year;
            return this;
        }

        public Builder setFuelType(String fuelType) {
            car.fuelType = fuelType;
            return this;
        }
        
        public Builder setTransmission(String transmission) {
            car.transmission = transmission;
            return this;
        }

        public Builder setMileage(String mileage) {
            car.mileage = mileage;
            return this;
        }

        public Builder setPricePerDay(double pricePerDay) {
            car.pricePerDay = pricePerDay;
            return this;
        }
        
        public Car build() {
            return car;
        }

    }

    public int getId() {
        return this.id;
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public String getType() {
        return this.type;
    }

    public String getRegNumber() {
        return this.regNumber;
    }

    public String getYear() {
        return this.year;
    }

    public String getFuelType() {
        return this.fuelType;
    }

    public String getTransmission() {
        return this.transmission;
    }

    public String getMileage() {
        return this.mileage;
    }

    public double getPricePerDay() {
        return this.pricePerDay;
    }


    // toString method for easy printing
    @Override
    public String toString() {
        return String.format(
                "Car{id=%d, make='%s', model='%s', type='%s', regNumber='%s', year=%s, fuelType='%s', " +
                        "transmission='%s', mileage=%s, pricePerDay=%.2f}",
                id, make, model, type, regNumber, year, fuelType, transmission, mileage, pricePerDay);
    }
}