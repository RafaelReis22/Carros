package models;

public abstract class Carro {
    protected String combustivel;
    protected double consumoKmLitro;
    protected double capacidadeTanque;
    protected double nivelTanque;
    
    public Carro(String combustivel, double consumoKmLitro, double capacidadeTanque) {
        this.combustivel = combustivel;
        this.consumoKmLitro = consumoKmLitro;
        this.capacidadeTanque = capacidadeTanque;
        this.nivelTanque = capacidadeTanque;
    }

    public void abastecer(double litros) {
        if (litros <= 0) {
            System.out.println("Quantidade de combustível inválida.");
        } else if (nivelTanque + litros > capacidadeTanque) {
            System.out.println("Tanque cheio.");
            nivelTanque = capacidadeTanque;
        } else {
            nivelTanque += litros;
        }
    }

    public double calcularAutonomia() {
        return nivelTanque * consumoKmLitro;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [combustível=" + combustivel + ", consumoKmLitro=" + consumoKmLitro + 
               ", capacidadeTanque=" + capacidadeTanque + ", nívelTanque=" + nivelTanque + "]";
    }
}

