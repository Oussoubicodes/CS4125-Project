package com.car_rental_cs4125.cs4125_carrental.controller;

import com.car_rental_cs4125.cs4125_carrental.repository.AdminRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminLoginController {

    private AdminRepositoryImpl adminRepositoryImpl;

    @Autowired
    public AdminLoginController(AdminRepositoryImpl adminRepositoryImpl) {
        this.adminRepositoryImpl = adminRepositoryImpl;
    }

    @GetMapping("/adminLogin")
    public String showAdminLoginForm() {
        return "adminlogin";
    }

    @PostMapping("/adminLogin")
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password) {
        boolean isValid = adminRepositoryImpl.authenticate(username, password);
        ModelAndView modelAndView = new ModelAndView("adminLogin");
        if (isValid) {
            modelAndView.setViewName("redirect:/manageCars");
        } else {
            modelAndView.addObject("error", "Invalid username or password");
        }
        return modelAndView;
    }

}
