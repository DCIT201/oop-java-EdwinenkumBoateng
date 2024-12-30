package com.rental.agency;

import com.rental.customer.Customer;
import com.rental.vehicles.Car;
import com.rental.exceptions.InvalidInputException;
import com.rental.exceptions.VehicleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RentalAgencyTest {
    private RentalAgency agency;
    private Customer customer;
    private Car car;

    @BeforeEach
    void setUp() throws InvalidInputException {
        agency = new RentalAgency();
        customer = new Customer("C001", "John Doe");
        car = new Car("V001", "Toyota Camry", 50.0, true);
        agency.addVehicle(car);
    }

    @Test
    void testAddVehicle() {
        Car newCar = new Car("V002", "Honda Civic", 45.0, true);
        agency.addVehicle(newCar);
        assertEquals(2, agency.getAvailableVehicles().size());
    }

    @Test
    void testRentVehicle() throws VehicleNotFoundException, InvalidInputException {
        RentalTransaction transaction = agency.rentVehicle("V001", customer, 5);
        assertNotNull(transaction);
        assertEquals(customer, transaction.getCustomer());
        assertEquals(car, transaction.getVehicle());
        assertEquals(5, transaction.getRentalDays());
        assertFalse(car.isAvailableForRental());
    }

    @Test
    void testReturnVehicle() throws VehicleNotFoundException, InvalidInputException {
        agency.rentVehicle("V001", customer, 5);
        agency.returnVehicle("V001");
        assertTrue(car.isAvailableForRental());
    }

    @Test
    void testRentUnavailableVehicle() throws VehicleNotFoundException, InvalidInputException {
        agency.rentVehicle("V001", customer, 5);
        assertThrows(InvalidInputException.class, () -> {
            agency.rentVehicle("V001", customer, 3);
        });
    }

    @Test
    void testVehicleNotFound() {
        assertThrows(VehicleNotFoundException.class, () -> {
            agency.rentVehicle("NONEXISTENT", customer, 5);
        });
    }

    @Test
    void testInvalidRentalDays() {
        assertThrows(InvalidInputException.class, () -> {
            agency.rentVehicle("V001", customer, 0);
        });
    }

    @Test
    void testGetTransactionHistory() throws VehicleNotFoundException, InvalidInputException {
        agency.rentVehicle("V001", customer, 5);
        assertEquals(1, agency.getTransactionHistory().size());
    }

    @Test
    void testRemoveVehicle() throws VehicleNotFoundException {
        agency.removeVehicle("V001");
        assertEquals(0, agency.getAvailableVehicles().size());
    }
}