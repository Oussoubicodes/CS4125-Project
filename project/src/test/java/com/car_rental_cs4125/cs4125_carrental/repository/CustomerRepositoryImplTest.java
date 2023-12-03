package com.car_rental_cs4125.cs4125_carrental.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.car_rental_cs4125.cs4125_carrental.model.Customer;
import com.car_rental_cs4125.cs4125_carrental.service.CustomerServiceImpl;

class CustomerRepositoryImplTest {

    private CustomerServiceImpl customerServiceImpl;
    private final String testFilePath = "project\\src\\main\\resources\\users.csv"; // Adjust the test file path as needed

    @BeforeEach
    public void setUp() {
        customerServiceImpl = new CustomerServiceImpl();
    }

    @Test
    void testLoadUsersFromCSV() {
        List<Customer> customers = customerServiceImpl.loadUsersFromCSV(testFilePath);
        assertNotNull(customers);
        // Add more assertions based on the expected behavior
    }

    @Test
    void testFindAll() {
        List<Customer> customers = customerServiceImpl.findAll();
        assertNotNull(customers);
        // Add more assertions based on the expected behavior
    }

    @Test
    void testFindByUsername() {
        Customer foundCustomer = customerServiceImpl.findByUsername("testUsername");
        assertNull(foundCustomer); // Assuming "testUsername" is not present in the test data
        // Add more assertions based on the expected behavior
    }

    @Test
    void testAuthenticate() {
        boolean authenticated = customerServiceImpl.authenticate("testUsername", "testPassword");
        assertFalse(authenticated); // Assuming "testUsername" and "testPassword" are not in the test data
        // Add more assertions based on the expected behavior
    }

    @Test
    void testAddCustomerToCSV() {
        Customer customer = new Customer();
        customer.setUsername("newUser");
        customer.setPassword("password123");

        assertDoesNotThrow(() -> customerServiceImpl.addCustomerToCSV(customer));
        // Add more assertions based on the expected behavior
    }

    @Test
    void testIsCustomerValid() {
        Customer invalidCustomer = new Customer();
        invalidCustomer.setUsername("acceptableUsername");
        invalidCustomer.setPassword("accept");

        assertFalse(customerServiceImpl.isCustomerValid(invalidCustomer));
        // Add more assertions based on the expected behavior
    }
}