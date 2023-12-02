package com.car_rental_cs4125.cs4125_carrental.repository;

import com.car_rental_cs4125.cs4125_carrental.model.Reservation;
import org.hibernate.validator.internal.util.privilegedactions.LoadClass;
import org.springframework.cglib.core.Local;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository {

    Reservation createReservation(int carId, LocalDate startDate, LocalDate endDate, String customerName, String customerEmail, double totalCost) throws IOException;
    void addReservation(Reservation reservation) throws IOException;
    int getNextReservationId(List<Reservation> reservations);
    List<Reservation> getAllReservations() throws IOException;
}
