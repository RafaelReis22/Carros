package com.automotive.simulator.domain.entities;

public class SUV extends BaseCar {
    public SUV(ConsumptionStrategy strategy, double tankCapacity) {
        super(FuelType.GASOLINE, strategy, tankCapacity);
    }
}
