package com.car_rental_cs4125.cs4125_carrental.repository;

import com.car_rental_cs4125.cs4125_carrental.model.Car;
import com.car_rental_cs4125.cs4125_carrental.model.Reservation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationRepoImplTest {

    @Mock
    private CarRepositoryImpl carRepository;

    @InjectMocks
    private ReservationRepositoryImpl reservationRepository;

    @Test
    public void testCreateReservation() throws IOException {
        // Mock data
        int carId = 1;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(3);
        String customerName = "John Doe";
        String customerEmail = "john@example.com";
        double totalCost = 100.0;

        // Mock behavior
        when(carRepository.findByCarID(carId)).thenReturn(new Car.Builder()
                .setId(carId)
                .setMake("Toyota")
                .setModel("Camry")
                .setType("Sedan")
                .setRegNumber("ABC123")
                .setYear("2022")
                .setFuelType("Petrol")
                .setTransmission("Automatic")
                .setMileage("5000")
                .setPricePerDay(50.0)
                .build());

        // Test the method
        Reservation reservation = reservationRepository.createReservation(carId, startDate, endDate, customerName, customerEmail, totalCost);

        // Assertions or verifications
        assertNotNull(reservation);
        assertEquals(carId, reservation.getCarId());
        // Add more assertions based on your logic
    }

    @Test
    public void testGetAllReservations() throws IOException {
        // Mock behavior
        // Mock the FileReader, CSVParser, and CSVRecord to return sample data

        // Test the method
        List<Reservation> allReservations = reservationRepository.getAllReservations();

        // Assertions or verifications
        assertNotNull(allReservations);
        // Add more assertions based on your logic
    }

    // Add more test methods for other functionalities

}

