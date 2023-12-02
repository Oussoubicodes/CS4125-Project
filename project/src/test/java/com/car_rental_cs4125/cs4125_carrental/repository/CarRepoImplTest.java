package com.car_rental_cs4125.cs4125_carrental.repository;

import com.car_rental_cs4125.cs4125_carrental.model.Car;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CarRepoImplTest {

    @Mock
    private CSVParser csvParser;

    @InjectMocks
    private CarRepositoryImpl carRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCars() throws IOException {
        // Mock CSVRecord
        CSVRecord csvRecord = mock(CSVRecord.class);
        when(csvRecord.get("id")).thenReturn("1");
        when(csvRecord.get("make")).thenReturn("Toyota");
        when(csvRecord.get("model")).thenReturn("Camry");
        // Add more fields as needed...

        // Mock CSVParser
        when(csvParser.iterator()).thenReturn(List.of(csvRecord).iterator());

        // Mock FileReader
        when(carRepository.getAllCars()).thenCallRealMethod();

        List<Car> cars = carRepository.getAllCars();
        assertEquals(1, cars.size());
        assertEquals("Toyota", cars.get(0).getMake());
        // Add more assertions as needed...
    }

    @Test
    void findByCarID() throws IOException {
        // Mock CSVRecord
        CSVRecord csvRecord = mock(CSVRecord.class);
        when(csvRecord.get("id")).thenReturn("1");
        when(csvRecord.get("make")).thenReturn("Toyota");
        when(csvRecord.get("model")).thenReturn("Camry");
        // Add more fields as needed...

        // Mock CSVParser
        when(csvParser.iterator()).thenReturn(List.of(csvRecord).iterator());

        // Mock FileReader
        when(carRepository.getAllCars()).thenCallRealMethod();

        Car car = carRepository.findByCarID(1);
        assertEquals("Toyota", car.getMake());
        // Add more assertions as needed...
    }

    // Add more test methods for other functionalities...
}
