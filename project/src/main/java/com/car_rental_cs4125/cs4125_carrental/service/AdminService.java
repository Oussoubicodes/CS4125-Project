package com.car_rental_cs4125.cs4125_carrental.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.car_rental_cs4125.cs4125_carrental.model.Admin;
import com.car_rental_cs4125.cs4125_carrental.repository.AdminRepositoryImpl;

@Service
public class AdminService {


    private static final String FILE_PATH = "project\\src\\main\\resources\\admins.csv";
    private AdminRepositoryImpl adminRepositoryImpl;

    public boolean authenticate(String username, String password) {
       List<Admin> adminList = adminRepositoryImpl.loadAdminsFromCSV(FILE_PATH);

       for (Admin admin: adminList) {
        if(admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
            return true;
        }
       }
       return false;
    }

    public boolean isAdminValid(String username) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                String [] adminData = line.split(",");
                String existingUsername = adminData[0];
                if(existingUsername.equals(username)) {
                    reader.close();
                    return false;

                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    
}
