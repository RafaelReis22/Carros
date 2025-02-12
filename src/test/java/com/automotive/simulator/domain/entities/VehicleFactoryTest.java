package com.automotive.simulator.domain.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("VehicleFactory — criação de veículos por tipo")
class VehicleFactoryTest {

    @Test
    @DisplayName("createSport retorna SportCar não nulo")
    void createSportRetornaSportCar() {
        assertThat(VehicleFactory.createSport()).isInstanceOf(SportCar.class);
    }

    @Test
    @DisplayName("createUtility retorna UtilityCar não nulo")
    void createUtilityRetornaUtilityCar() {
        assertThat(VehicleFactory.createUtility()).isInstanceOf(UtilityCar.class);
    }

    @Test
    @DisplayName("createSUV retorna SUV não nulo")
    void createSUVRetornaSUV() {
        assertThat(VehicleFactory.createSUV()).isInstanceOf(SUV.class);
    }

    @Test
    @DisplayName("createEconomy retorna EconomyCar não nulo")
    void createEconomyRetornaEconomyCar() {
        assertThat(VehicleFactory.createEconomy()).isInstanceOf(EconomyCar.class);
    }

    @Test
    @DisplayName("createSUVFlex com GASOLINE retorna SUVFlex com combustível correto")
    void createSUVFlexGasolina() {
        BaseCar car = VehicleFactory.createSUVFlex(FuelType.GASOLINE);
        assertThat(car).isInstanceOf(SUVFlex.class);
        assertThat(car.getFuelType()).isEqualTo(FuelType.GASOLINE);
    }

    @Test
    @DisplayName("createSUVFlex com ETHANOL retorna SUVFlex com combustível correto")
    void createSUVFlexEtanol() {
        BaseCar car = VehicleFactory.createSUVFlex(FuelType.ETHANOL);
        assertThat(car.getFuelType()).isEqualTo(FuelType.ETHANOL);
    }

    @Test
    @DisplayName("Cada chamada cria nova instância independente")
    void cadaChamadaCriaNovaInstancia() {
        BaseCar car1 = VehicleFactory.createEconomy();
        BaseCar car2 = VehicleFactory.createEconomy();
        car1.run(100);
        assertThat(car1.getCurrentFuelLevel()).isNotEqualTo(car2.getCurrentFuelLevel());
    }
}
