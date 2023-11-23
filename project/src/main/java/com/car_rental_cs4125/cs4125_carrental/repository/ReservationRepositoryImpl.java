package com.car_rental_cs4125.cs4125_carrental.repository;

import com.car_rental_cs4125.cs4125_carrental.model.Car;
import com.car_rental_cs4125.cs4125_carrental.model.Reservation;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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


    public double calculateTotalCost(String startDate, String endDate, double pricePerDay) {
        // Parse start_date and end_date to LocalDate objects (assuming they are in "yyyy-MM-dd" format)
        LocalDate startDateObj = LocalDate.parse(startDate);
        LocalDate endDateObj = LocalDate.parse(endDate);

        // Calculate the number of days between start_date and end_date
        long numberOfDays = ChronoUnit.DAYS.between(startDateObj, endDateObj) + 1;

        // Calculate the total cost based on the number of days and pricePerDay
        return numberOfDays * pricePerDay;
    }

    public boolean checkAvailability(int carId, String startDate, String endDate) throws IOException {
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
    private boolean isOverlap(String startDate1, String endDate1, String startDate2, String endDate2) {
        // Parse the dates for comparison
        long start1 = parseDate(startDate1);
        long end1 = parseDate(endDate1);
        long start2 = parseDate(startDate2);
        long end2 = parseDate(endDate2);

        // Check if the date ranges overlap
        return !(end1 < start2 || start1 > end2);
    }

    @Override
    public List<Reservation> getAllReservations() throws IOException {
        try (Reader reader = new FileReader(CSV_RESERVATIONS)) {
            try (CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
                for (CSVRecord record : csvParser) {
                    int reservationId = Integer.parseInt(record.get("reservationId"));
                    int carId = Integer.parseInt(record.get("carId"));
                    String startDate = record.get("startDate");
                    String endDate = record.get("endDate");
                    String customerName = record.get("customerName");
                    String customerEmail = record.get("customerEmail");
                    double totalCost = Double.parseDouble(record.get("totalCost"));

                    Reservation reservation = new Reservation(reservationId, carId, startDate, endDate, customerName, customerEmail, totalCost);
                    reservations.add(reservation);
                }
            }
            for (Reservation res:reservations) {
                System.out.println(res.toString());
            }
            return reservations;
        }
    }

    @Override
    public void makeReservation(int carId, String startDate, String endDate, String customerName, String customerEmail) {

    }

    @Override
    public void cancelReservation(int reservationId) {

    }

    @Override
    public Reservation getReservationDetails(int reservationId) {
        return null;
    }
}
