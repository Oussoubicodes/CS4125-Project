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
    private int year;
    private String fuelType;
    private String transmission;
    private int mileage;
    private double pricePerDay;

    // Constructor
    public Car(int id, String make, String model, String type, String regNumber,
               int year, String fuelType, String transmission, int mileage, double pricePerDay) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.type = type;
        this.regNumber = regNumber;
        this.year = year;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.mileage = mileage;
        this.pricePerDay = pricePerDay;
    }

    public Car() {

    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public int getYear() {
        return year;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getTransmission() {
        return transmission;
    }

    public int getMileage() {
        return mileage;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return String.format(
                "Car{id=%d, make='%s', model='%s', type='%s', regNumber='%s', year=%d, fuelType='%s', " +
                        "transmission='%s', mileage=%d, pricePerDay=%.2f}",
                id, make, model, type, regNumber, year, fuelType, transmission, mileage, pricePerDay);
    }
}