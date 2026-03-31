package com.automotive.simulator.application.services;

import com.automotive.simulator.domain.entities.BaseCar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Serviço responsável pela orquestração da frota de veículos.
 * Parte da camada de aplicação.
 */
public class SimulatorService {
    private static final Logger logger = LoggerFactory.getLogger(SimulatorService.class);
    private final List<BaseCar> fleet = new ArrayList<>();

    public void addCarToFleet(BaseCar car) {
        fleet.add(car);
        logger.info("Novo {} adicionado à frota.", car.getClass().getSimpleName());
    }

    public void runFullSimulation() {
        logger.info("=== Iniciando Simulação Global da Frota ===");
        fleet.forEach(car -> {
            logger.info("Processando: {}", car);
            System.out.println("Resultado: " + car);
        });
        logger.info("=== Simulação Finalizada ===");
    }

    public List<BaseCar> getFleet() {
        return new ArrayList<>(fleet);
    }
}
