package com.car_rental_cs4125.cs4125_carrental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.car_rental_cs4125.cs4125_carrental.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository <Admin, Long> {
    List<Admin> findAll();

    Admin findByUsername(String username);
}
