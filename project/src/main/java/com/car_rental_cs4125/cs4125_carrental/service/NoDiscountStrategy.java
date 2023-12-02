package com.car_rental_cs4125.cs4125_carrental.service;

import com.car_rental_cs4125.cs4125_carrental.service.DiscountStrategy;

public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyFiveDay(double totalCost) {
        return totalCost; // No discount applied
    }
}

