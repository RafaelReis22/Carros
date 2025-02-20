package com.automotive.simulator.infrastructure;

import com.automotive.simulator.domain.entities.BaseCar;
import com.automotive.simulator.domain.entities.VehicleFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DisplayName("InMemoryVehicleRepository — persistência em memória")
class InMemoryVehicleRepositoryTest {

    private InMemoryVehicleRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryVehicleRepository();
    }

    @Test
    @DisplayName("Repositório começa vazio")
    void repositorioComecaVazio() {
        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    @DisplayName("save adiciona veículo ao repositório")
    void saveAdicionaVeiculo() {
        repository.save(VehicleFactory.createEconomy());
        assertThat(repository.findAll()).hasSize(1);
    }

    @Test
    @DisplayName("Múltiplos veículos podem ser salvos")
    void multiplosVeiculosSalvos() {
        repository.save(VehicleFactory.createSport());
        repository.save(VehicleFactory.createSUV());
        repository.save(VehicleFactory.createEconomy());
        assertThat(repository.findAll()).hasSize(3);
    }

    @Test
    @DisplayName("findAll retorna cópia defensiva — alterações externas não afetam estado")
    void findAllRetornaCopiaDefensiva() {
        repository.save(VehicleFactory.createSport());
        repository.findAll().clear();
        assertThat(repository.findAll()).hasSize(1);
    }

    @Test
    @DisplayName("findById encontra veículo por nome de classe")
    void findByIdEncontraVeiculoPorNome() {
        repository.save(VehicleFactory.createEconomy());
        Optional<BaseCar> result = repository.findById("EconomyCar");
        assertThat(result).isPresent();
        assertThat(result.get()).isInstanceOf(
                com.automotive.simulator.domain.entities.EconomyCar.class);
    }

    @Test
    @DisplayName("findById é case-insensitive")
    void findByIdCaseInsensitive() {
        repository.save(VehicleFactory.createSport());
        assertThat(repository.findById("sportcar")).isPresent();
        assertThat(repository.findById("SPORTCAR")).isPresent();
    }

    @Test
    @DisplayName("findById retorna empty para nome inexistente")
    void findByIdRetornaEmptyParaNomeInexistente() {
        assertThat(repository.findById("NaoExiste")).isEmpty();
    }
}
