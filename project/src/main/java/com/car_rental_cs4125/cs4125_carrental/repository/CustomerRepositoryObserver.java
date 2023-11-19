package com.car_rental_cs4125.cs4125_carrental.repository;

import com.car_rental_cs4125.cs4125_carrental.model.Customer;

public interface CustomerRepositoryObserver {
    void onCustomerAdded(Customer customer);
}
