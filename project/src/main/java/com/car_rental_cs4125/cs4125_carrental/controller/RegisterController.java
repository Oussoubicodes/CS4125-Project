package com.car_rental_cs4125.cs4125_carrental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.car_rental_cs4125.cs4125_carrental.model.Customer;
import com.car_rental_cs4125.cs4125_carrental.service.CustomerServiceImpl;

@Controller
public class RegisterController {

    private CustomerServiceImpl customerServiceImpl;

    public RegisterController(CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
    }

    
    @GetMapping("/register")
    public ModelAndView showRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute("customer") Customer customer) {
        ModelAndView modelAndView = new ModelAndView();
    
        if (!customerServiceImpl.isCustomerValid(customer)) {
            modelAndView.setViewName("registration");
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("error", "Invalid customer details. Please check your inputs");
        } else {
            String username = customer.getUsername();
            String password = customer.getPassword();
    
            customerServiceImpl.createCustomer(username, password);
            modelAndView.setViewName("redirect:/login"); // Redirect to the login page after successful registration
        }
    
        return modelAndView;
    }
    
}
