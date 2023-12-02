package com.car_rental_cs4125.cs4125_carrental.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.car_rental_cs4125.cs4125_carrental.model.Reservation;
import com.car_rental_cs4125.cs4125_carrental.repository.ReservationRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class ReservationServiceImplTest {

    @Mock
    private ReservationRepositoryImpl reservationRepositoryImpl;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculateTotalCost() {
        // Test the calculateTotalCost method

        // Mock data
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(7);
        double pricePerDay = 10.0;

        // Call the method
        double totalCost = reservationService.calculateTotalCost(startDate, endDate, pricePerDay);

        // Assert the result
        assertEquals(63.0, totalCost); // You need to adjust the expected value based on your discount logic
    }

    @Test
    void checkAvailability_vehicleAvailable() throws IOException {
        // Test the checkAvailability method when the vehicle is available

        // Mock data
        int carId = 1;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(7);

        // Mock the repository response
        when(reservationRepositoryImpl.getAllReservations()).thenReturn(new ArrayList<>());

        // Call the method
        boolean isAvailable = reservationService.checkAvailability(carId, startDate, endDate);

        // Assert the result
        assertTrue(isAvailable);
    }

    @Test
    void checkAvailability_vehicleNotAvailable() throws IOException {
        // Test the checkAvailability method when the vehicle is not available

        // Mock data
        int carId = 1;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(7);

        // Mock existing reservations
        List<Reservation> existingReservations = new ArrayList<>();
        existingReservations.add(new Reservation(1, carId, startDate.minusDays(5), endDate.minusDays(1), "John Doe", "john@example.com", 500.0));
        when(reservationRepositoryImpl.getAllReservations()).thenReturn(existingReservations);

        // Call the method
        boolean isAvailable = reservationService.checkAvailability(carId, startDate, endDate);

        // Assert the result
        assertFalse(isAvailable);
    }

    @Test
    void addReservation() throws IOException {
        // Test the addReservation method

        // Mock data
        Reservation reservationToAdd = new Reservation(1, 1, LocalDate.now(), LocalDate.now().plusDays(3), "John Doe", "john@example.com", 300.0);

        // Call the method
        reservationService.addReservation(reservationToAdd);

        // Verify that the writeAllReservationsToCSV method is called with the correct argument
        verify(reservationRepositoryImpl, times(1)).writeAllReservationsToCSV(List.of(reservationToAdd));
    }

    @Test
    void cancelReservation() throws IOException {
        // Test the cancelReservation method

        // Mock data
        int reservationIdToCancel = 1;

        // Mock existing reservations
        List<Reservation> existingReservations = new ArrayList<>();
        existingReservations.add(new Reservation(reservationIdToCancel, 1, LocalDate.now(), LocalDate.now().plusDays(3), "John Doe", "john@example.com", 300.0));
        when(reservationRepositoryImpl.getAllReservations()).thenReturn(existingReservations);

        // Call the method
        reservationService.cancelReservation(reservationIdToCancel);

        // Verify that the reservation is removed from the repository
        assertEquals(0, existingReservations.size());
    }
}

