package com.car_rental_cs4125.cs4125_carrental.repository;

import org.springframework.stereotype.Repository;

import com.car_rental_cs4125.cs4125_carrental.errors.CustomerAlreadyExistsException;
import com.car_rental_cs4125.cs4125_carrental.model.Customer;

import java.util.List;

@Repository
public interface CustomerRepository {
    List<Customer> findAll();
    Customer findByUsername(String username);
    boolean authenticate(String userrname, String password);
    void addCustomerToCSV(Customer customer);
    boolean isCustomerValid(Customer customer);

    void addObserver(CustomerRepositoryObserver observer);
    void removeObserver(CustomerRepositoryObserver observer);

    Customer createCustomer(String username, String password) throws CustomerAlreadyExistsException; 
    }


    