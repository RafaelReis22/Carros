package com.automotive.simulator.presentation;

import com.automotive.simulator.domain.entities.*;
import com.automotive.simulator.infrastructure.InMemoryVehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Ponto de entrada da aplicação.
 * Fornece um menu interativo para o usuário.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final VehicleRepository repository = new InMemoryVehicleRepository();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        logger.info("Iniciando Simulador Automotivo Especialista+...");
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addCar();
                case "2" -> listCars();
                case "3" -> fuelCar();
                case "4" -> runSimulation();
                case "0" -> running = false;
                default -> System.out.println("Opção inválida.");
            }
        }
        logger.info("Saindo do Simulador. Dirija com segurança!");
    }

    private static void printMenu() {
        System.out.println("\n--- SIMULADOR DE CARROS ESPECIALISTA+ ---");
        System.out.println("1. Adicionar Veículo (Padrão Factory)");
        System.out.println("2. Listar Frota (Padrão Repository)");
        System.out.println("3. Abastecer Veículo");
        System.out.println("4. Rodar Simulação (Padrão Strategy)");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void addCar() {
        System.out.println("Selecione o tipo: 1.Esportivo 2.Utilitário 3.SUV 4.SUVFlex 5.Econômico");
        String type = scanner.nextLine();
        BaseCar car = switch (type) {
            case "1" -> VehicleFactory.createSport();
            case "2" -> VehicleFactory.createUtility();
            case "3" -> VehicleFactory.createSUV();
            case "4" -> VehicleFactory.createSUVFlex(FuelType.ETHANOL);
            case "5" -> VehicleFactory.createEconomy();
            default -> null;
        };
        if (car != null) {
            repository.save(car);
            System.out.println("Veículo adicionado com sucesso!");
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void listCars() {
        System.out.println("\n=== Listagem de Frota ===");
        repository.findAll().forEach(System.out::println);
    }

    private static void fuelCar() {
        System.out.print("Digite o nome da classe do carro: ");
        String name = scanner.nextLine();
        repository.findById(name).ifPresentOrElse(car -> {
            System.out.print("Quantidade para abastecer: ");
            try {
                double amount = Double.parseDouble(scanner.nextLine());
                car.fuel(amount);
                System.out.println("Abastecido com sucesso!");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }, () -> System.out.println("Veículo não encontrado."));
    }

    private static void runSimulation() {
        System.out.print("Distância para rodar (km): ");
        try {
            double dist = Double.parseDouble(scanner.nextLine());
            repository.findAll().forEach(car -> {
                try {
                    car.run(dist);
                    System.out.println(car.getClass().getSimpleName() + " rodou com sucesso.");
                } catch (Exception e) {
                    System.out.println(car.getClass().getSimpleName() + " falhou: " + e.getMessage());
                }
            });
        } catch (Exception e) {
            System.out.println("Valor inválido.");
        }
    }
}
