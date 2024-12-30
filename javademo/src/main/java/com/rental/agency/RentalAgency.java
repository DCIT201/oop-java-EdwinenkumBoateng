package com.rental.agency;

import com.rental.customer.Customer;
import com.rental.vehicles.Vehicle;
import com.rental.exceptions.VehicleNotFoundException;
import com.rental.exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RentalAgency {
    private List<Vehicle> vehicleFleet;
    private List<RentalTransaction> transactions;

    public RentalAgency() {
        this.vehicleFleet = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleFleet.add(vehicle);
    }

    public void removeVehicle(String vehicleId) throws VehicleNotFoundException {
        Vehicle vehicle = findVehicle(vehicleId);
        vehicleFleet.remove(vehicle);
    }

    public RentalTransaction rentVehicle(String vehicleId, Customer customer, int days) 
            throws VehicleNotFoundException, InvalidInputException {
        Vehicle vehicle = findVehicle(vehicleId);
        
        if (!vehicle.isAvailableForRental()) {
            throw new InvalidInputException("Vehicle is not available for rent");
        }

        RentalTransaction transaction = new RentalTransaction(customer, vehicle, days);
        vehicle.setAvailable(false);
        transactions.add(transaction);
        customer.addRentalTransaction(transaction);
        
        return transaction;
    }

    public void returnVehicle(String vehicleId) throws VehicleNotFoundException {
        Vehicle vehicle = findVehicle(vehicleId);
        vehicle.setAvailable(true);
    }

    private Vehicle findVehicle(String vehicleId) throws VehicleNotFoundException {
        return vehicleFleet.stream()
                .filter(v -> v.getVehicleId().equals(vehicleId))
                .findFirst()
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found: " + vehicleId));
    }

    public List<Vehicle> getAvailableVehicles() {
        return vehicleFleet.stream()
                .filter(Vehicle::isAvailableForRental)
                .collect(Collectors.toList());
    }

    public List<RentalTransaction> getTransactionHistory() {
        return new ArrayList<>(transactions);
    }
}