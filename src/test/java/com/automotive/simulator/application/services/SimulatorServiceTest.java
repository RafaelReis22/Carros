package com.automotive.simulator.application.services;

import com.automotive.simulator.domain.entities.VehicleFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Testes de Unidade - SimulatorService")
class SimulatorServiceTest {
    private SimulatorService simulator;

    @BeforeEach
    void setUp() {
        simulator = new SimulatorService();
    }

    @Test
    @DisplayName("Deve adicionar carro à frota")
    void testAddCar() {
        simulator.addCarToFleet(VehicleFactory.createSport());
        assertEquals(1, simulator.getFleet().size());
    }
}
