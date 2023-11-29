package com.car_rental_cs4125.cs4125_carrental.repository;

public class DiscountStrategyImpl implements DiscountStrategy{

    @Override
    public double applyDiscount(double totalCost) {
        return totalCost * 0.9; // 10% discount
    }




}
