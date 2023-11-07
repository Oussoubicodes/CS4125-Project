package com.car_rental_cs4125.cs4125_carrental.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import com.car_rental_cs4125.cs4125_carrental.model.Customer;
import com.car_rental_cs4125.cs4125_carrental.repository.CustomerRepositoryImpl;

class RegistrationControllerTest {
    private CustomerRepositoryImpl customerRepositoryImpl;
    private RegisterController registerController;

    @BeforeEach
    void setUp() {
        customerRepositoryImpl = mock(CustomerRepositoryImpl.class);
        registerController = new RegisterController(customerRepositoryImpl);
    }

    @Test
     void testShowRegistrationForm() {
        ModelAndView modelAndView = registerController.showRegistrationForm();
        assertEquals("registration", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel().get("customer"));
    }

    @Test
    void testRegisterWithValidCustomer() {
        Customer validCustomer = new Customer();
        when(customerRepositoryImpl.isCustomerValid(validCustomer)).thenReturn(true);

        ModelAndView modelAndView = registerController.register(validCustomer);
        assertEquals("redirect:/login", modelAndView.getViewName());
        verify(customerRepositoryImpl, times(1)).addCustomerToCSV(validCustomer);
    }

    @Test
    void testRegisterWithInvalidCustomer() {
        Customer invalidCustomer = new Customer();
        when(customerRepositoryImpl.isCustomerValid(invalidCustomer)).thenReturn(false);

        ModelAndView modelAndView = registerController.register(invalidCustomer);
        assertEquals("registration", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel().get("customer"));
        assertNotNull(modelAndView.getModel().get("error"));
    }

}
