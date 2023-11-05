package com.car_rental_cs4125.cs4125_carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.car_rental_cs4125.cs4125_carrental.model.Customer;
import com.car_rental_cs4125.cs4125_carrental.service.CustomerService;

@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
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
    
        if (!customerService.isCustomerValid(customer)) {
            modelAndView.setViewName("registration");
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("error", "Invalid customer details. Please check your inputs");
        } else {
            customerService.addCustomerToCSV(customer);
            modelAndView.setViewName("redirect:/login"); // Redirect to the login page after successful registration
        }
    
        return modelAndView;
    }
    

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password) {
        boolean isValid = customerService.authenticate(username, password);
        ModelAndView modelAndView = new ModelAndView("login");
        if (isValid) {
            modelAndView.setViewName("redirect:/home"); // Redirect to home page on successful login
        } else {
            modelAndView.addObject("error", "Invalid username or password");
        }
        return modelAndView;
    }

    

    @GetMapping("/home")
    public String home() {
        return "home";
    }

}
