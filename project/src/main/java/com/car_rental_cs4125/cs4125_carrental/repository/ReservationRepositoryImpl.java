package com.car_rental_cs4125.cs4125_carrental.repository;

import com.car_rental_cs4125.cs4125_carrental.model.Car;
import com.car_rental_cs4125.cs4125_carrental.model.Reservation;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.apache.tomcat.util.http.FastHttpDateFormat.parseDate;

@Service

public class ReservationRepositoryImpl implements ReservationRepository {


    private final CarRepositoryImpl carRepositoryImpl;
    private static List<Reservation> reservations = new ArrayList<>();
    private static final String CSV_RESERVATIONS = "C:\\Users\\ameen\\CS4125(2)\\CS4125-Project\\project\\src\\main\\resources\\carReservations.csv";


    public ReservationRepositoryImpl(CarRepositoryImpl carRepositoryImpl) {
        this.carRepositoryImpl = carRepositoryImpl;
    }

    //Factory  Method
    @Override
    public Reservation createReservation(int carId, LocalDate startDate, LocalDate endDate, String customerName, String customerEmail, double totalCost) throws IOException {
        // Look up the Car object by carId
        Car car = carRepositoryImpl.findByCarID(carId);

        // Check if the car with the specified carId exists
        if (car != null) {
            // Create and return a new Reservation
            return new Reservation(getNextReservationId(getAllReservations()), carId, startDate, endDate, customerName, customerEmail, totalCost);
        } else {
            // Handle the case where the car with the specified carId is not found (throw an exception or handle it accordingly)
            throw new IllegalArgumentException("Car with ID " + carId + " not found");
        }
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
        List<Reservation> reservations = getAllReservations();

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
    public List<Reservation> getAllReservations() throws IOException {
        try (Reader reader = new FileReader(CSV_RESERVATIONS)) {
            try (CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
                for (CSVRecord record : csvParser) {
                    int reservationId = Integer.parseInt(record.get("reservationId"));
                    int carId = Integer.parseInt(record.get("carId"));
                    LocalDate startDate = LocalDate.parse(record.get("startDate"));
                    LocalDate endDate = LocalDate.parse(record.get("endDate"));
                    String customerName = record.get("customerName");
                    String customerEmail = record.get("customerEmail");
                    double totalCost = Double.parseDouble(record.get("totalCost"));

                    Reservation reservation = new Reservation(reservationId, carId, startDate, endDate, customerName, customerEmail, totalCost);
                    reservations.add(reservation);
                }
            }
            for (Reservation res : reservations) {
                System.out.println(res.toString());
            }
            return reservations;
        }
    }

    @Override
    public void addReservation(Reservation reservation) throws IOException {
        List<Reservation> newReservations = new ArrayList<>();
        newReservations.add(reservation);
        writeAllReservationsToCSV(newReservations);
    }

    public List<Reservation> searchReservation(int reservationId, String customerName) throws IOException {
        List<Reservation> allReservations = getAllReservations();
        List<Reservation> searchResults = new ArrayList<>();

        for (Reservation reservation : allReservations) {
            if (reservation.getReservationId() == reservationId &&
                    reservation.getCustomerName().equalsIgnoreCase(customerName)) {
                searchResults.add(reservation);
            }
        }

        return searchResults;
    }



    @Override
    public void cancelReservation(int reservationId) throws IOException {
        List<Reservation> reservations = getAllReservations();

            Iterator<Reservation> iterator = reservations.iterator();
            while (iterator.hasNext()) {
                Reservation reservation = iterator.next();
                if (reservation.getReservationId() == reservationId) {
                    iterator.remove();
                    return;
                }
            }
        }

    private void writeAllReservationsToCSV(List<Reservation> reservations) throws IOException {
        try (Writer csvWriter = new FileWriter(CSV_RESERVATIONS, true);  // Set append mode to true
             CSVPrinter csvPrinter = new CSVPrinter(csvWriter, CSVFormat.DEFAULT.withHeader())) {
            for (Reservation reservation : reservations) {
                csvPrinter.printRecord(
                        reservation.getReservationId(),
                        reservation.getCarId(),
                        reservation.getStartDate(),
                        reservation.getEndDate(),
                        reservation.getCustomerName(),
                        reservation.getCustomerEmail(),
                        reservation.getTotalCost());
            }
        }
    }

    @Override
    public int getNextReservationId(List<Reservation> reservations) {
        return reservations.size()+1;
    }

    }

