package com.automotive.simulator.domain.entities;

/** 
 * Fábrica para a criação de instâncias de veículos.
 * Parte do padrão Factory.
 */
public class VehicleFactory {
    public static BaseCar createSport() {
        return new SportCar(new SimpleConsumption(5.0), 60.0);
    }

    public static BaseCar createUtility() {
        return new UtilityCar(new SimpleConsumption(10.0), 80.0);
    }

    public static BaseCar createSUV() {
        return new SUV(new SimpleConsumption(8.0), 65.0);
    }

    public static BaseCar createSUVFlex(FuelType type) {
        return new SUVFlex(type, new FlexConsumption(type), 65.0);
    }

    public static BaseCar createEconomy() {
        return new EconomyCar(new SimpleConsumption(15.0), 50.0);
    }
}
