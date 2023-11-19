package com.car_rental_cs4125.cs4125_carrental.repository;

import com.car_rental_cs4125.cs4125_carrental.model.Reservation;

import java.io.IOException;
import java.util.List;

public interface ReservationRepository {
    boolean checkAvailability(List<Reservation> reservations,int carId, String startDate, String endDate) throws IOException;
    public double calculateTotalCost(String startDate, String endDate,double pricePerDay);
    void makeReservation(int carId, String startDate, String endDate, String customerName, String customerEmail);
    void cancelReservation(int reservationId);
    Reservation getReservationDetails(int reservationId);
    List<Reservation> getAllReservations() throws IOException;
}
