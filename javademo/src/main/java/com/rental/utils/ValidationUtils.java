package com.rental.utils;

import com.rental.exceptions.InvalidInputException;

public class ValidationUtils {
    public static void validateRentalDays(int days) throws InvalidInputException {
        if (days <= 0) {
            throw new InvalidInputException("Rental days must be greater than 0");
        }
    }

    public static void validateCustomerDetails(String customerId, String name) throws InvalidInputException {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new InvalidInputException("Customer ID cannot be empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("Customer name cannot be empty");
        }
    }

    public static void validateVehicleDetails(String vehicleId, String model, double rate) throws InvalidInputException {
        if (vehicleId == null || vehicleId.trim().isEmpty()) {
            throw new InvalidInputException("Vehicle ID cannot be empty");
        }
        if (model == null || model.trim().isEmpty()) {
            throw new InvalidInputException("Vehicle model cannot be empty");
        }
        if (rate <= 0) {
            throw new InvalidInputException("Base rental rate must be greater than 0");
        }
    }
}
