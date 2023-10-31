package com.car_rental_cs4125.cs4125_carrental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)

private int id;
private String name;
private String username;
private String password;
private String email;
private String licenseDetails;


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicenseDetails() {
        return this.licenseDetails;
    }

    public void setLicenseDetails(String licenseDetails) {
        this.licenseDetails = licenseDetails;
    }

    
}
