package com.automotive.simulator.domain.entities;

public class SportCar extends BaseCar {
    public SportCar(ConsumptionStrategy strategy, double tankCapacity) {
        super(FuelType.GASOLINE, strategy, tankCapacity);
    }
}
