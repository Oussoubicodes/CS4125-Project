package com.car_rental_cs4125.cs4125_carrental.service;

import com.car_rental_cs4125.cs4125_carrental.model.Reservation;

import java.io.IOException;
import java.time.LocalDate;

public interface ReservationService {
    double calculateTotalCost(LocalDate startDate, LocalDate endDate, double pricePerDay);
    boolean checkAvailability(int carId, LocalDate startDate, LocalDate endDate) throws IOException;
    void cancelReservation(int reservationId) throws IOException;
    void addReservation(Reservation reservation) throws IOException;
}
