package com.car_rental_cs4125.cs4125_carrental.service;

import com.car_rental_cs4125.cs4125_carrental.model.Customer;
import com.car_rental_cs4125.cs4125_carrental.repository.CustomerRepositoryImpl;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service

public class CustomerServiceImpl {

    private static final String FILE_PATH = "project\\src\\main\\resources\\users.csv";

    private CustomerRepositoryImpl customerRepository;

    public void addCustomerToCSV(Customer customer) {
        if (customerRepository.isCustomerValid(customer)) {
            try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
                writer.append(String.format("%s,%s\n", customer.getUsername(), customer.getPassword()));
            } catch (IOException e) {
                // Handling the IOException
                e.printStackTrace();
            }
        } else {
            // in case the customer details are invalid
            throw new IllegalArgumentException("Invalid customer details provided");
        }
    }
}
