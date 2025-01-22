package com.automotive.simulator.domain.entities;

import com.automotive.simulator.domain.exceptions.BusinessRuleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("BaseCar — regras de negócio compartilhadas")
class BaseCarTest {

    @Test
    @DisplayName("Tanque começa cheio ao criar o veículo")
    void tanqueComecaCheioCriarVeiculo() {
        BaseCar car = VehicleFactory.createEconomy();
        assertThat(car.getCurrentFuelLevel()).isEqualTo(car.getTankCapacity());
    }

    @Test
    @DisplayName("Abastecer com valor positivo aumenta nível de combustível")
    void abastecerPositivoAumentaNivel() {
        BaseCar car = VehicleFactory.createSport();
        car.run(100); // consome combustível
        double antes = car.getCurrentFuelLevel();
        car.fuel(5.0);
        assertThat(car.getCurrentFuelLevel()).isGreaterThan(antes);
    }

    @Test
    @DisplayName("Abastecer além da capacidade limita ao tanque cheio")
    void abastecerAlemCapacidadeLimitaTanque() {
        BaseCar car = VehicleFactory.createEconomy();
        car.run(10); // consome pouco
        car.fuel(car.getTankCapacity() * 2); // tenta encher mais do que cabe
        assertThat(car.getCurrentFuelLevel()).isEqualTo(car.getTankCapacity());
    }

    @Test
    @DisplayName("Abastecer com valor zero lança BusinessRuleException")
    void abastecerZeroLancaExcecao() {
        BaseCar car = VehicleFactory.createSport();
        assertThatThrownBy(() -> car.fuel(0))
                .isInstanceOf(BusinessRuleException.class);
    }

    @Test
    @DisplayName("Abastecer com valor negativo lança BusinessRuleException")
    void abastecerNegativoLancaExcecao() {
        BaseCar car = VehicleFactory.createSport();
        assertThatThrownBy(() -> car.fuel(-5))
                .isInstanceOf(BusinessRuleException.class);
    }

    @Test
    @DisplayName("Rodar com combustível suficiente reduz nível corretamente")
    void rodarReduzCombustivel() {
        BaseCar car = VehicleFactory.createEconomy(); // 15 km/L, tanque 50L
        double antes = car.getCurrentFuelLevel();
        car.run(15); // consome 1L
        assertThat(car.getCurrentFuelLevel()).isCloseTo(antes - 1.0, within(0.001));
    }

    @Test
    @DisplayName("Rodar sem combustível suficiente lança BusinessRuleException")
    void rodarSemCombustivelLancaExcecao() {
        BaseCar car = VehicleFactory.createSport(); // 5 km/L, tanque 60L → autonomia 300km
        assertThatThrownBy(() -> car.run(999))
                .isInstanceOf(BusinessRuleException.class)
                .hasMessageContaining("Combustível insuficiente");
    }

    @Test
    @DisplayName("calculateAutonomy retorna distância máxima com tanque atual")
    void calculateAutonomyRetornaDistanciaCorreta() {
        BaseCar car = VehicleFactory.createEconomy(); // 15 km/L, tanque 50L
        assertThat(car.calculateAutonomy()).isCloseTo(750.0, within(0.001));
    }

    @Test
    @DisplayName("toString contém nome da classe e tipo de combustível")
    void toStringContemInformacoesEssenciais() {
        BaseCar car = VehicleFactory.createEconomy();
        assertThat(car.toString())
                .contains("EconomyCar")
                .contains("Gasolina");
    }
}
