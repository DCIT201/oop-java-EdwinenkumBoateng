package com.rental.customer;

enum CustomerTier {
    STANDARD,
    GOLD,
    PLATINUM
}

public class LoyaltyProgram {
    private int points;
    private CustomerTier tier;

    public LoyaltyProgram() {
        this.points = 0;
        this.tier = CustomerTier.STANDARD;
    }

    public int getPoints() {
        return points;
    }

    public CustomerTier getTier() {
        return tier;
    }

    public void addPoints(int points) {
        this.points += points;
        updateTier();
    }

    private void updateTier() {
        if (points >= 3000) {
            tier = CustomerTier.PLATINUM;
        } else if (points >= 1000) {
            tier = CustomerTier.GOLD;
        }
    }

    public boolean redeemPoints(int amount) {
        if (amount <= points) {
            points -= amount;
            updateTier();
            return true;
        }
        return false;
    }

    public double getDiscount() {
        // return the discount value, for example, 0.1 for 10% discount
        return 0.1;
    }
}
