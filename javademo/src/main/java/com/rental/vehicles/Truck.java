package com.rental.vehicles;

public class Truck extends Vehicle {

    private double weightCapacity;

    public Truck(String vehicleId, String model, double baseRentalRate, double weightCapacity) {
        super(vehicleId, model, baseRentalRate);
        this.weightCapacity = weightCapacity;
    }

    @Override
    public double calculateRentalCost(int days) {
        double cost = super.getBaseRentalRate() * days;
        // Add surcharge for trucks based on weight capacity
        if (weightCapacity > 3000) {
            cost += days * 25; // Heavy truck surcharge
        } else {
            cost += days * 15; // Standard truck surcharge
        }
        return cost;
    }

    @Override
    public boolean isAvailableForRental() {
        return isAvailable;
    }

    public double getWeightCapacity() {
        return weightCapacity;
    }

    public void setWeightCapacity(double weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "vehicleId='" + getVehicleId() + '\'' +
                ", model='" + getModel() + '\'' +
                ", baseRentalRate=" + getBaseRentalRate() +
                ", weightCapacity=" + weightCapacity +
                ", isAvailable=" + isAvailable +
                '}';
    }
}