package com.car_rental_cs4125.cs4125_carrental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {
    @Id
    private Long id;

    private String name;
    private String username;
    private String password;
    private int phoneNumber;
    private String licenseDetails;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        if (username == null) {
            // Log a message or throw an exception to indicate the issue
            // This helps to identify if the username is unexpectedly null
            throw new NullPointerException("Username is unexpectedly null");
        }
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLicenseDetails() {
        return this.licenseDetails;
    }

    public void setLicenseDetails(String licenseDetails) {
        this.licenseDetails = licenseDetails;
    }


    public Customer() {
    }

    public Customer(String username, String password) {
    }
    
}
