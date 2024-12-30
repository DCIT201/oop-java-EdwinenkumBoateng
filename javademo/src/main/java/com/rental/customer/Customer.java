package com.rental.customer;

import com.rental.agency.RentalTransaction;
import com.rental.exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {
    private final String customerId;
    private final String name;
    private final List<RentalTransaction> rentalHistory;
    private final LoyaltyProgram loyaltyProgram;

    public Customer(String customerId, String name) throws InvalidInputException {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new InvalidInputException("Customer ID cannot be null or empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("Customer name cannot be null or empty");
        }
        this.customerId = customerId;
        this.name = name;
        this.rentalHistory = new ArrayList<>();
        this.loyaltyProgram = new LoyaltyProgram();
    }

    public void addRentalTransaction(RentalTransaction transaction) {
        rentalHistory.add(transaction);
        loyaltyProgram.addPoints((int)transaction.getTotalCost());
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public List<RentalTransaction> getRentalHistory() {
        return Collections.unmodifiableList(rentalHistory);
    }

    public LoyaltyProgram getLoyaltyProgram() {
        return loyaltyProgram;
    }
}