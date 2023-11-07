package com.car_rental_cs4125.cs4125_carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.car_rental_cs4125.cs4125_carrental.repository.CustomerRepositoryImpl;

@Controller
public class LoginController {

    private CustomerRepositoryImpl customerRepoImpl;

    @Autowired
    public LoginController(CustomerRepositoryImpl customerRepoImpl) {
        this.customerRepoImpl = customerRepoImpl;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password) {
        boolean isValid = customerRepoImpl.authenticate(username, password);
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
