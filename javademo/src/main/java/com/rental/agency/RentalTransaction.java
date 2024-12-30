package com.rental.agency;

import com.rental.customer.Customer;
import com.rental.vehicles.Vehicle;
import com.rental.exceptions.InvalidInputException;
import com.rental.utils.ValidationUtils;
import java.time.LocalDateTime;

public class RentalTransaction {
    private String transactionId;
    private Customer customer;
    private Vehicle vehicle;
    private int rentalDays;
    private double totalCost;
    private LocalDateTime rentalDate;

    public RentalTransaction(Customer customer, Vehicle vehicle, int days) throws InvalidInputException {
        ValidationUtils.validateRentalDays(days);
        this.transactionId = generateTransactionId();
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentalDays = days;
        this.totalCost = calculateTotalCost();
        this.rentalDate = LocalDateTime.now();
    }

    private String generateTransactionId() {
        return "TR" + System.currentTimeMillis();
    }

    private double calculateTotalCost() {
        double baseCost = vehicle.calculateRentalCost(rentalDays);
        double discount = customer.getLoyaltyProgram().getDiscount();
        return baseCost * (1 - discount);
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public LocalDateTime getRentalDate() {
        return rentalDate;
    }
}