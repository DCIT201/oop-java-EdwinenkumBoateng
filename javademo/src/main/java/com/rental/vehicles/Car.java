package com.rental.vehicles;

public class Car extends Vehicle {

    private boolean hasAC;

    public Car(String vehicleId, String model, double baseRentalRate, boolean hasAC) {
        super(vehicleId, model, baseRentalRate);
        this.hasAC = hasAC;
    }

    @Override
    public double calculateRentalCost(int days) {
        double cost = super.getBaseRentalRate() * days;
        if (hasAC) {
            cost += days * 10; // Extra charge for AC
        }
        return cost;
    }

    @Override
    public boolean isAvailableForRental() {
        return isAvailable;
    }

    public boolean hasAC() {
        return hasAC;
    }

    public void setHasAC(boolean hasAC) {
        this.hasAC = hasAC;
    }

    @Override
    public String toString() {
        return "Car{" +
                "vehicleId='" + getVehicleId() + '\'' +
                ", model='" + getModel() + '\'' +
                ", baseRentalRate=" + getBaseRentalRate() +
                ", hasAC=" + hasAC +
                ", isAvailable=" + isAvailable +
                '}';
    }
}