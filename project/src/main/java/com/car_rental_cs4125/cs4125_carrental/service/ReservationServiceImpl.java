package com.car_rental_cs4125.cs4125_carrental.service;

import com.car_rental_cs4125.cs4125_carrental.model.Reservation;
import com.car_rental_cs4125.cs4125_carrental.repository.ReservationRepositoryImpl;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service

public class ReservationServiceImpl implements ReservationService {

    private ReservationRepositoryImpl reservationRepositoryImpl;

public ReservationServiceImpl(ReservationRepositoryImpl reservationRepositoryImpl){
    this.reservationRepositoryImpl = reservationRepositoryImpl;
}
    //Strategy Pattern
    public double calculateTotalCost(LocalDate startDate, LocalDate endDate, double pricePerDay) {
        // Parse start_date and end_date to LocalDate objects (assuming they are in "yyyy-MM-dd" format)
        long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        double totalCost = numberOfDays * pricePerDay;

        // Apply discount logic directly
        if (numberOfDays > 5) {
            Reservation reservation = new Reservation(); // Create a dummy reservation to set the discount strategy
            reservation.setDiscountStrategy(new DiscountStrategyImpl());
            return reservation.calculateTotalCostWithDiscount(totalCost);
        }
        return totalCost;
    }

    public boolean checkAvailability(int carId, LocalDate startDate, LocalDate endDate) throws IOException {
        // Retrieve the vehicle based on its ID
        List<Reservation> reservations = reservationRepositoryImpl.getAllReservations();

        // Check if the car is available for the given period
        for (Reservation existingRes : reservations) {
            if (existingRes.getCarId() == carId) {
                // Check if the requested period overlaps with an existing reservation
                if (isOverlap(startDate, endDate, existingRes.getStartDate(), existingRes.getEndDate())) {
                    System.out.println("Vehicle is not available for the requested period.");
                    return false;
                }
            }
        }

        System.out.println("Vehicle is available for the requested period.");
        return true;
    }


    //Check if dates overlap
    private boolean isOverlap(LocalDate startDate1, LocalDate endDate1, LocalDate startDate2, LocalDate endDate2) {
        // Check if the date ranges overlap
        return !(endDate1.isBefore(startDate2) || startDate1.isAfter(endDate2));
    }

    @Override
    public void addReservation(Reservation reservation) throws IOException {
        List<Reservation> newReservations = new ArrayList<>();
        newReservations.add(reservation);
        reservationRepositoryImpl.writeAllReservationsToCSV(newReservations);
    }

    @Override
    public void cancelReservation(int reservationId) throws IOException {
        List<Reservation> reservations = reservationRepositoryImpl.getAllReservations();

        Iterator<Reservation> iterator = reservations.iterator();
        while (iterator.hasNext()) {
            Reservation reservation = iterator.next();
            if (reservation.getReservationId() == reservationId) {
                iterator.remove();
                return;
            }
        }
    }

}
