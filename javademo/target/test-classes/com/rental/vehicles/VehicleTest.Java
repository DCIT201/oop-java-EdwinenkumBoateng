package com.rental.vehicles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    
    @Test
    void testVehicleCreation() {
        Vehicle vehicle = new Car("V001", "Toyota Camry", 50.0, true);
        
        assertEquals("V001", vehicle.getVehicleId());
        assertEquals("Toyota Camry", vehicle.getModel());
        assertEquals(50.0, vehicle.getDailyRate());
        assertTrue(vehicle.isAvailable());
    }
    
    @Test
    void testAvailabilityToggle() {
        Vehicle vehicle = new Car("V001", "Toyota Camry", 50.0, true);
        
        vehicle.setAvailable(false);
        assertFalse(vehicle.isAvailable());
        
        vehicle.setAvailable(true);
        assertTrue(vehicle.isAvailable());
    }
    
    @Test
    void testRateCalculation() {
        Vehicle vehicle = new Car("V001", "Toyota Camry", 50.0, true);
        assertEquals(150.0, vehicle.calculateRentalCost(3));
    }
}