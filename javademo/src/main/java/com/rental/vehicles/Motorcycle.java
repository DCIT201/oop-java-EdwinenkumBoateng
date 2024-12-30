package com.rental.vehicles;

public class Motorcycle extends Vehicle {

    private int engineCapacity;

    public Motorcycle(String vehicleId, String model, double baseRentalRate, int engineCapacity) {
        super(vehicleId, model, baseRentalRate);
        this.engineCapacity = engineCapacity;
    }

    @Override
    public double calculateRentalCost(int days) {
        double cost = super.getBaseRentalRate() * days;
        // Add surcharge for high engine capacity motorcycles
        if (engineCapacity > 500) {
            cost += days * 15;
        }
        return cost;
    }

    @Override
    public boolean isAvailableForRental() {
        return isAvailable;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    @Override
    public String toString() {
        return "Motorcycle{" +
                "vehicleId='" + getVehicleId() + '\'' +
                ", model='" + getModel() + '\'' +
                ", baseRentalRate=" + getBaseRentalRate() +
                ", engineCapacity=" + engineCapacity +
                ", isAvailable=" + isAvailable +
                '}';
    }
}