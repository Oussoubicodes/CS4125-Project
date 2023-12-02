package com.car_rental_cs4125.cs4125_carrental.service;


import java.io.Serializable;

public interface DiscountStrategy extends Serializable {
    double applyFiveDay(double totalCost);
}
