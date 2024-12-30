package com.rental.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoyaltyProgramTest {
    private LoyaltyProgram loyaltyProgram;

    @BeforeEach
    void setUp() {
        loyaltyProgram = new LoyaltyProgram();
    }

    @Test
    void testInitialState() {
        assertEquals(0, loyaltyProgram.getPoints());
        assertEquals(CustomerTier.STANDARD, loyaltyProgram.getTier());
    }

    @Test
    void testAddPoints() {
        loyaltyProgram.addPoints(100);
        assertEquals(100, loyaltyProgram.getPoints());
    }

    @Test
    void testTierUpgrade() {
        // Test GOLD upgrade
        loyaltyProgram.addPoints(1000);
        assertEquals(CustomerTier.GOLD, loyaltyProgram.getTier());
        
        // Test PLATINUM upgrade
        loyaltyProgram.addPoints(2000);
        assertEquals(CustomerTier.PLATINUM, loyaltyProgram.getTier());
    }

    @Test
    void testRedeemPoints() {
        loyaltyProgram.addPoints(500);
        assertTrue(loyaltyProgram.redeemPoints(200));
        assertEquals(300, loyaltyProgram.getPoints());
    }

    @Test
    void testInvalidRedemption() {
        loyaltyProgram.addPoints(100);
        assertFalse(loyaltyProgram.redeemPoints(200));
        assertEquals(100, loyaltyProgram.getPoints());
    }
}
