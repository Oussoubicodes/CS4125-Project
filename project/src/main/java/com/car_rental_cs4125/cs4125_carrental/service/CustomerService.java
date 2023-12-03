package com.car_rental_cs4125.cs4125_carrental.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.car_rental_cs4125.cs4125_carrental.model.Customer;

@Service
public interface CustomerService {
    void addCustomerToCSV(Customer customer);
    List<Customer> findAll();
    boolean authenticate(String username, String password);
    boolean isCustomerValid(Customer customer);
    
}
