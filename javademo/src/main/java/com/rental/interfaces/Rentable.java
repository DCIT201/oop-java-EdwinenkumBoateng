package com.rental.interfaces;

import com.rental.customer.Customer;

public interface Rentable {

    double calculateRentalCost(int days);
    boolean isAvailableForRental();
    void rent(Customer customer, int days);
    void returnVehicle();
}