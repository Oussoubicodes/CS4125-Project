package com.car_rental_cs4125.cs4125_carrental.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.car_rental_cs4125.cs4125_carrental.model.Customer;
import com.car_rental_cs4125.cs4125_carrental.repository.CustomerRepository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private static final String FILE_PATH = "project\\src\\main\\resources\\users.csv";

    private List<Customer> customers = new ArrayList<>();

    public CustomerRepositoryImpl() {
        this.customers = loadUsersFromCSV(FILE_PATH);
    }

    public List<Customer> loadUsersFromCSV(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // Assuming CSV values are separated by commas

                if (data.length >= 2) { // Assuming the first element is username and second is password
                    String username = data[0];
                    String password = data[1];

                    // Create Customer object
                    Customer customer = new Customer();
                    customer.setUsername(username); // Set username using the setter method
                    customer.setPassword(password); // Set password using the setter method

                    // Add the created customer to the list
                    customers.add(customer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle or log the exception as required
        }

        return customers;
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    public Customer findByUsername(String username) {
        if (username != null) {
            // Query your data store or list to find the user by username
            // Example: Assuming user list is 'users' and 'getUsername()' method returns the
            // username
            for (Customer customer : customers) {
                if (username.equals(customer.getUsername())) { // Ensure 'username' is not null before comparison
                    return customer;
                }
            }
        }
        return null;
    }

    public boolean authenticate(String username, String password) {
        // Load user data from the CSV file or stored list
        List<Customer> customerList = loadUsersFromCSV(FILE_PATH);

        for (Customer customer : customerList) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                return true; // Authentication successful
            }
        }
        return false; // Authentication failed
    }

    // add a new customer to the CSV file
    public void addCustomerToCSV(Customer customer) {
        if (isCustomerValid(customer)) {
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

    public boolean isCustomerValid(Customer customer) {
        if (customer.getUsername() == null || customer.getUsername().isEmpty()) {
            return false; // username shouldn't be null or empty
        }
        if (customer.getPassword() == null || customer.getPassword().length() < 8) {
            return false;
        }
        if (!customer.getUsername().matches("^[a-zA-Z0-9._-]{3,}$")) {
            return false;
        }
        if (!customer.getPassword().matches(".*\\d.*")) {
            return false;
        }
        return true;
    }

}