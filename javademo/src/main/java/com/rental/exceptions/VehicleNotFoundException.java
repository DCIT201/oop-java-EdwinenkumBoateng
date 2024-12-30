package com.rental.exceptions;

public class VehicleNotFoundException extends RentalException {
    public VehicleNotFoundException(String message) {
        super(message);
    }
}
