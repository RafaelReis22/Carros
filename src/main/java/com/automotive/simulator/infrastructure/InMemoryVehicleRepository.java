package com.automotive.simulator.infrastructure;

import com.automotive.simulator.domain.entities.BaseCar;
import com.automotive.simulator.domain.entities.VehicleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementação em memória do repositório de veículos.
 * Parte da camada de infraestrutura.
 */
public class InMemoryVehicleRepository implements VehicleRepository {
    private final List<BaseCar> storage = new ArrayList<>();

    @Override
    public void save(BaseCar car) {
        storage.add(car);
    }

    @Override
    public List<BaseCar> findAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public Optional<BaseCar> findById(String name) {
        return storage.stream()
                .filter(c -> c.getClass().getSimpleName().equalsIgnoreCase(name))
                .findFirst();
    }
}
