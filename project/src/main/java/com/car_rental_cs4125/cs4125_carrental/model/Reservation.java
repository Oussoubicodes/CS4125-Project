package com.car_rental_cs4125.cs4125_carrental.model;

import jakarta.persistence.*;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(insertable=false, updatable=false)
    @PrimaryKeyJoinColumn(name = "carId")
    private Car car;

        private int reservationId;
        private String customerName;
        private String customerEmail;
        private int carId;
        private String startDate;
        private String endDate;
        private Double totalCost;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    // Constructors, getters, and setters

    public Reservation() {
    }

    public Reservation(int reservationId, int carId, String customerName, String customerEmail, String startDate, String endDate, double totalCost){
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
    }


    public void setReservationId(int reservationId) {
            this.reservationId = reservationId;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public void setTotalCost(Double totalCost) {
            this.totalCost = totalCost;
        }

        public int getReservationId() {
            return reservationId;
        }

        public int getCarId() {
            return carId;
        }

    public String getCustomerEmail() {
        return customerEmail;
    }


        public String getCustomerName() {
            return customerName;
        }

        public String getStartDate() {
            return startDate;
        }

        public Double getTotalCost() {
            return totalCost;
        }

        public String getEndDate() {
            return endDate;
        }

    @Override
    public String toString(){
        return String.format(
                "Reservation Details:%n" +
                        "  Reservation ID: %d%n" +
                        "  Vehicle ID: %d%n" +
                        "  Start Date: %s%n" +
                        "  End Date: %s%n" +
                        "  Customer Name: %s%n" +
                        "  Customer Email: %s%n" +
                        "  Total Cost: %.2f%n",
                reservationId, carId, startDate, endDate, customerName, customerEmail, totalCost);
    }
    }

