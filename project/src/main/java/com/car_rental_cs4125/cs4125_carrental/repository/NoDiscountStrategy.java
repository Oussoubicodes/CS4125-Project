package com.car_rental_cs4125.cs4125_carrental.repository;

public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double totalCost) {
        return totalCost; // No discount applied
    }
}

