package com.rental.customer;

import com.rental.agency.RentalTransaction;
import com.rental.exceptions.InvalidInputException;
import com.rental.vehicles.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    private Customer customer;
    
    @BeforeEach
    void setUp() throws InvalidInputException {
        customer = new Customer("CUST001", "John Doe");
    }
    
    @Test
    void testCustomerCreation() {
        assertEquals("CUST001", customer.getCustomerId());
        assertEquals("John Doe", customer.getName());
        assertNotNull(customer.getRentalHistory());
        assertTrue(customer.getRentalHistory().isEmpty());
        assertNotNull(customer.getLoyaltyProgram());
    }
    
    @Test
    void testInvalidCustomerId() {
        assertThrows(InvalidInputException.class, () -> {
            new Customer("", "John Doe");
        });
        
        assertThrows(InvalidInputException.class, () -> {
            new Customer(null, "John Doe");
        });
    }
    
    @Test
    void testInvalidCustomerName() {
        assertThrows(InvalidInputException.class, () -> {
            new Customer("CUST001", "");
        });
        
        assertThrows(InvalidInputException.class, () -> {
            new Customer("CUST001", null);
        });
    }
    
    @Test
    void testAddRentalTransaction() throws InvalidInputException {
        Car car = new Car("C001", "Toyota", 50.0, true);
        RentalTransaction transaction = new RentalTransaction(customer, car, 5);
        
        customer.addRentalTransaction(transaction);
        
        assertEquals(1, customer.getRentalHistory().size());
        assertEquals(transaction, customer.getRentalHistory().get(0));
        assertTrue(customer.getLoyaltyProgram().getPoints() > 0);
    }
    
    @Test
    void testRentalHistoryImmutable() throws InvalidInputException {
        Car car = new Car("C001", "Toyota", 50.0, true);
        RentalTransaction transaction = new RentalTransaction(customer, car, 5);
        
        customer.addRentalTransaction(transaction);
        List<RentalTransaction> history = customer.getRentalHistory();
        
        // Try to modify the returned list
        assertThrows(UnsupportedOperationException.class, () -> {
            history.clear();
        });
    }
}
