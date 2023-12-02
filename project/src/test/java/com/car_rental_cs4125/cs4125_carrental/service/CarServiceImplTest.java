package com.car_rental_cs4125.cs4125_carrental.service;

import com.car_rental_cs4125.cs4125_carrental.model.Car;
import com.car_rental_cs4125.cs4125_carrental.repository.CarRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CarServiceImplTest {

    @Mock
    private CarRepositoryImpl carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCar_shouldNotThrowException() throws IOException {
        Car carToAdd = new Car.Builder()
                .setId(1)
                .setMake("Toyota")
                .setModel("Camry")
                .setPricePerDay(50.0)
                .build();

        List<Car> existingCars = new ArrayList<>();
        when(carRepository.getAllCars()).thenReturn(existingCars);

        assertDoesNotThrow(() -> carService.addCar(carToAdd));

        verify(carRepository, times(1)).writeCarsToCSV(anyList());
    }


    @Test
    void addCar_shouldThrowIOException() throws IOException {
        Car carToAdd = new Car.Builder()
                .setId(1)
                .setMake("Toyota")
                .setModel("Camry")
                .setPricePerDay(50.0)
                .build();

        List<Car> existingCars = new ArrayList<>();
        when(carRepository.getAllCars()).thenReturn(existingCars);
        doThrow(IOException.class).when(carRepository).writeCarsToCSV(anyList());

        assertThrows(IOException.class, () -> carService.addCar(carToAdd));

        verify(carRepository, times(1)).writeCarsToCSV(anyList());
    }


    @Test
    void removeCar_shouldNotThrowException() throws IOException {
        int carIdToRemove = 1;

        List<Car> existingCars = new ArrayList<>();
        existingCars.add(new Car.Builder()
                .setId(1)
                .setMake("Toyota")
                .setModel("Camry")
                .setPricePerDay(50.0)
                .build());

        when(carRepository.getAllCars()).thenReturn(existingCars);

        assertDoesNotThrow(() -> carService.removeCar(carIdToRemove));

        verify(carRepository, times(1)).writeCarsToCSV(anyList());
    }

    @Test
    void removeCar_shouldThrowIOException() throws IOException {
        int carIdToRemove = 1;

        List<Car> existingCars = new ArrayList<>();
        existingCars.add(new Car.Builder()
                .setId(1)
                .setMake("Toyota")
                .setModel("Camry")
                .setPricePerDay(50.0)
                .build());

        when(carRepository.getAllCars()).thenReturn(existingCars);
        doThrow(IOException.class).when(carRepository).writeCarsToCSV(anyList());

        assertThrows(IOException.class, () -> carService.removeCar(carIdToRemove));

        verify(carRepository, times(1)).writeCarsToCSV(anyList());
    }

    @Test
    void updateCar_shouldNotThrowException() throws IOException {
        Car updatedCar = new Car.Builder()
                .setId(1)
                .setMake("Toyota")
                .setModel("Corolla")
                .setPricePerDay(55.0)
                .build();

        List<Car> existingCars = new ArrayList<>();
        existingCars.add(new Car.Builder()
                .setId(1)
                .setMake("Toyota")
                .setModel("Camry")
                .setPricePerDay(50.0)
                .build());

        when(carRepository.getAllCars()).thenReturn(existingCars);

        assertDoesNotThrow(() -> carService.updateCar(updatedCar));

        verify(carRepository, times(1)).writeCarsToCSV(anyList());
    }

    @Test
    void updateCar_shouldThrowIOException() throws IOException {
        Car updatedCar = new Car.Builder()
                .setId(1)
                .setMake("Toyota")
                .setModel("Corolla")
                .setPricePerDay(55.0)
                .build();

        List<Car> existingCars = new ArrayList<>();
        existingCars.add(new Car.Builder()
                .setId(1)
                .setMake("Toyota")
                .setModel("Camry")
                .setPricePerDay(50.0)
                .build());

        when(carRepository.getAllCars()).thenReturn(existingCars);
        doThrow(IOException.class).when(carRepository).writeCarsToCSV(anyList());

        assertThrows(IOException.class, () -> carService.updateCar(updatedCar));

        verify(carRepository, times(1)).writeCarsToCSV(anyList());
    }
}
