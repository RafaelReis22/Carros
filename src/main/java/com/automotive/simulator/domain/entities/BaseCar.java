package com.automotive.simulator.domain.entities;

import com.automotive.simulator.domain.exceptions.BusinessRuleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Representa a base para todos os veículos no simulador.
 * Utiliza o padrão Strategy para o cálculo de consumo de combustível.
 */
public abstract class BaseCar {
    private static final Logger logger = LoggerFactory.getLogger(BaseCar.class);

    protected FuelType fuelType;
    protected ConsumptionStrategy consumptionStrategy;
    protected double tankCapacity;
    protected double currentFuelLevel;

    protected BaseCar(FuelType fuelType, ConsumptionStrategy strategy, double tankCapacity) {
        this.fuelType = fuelType;
        this.consumptionStrategy = strategy;
        this.tankCapacity = tankCapacity;
        this.currentFuelLevel = tankCapacity; // Abastecido automaticamente ao criar
    }

    /**
     * Abastece o veículo com uma quantidade de litros.
     */
    public void fuel(double liters) {
        if (liters <= 0) {
            throw new BusinessRuleException("A quantidade de combustível deve ser positiva.");
        }
        if (currentFuelLevel + liters > tankCapacity) {
            logger.info("Tanque cheio para o veículo {}.", this.getClass().getSimpleName());
            currentFuelLevel = tankCapacity;
        } else {
            currentFuelLevel += liters;
            logger.info("Abastecido {}L. Nível atual: {}L.", liters, currentFuelLevel);
        }
    }

    /**
     * Calcula a autonomia restante baseada na estratégia de consumo.
     */
    public double calculateAutonomy() {
        return currentFuelLevel * consumptionStrategy.getConsumptionKmL();
    }

    /**
     * Tenta rodar uma distância em km.
     */
    public void run(double km) {
        double efficiency = consumptionStrategy.getConsumptionKmL();
        double neededFuel = km / efficiency;
        if (neededFuel > currentFuelLevel) {
            throw new BusinessRuleException("Combustível insuficiente para rodar " + km + "km.");
        }
        currentFuelLevel -= neededFuel;
        logger.info("{} rodou {}km. Combustível restante: {}L.", this.getClass().getSimpleName(), km, currentFuelLevel);
    }

    @Override
    public String toString() {
        return String.format("%s [Combustível=%s, Estratégia=%s, Tanque=%.1f/%.1f L, Autonomia=%.1f km]",
                this.getClass().getSimpleName(), fuelType.getDescription(), 
                consumptionStrategy.getClass().getSimpleName(), currentFuelLevel, 
                tankCapacity, calculateAutonomy());
    }

    // Getters
    public FuelType getFuelType() { return fuelType; }
    public double getTankCapacity() { return tankCapacity; }
    public double getCurrentFuelLevel() { return currentFuelLevel; }
}
