package com.car_rental_cs4125.cs4125_carrental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Car{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)

    private int id;
    private String regNumber;
    private String brand;
    private String type;
    private int year;
    private String model;
    private String fuelType;
    private String transmission;
    private int mileage;

    public Car(String brand, String type, String model, String regNumber, int year, String fuelType, String transmission, int mileage) {
        this.brand = brand;
        this.type = type;
        this.model = model;
        this.regNumber = regNumber;
        this.year = year;
        this.fuelType= fuelType;
        this.transmission = transmission;
        this.mileage = mileage;
    }

    public Car(){

    }

    //Set methods
    public void setId(int id){
        this.id = id;
    }

    public void setRegNum(String regNumber){
        this.regNumber = regNumber;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public void setYear(int year){
        this.year = year;
    }

    public void setModel(String model){
        this.model = model;
    }

    public void setFuelType(String fuelType){
        this.fuelType = fuelType;
    }

    public void setTransmission(String transmission){
        this.transmission = transmission;
    }

    public void setMileage(int mileage){
        this.mileage = mileage;
    }

    //Get methods
    public int getById(){
        return this.id;
    }

    public String getRegNum(){
        return this.regNumber;
    }

    public int getYear(){
        return this.year;
    }

    public String getBrand(){
        return this.brand;
    }

    public String getModel(){
        return this.model;
    }

    public String getFuelType(){
        return this.fuelType;
    }

    public String getTransmission(){
        return this.transmission;
    }

    public int getMileage(){
        return this.mileage;
    }

}