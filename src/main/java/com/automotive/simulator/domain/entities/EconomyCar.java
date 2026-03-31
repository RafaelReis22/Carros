package com.automotive.simulator.domain.entities;

public class EconomyCar extends BaseCar {
    public EconomyCar(ConsumptionStrategy strategy, double tankCapacity) {
        super(FuelType.GASOLINE, strategy, tankCapacity);
    }
}
