package com.automotive.simulator.domain.entities;

public class FlexConsumption implements ConsumptionStrategy {
    private final FuelType currentFuel;
    private static final double GASOLINE_CONSUMPTION = 8.0;
    private static final double ETHANOL_CONSUMPTION = 6.0;

    public FlexConsumption(FuelType fuel) {
        this.currentFuel = fuel;
    }

    @Override
    public double getConsumptionKmL() {
        return (currentFuel == FuelType.GASOLINE) ? GASOLINE_CONSUMPTION : ETHANOL_CONSUMPTION;
    }
}
