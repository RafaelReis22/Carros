package com.automotive.simulator.domain.entities;

/** 
 * Tipos de combustíveis suportados.
 */
public enum FuelType {
    GASOLINE("Gasolina"),
    ETHANOL("Álcool");

    private final String description;

    FuelType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static FuelType fromDescription(String description) {
        for (FuelType type : FuelType.values()) {
            if (type.getDescription().equalsIgnoreCase(description)) {
                return type;
            }
        }
        return GASOLINE; // Padrão caso não encontrado
    }
}
