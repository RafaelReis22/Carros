package com.automotive.simulator.domain.entities;

import java.util.List;
import java.util.Optional;

/** 
 * Interface para persistência de veículos.
 * Parte do padrão Repository.
 */
public interface VehicleRepository {
    void save(BaseCar car);
    List<BaseCar> findAll();
    Optional<BaseCar> findById(String id);
}
