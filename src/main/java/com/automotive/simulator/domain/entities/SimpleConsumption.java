package com.automotive.simulator.domain.entities;

/** 
 * Estratégia simples de consumo de combustível.
 */
public record SimpleConsumption(double consumptionKmL) implements ConsumptionStrategy {
    @Override
    public double getConsumptionKmL() {
        return consumptionKmL;
    }
}
