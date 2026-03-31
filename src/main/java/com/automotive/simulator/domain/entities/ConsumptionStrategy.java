package com.automotive.simulator.domain.entities;

/** 
 * Estratégia de consumo de combustível para veículos.
 * Parte do padrão Strategy.
 */
public interface ConsumptionStrategy {
    double getConsumptionKmL();
}
