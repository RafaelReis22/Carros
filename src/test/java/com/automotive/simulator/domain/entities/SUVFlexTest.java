package com.automotive.simulator.domain.entities;

import com.automotive.simulator.domain.exceptions.BusinessRuleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes de Unidade - SUV Flex")
class SUVFlexTest {

    @Test
    @DisplayName("Deve calcular autonomia corretamente com Gasolina")
    void testGasolineAutonomy() {
        BaseCar suv = VehicleFactory.createSUVFlex(FuelType.GASOLINE);
        // Consumo 8.0 * Tanque 65.0
        assertEquals(65 * 8.0, suv.calculateAutonomy());
    }

    @Test
    @DisplayName("Deve calcular autonomia corretamente com Álcool")
    void testEthanolAutonomy() {
        BaseCar suv = VehicleFactory.createSUVFlex(FuelType.ETHANOL);
        // Consumo 6.0 * Tanque 65.0
        assertEquals(65 * 6.0, suv.calculateAutonomy());
    }

    @Test
    @DisplayName("Deve lançar exceção ao abastecer com valor negativo")
    void testFuelingError() {
        BaseCar suv = VehicleFactory.createSUVFlex(FuelType.GASOLINE);
        assertThrows(BusinessRuleException.class, () -> suv.fuel(-10));
    }
}
