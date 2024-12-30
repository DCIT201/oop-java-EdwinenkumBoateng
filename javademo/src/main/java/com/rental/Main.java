package com.rental;

import com.rental.agency.RentalAgency;
import com.rental.agency.RentalTransaction;
import com.rental.customer.Customer;
import com.rental.vehicles.*;
import com.rental.exceptions.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Initialize rental agency
            RentalAgency agency = new RentalAgency();

            // Create and add vehicles
            Car car = new Car("C001", "Toyota Camry", 50.0, true);
            Motorcycle motorcycle = new Motorcycle("M001", "Honda CBR", 30.0, 600);
            Truck truck = new Truck("T001", "Ford F150", 80.0, 2500.0);

            agency.addVehicle(car);
            agency.addVehicle(motorcycle);
            agency.addVehicle(truck);

            // Create customers
            Customer customer1 = new Customer("CUST001", "John Doe");
            Customer customer2 = new Customer("CUST002", "Jane Smith");

            System.out.println("=== Rental System Demo ===");

            // Demonstrate rental process
            System.out.println("\n1. First Rental Transaction:");
            RentalTransaction transaction1 = agency.rentVehicle("C001", customer1, 3);
            printTransactionDetails(transaction1);

            // Return vehicle
            System.out.println("\n2. Returning Vehicle:");
            agency.returnVehicle("C001");
            System.out.println("Vehicle C001 returned successfully");

            // Second rental to demonstrate loyalty points
            System.out.println("\n3. Second Rental Transaction:");
            RentalTransaction transaction2 = agency.rentVehicle("M001", customer1, 4);
            printTransactionDetails(transaction2);

            // Display loyalty program status
            System.out.println("\n4. Loyalty Program Status:");
            System.out.println("Points: " + customer1.getLoyaltyProgram().getPoints());
            System.out.println("Tier: " + customer1.getLoyaltyProgram().getTier());

            // Demonstrate error handling
            System.out.println("\n5. Error Handling Demo:");
            try {
                agency.rentVehicle("INVALID", customer2, 1);
            } catch (VehicleNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void printTransactionDetails(RentalTransaction transaction) {
        System.out.println("Transaction ID: " + transaction.getTransactionId());
        System.out.println("Customer: " + transaction.getCustomer().getName());
        System.out.println("Vehicle: " + transaction.getVehicle().getModel());
        System.out.println("Rental Days: " + transaction.getRentalDays());
        System.out.println("Total Cost: $" + transaction.getTotalCost());
    }
}