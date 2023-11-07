package com.car_rental_cs4125.cs4125_carrental.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import com.car_rental_cs4125.cs4125_carrental.repository.CustomerRepositoryImpl;

class LoginControllerTest {
    private CustomerRepositoryImpl customerRepositoryImpl;
    private LoginController loginController;

    @BeforeEach
    public void setUp() {
        customerRepositoryImpl = mock(CustomerRepositoryImpl.class);
        loginController = new LoginController(customerRepositoryImpl);
    }

    @Test
    void testLoginWithValidCredentials() {
        String username = "testUser";
        String password = "testPassword";
        when(customerRepositoryImpl.authenticate(username, password)).thenReturn(true);

        ModelAndView modelAndView = loginController.login(username, password);
        assertEquals("redirect:/home", modelAndView.getViewName());
    }

    @Test
    void testLoginWithInvalidCredentials() {
        String username = "testUser";
        String password = "testPassword";
        when(customerRepositoryImpl.authenticate(username, password)).thenReturn(false);

        ModelAndView modelAndView = loginController.login(username, password);
        assertEquals("login", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel().get("error"));
    }

    @Test
    void testHome() {
        String viewName = loginController.home();
        assertEquals("home", viewName);
    }
}
