package com.car_rental_cs4125.cs4125_carrental.service;


public class DiscountStrategyImpl implements DiscountStrategy {

    @Override
    public double applyFiveDay(double totalCost) {
        return totalCost * 0.9; // 10% discount
    }




}
