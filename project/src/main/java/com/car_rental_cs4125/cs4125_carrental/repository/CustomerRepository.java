package com.car_rental_cs4125.cs4125_carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.car_rental_cs4125.cs4125_carrental.errors.CustomerAlreadyExistsException;
import com.car_rental_cs4125.cs4125_carrental.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer createCustomer(String username, String password) throws CustomerAlreadyExistsException; 
    }


    