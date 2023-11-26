package com.car_rental_cs4125.cs4125_carrental.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.car_rental_cs4125.cs4125_carrental.model.Admin;

@Service
public class AdminRepositoryImpl implements AdminRepository {

    private static final String FILE_PATH = "project\\src\\main\\resources\\admins.csv";
    
    private List<Admin> admins = new ArrayList<>();

    public AdminRepositoryImpl() {
        this.admins = loadAdminsFromCSV(FILE_PATH);
    }

    public List<Admin> loadAdminsFromCSV(String filePath) {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length >= 2) {
                    String username = data[0];
                    String password = data[1];

                    Admin admin = new Admin();
                    admin.setUsername(username);
                    admin.setPassword(password);

                    admins.add(admin);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public List<Admin> findAll() {
        return admins;
    }

    @Override
    public Admin findByUsername(String username) {
        if(username != null) {
            // Query csv to find the admin by username

            for(Admin admin : admins) {
                if(username.equals(admin.getUsername())) {
                    return admin;
                }
            }
        }
        return null;
    }

    @Override
    public boolean authenticate(String username, String password) {
       List<Admin> adminList = loadAdminsFromCSV(FILE_PATH);

       for (Admin admin: adminList) {
        if(admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
            return true;
        }
       }
       return false;
    }

    @Override
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
