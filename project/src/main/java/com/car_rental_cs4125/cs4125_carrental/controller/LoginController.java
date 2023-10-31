package com.car_rental_cs4125.cs4125_carrental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private static final String HARD_CODED_USERNAME = "username";
    private static final String HARD_CODED_PASSWORD = "password";

    @GetMapping("/")
    public String loginForm() {
        return "login-page";
    }

    @PostMapping("/login-page")
    public ModelAndView login(String username, String password) {
        ModelAndView modelAndView = new ModelAndView();

        if (HARD_CODED_USERNAME.equals(username) && HARD_CODED_PASSWORD.equals(password)) {
            modelAndView.setViewName("redirect:/dashboard");
        } else {
            modelAndView.setViewName("login-page");
            modelAndView.addObject("error", "Invalid credentials");
        }

        return modelAndView;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}

