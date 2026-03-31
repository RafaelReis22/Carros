package com.automotive.simulator.domain.entities;

public class UtilityCar extends BaseCar {
    public UtilityCar(ConsumptionStrategy strategy, double tankCapacity) {
        super(FuelType.GASOLINE, strategy, tankCapacity);
    }
}
