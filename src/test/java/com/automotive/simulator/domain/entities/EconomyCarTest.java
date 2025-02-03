package com.automotive.simulator.domain.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("EconomyCar — eficiência e consumo econômico")
class EconomyCarTest {

    @Test
    @DisplayName("EconomyCar usa combustível GASOLINE")
    void usaCombustivelGasolina() {
        BaseCar car = VehicleFactory.createEconomy();
        assertThat(car.getFuelType()).isEqualTo(FuelType.GASOLINE);
    }

    @Test
    @DisplayName("EconomyCar tem capacidade de tanque 50L")
    void temCapacidadeTanque50L() {
        BaseCar car = VehicleFactory.createEconomy();
        assertThat(car.getTankCapacity()).isEqualTo(50.0);
    }

    @Test
    @DisplayName("EconomyCar tem consumo de 15 km/L")
    void temConsumo15KmL() {
        BaseCar car = VehicleFactory.createEconomy();
        // Tanque cheio 50L × 15 km/L = 750 km de autonomia
        assertThat(car.calculateAutonomy()).isCloseTo(750.0, within(0.001));
    }

    @Test
    @DisplayName("EconomyCar tem maior autonomia que SportCar")
    void temMaiorAutonomiaQueSportCar() {
        BaseCar economy = VehicleFactory.createEconomy();
        BaseCar sport = VehicleFactory.createSport();
        assertThat(economy.calculateAutonomy()).isGreaterThan(sport.calculateAutonomy());
    }

    @Test
    @DisplayName("Autonomia reduz proporcionalmente após rodar")
    void autonomiaReduzAposRodar() {
        BaseCar car = VehicleFactory.createEconomy(); // 15 km/L
        double autonomiaInicial = car.calculateAutonomy();
        car.run(150); // consome 10L
        assertThat(car.calculateAutonomy()).isCloseTo(autonomiaInicial - 150, within(0.001));
    }
}
