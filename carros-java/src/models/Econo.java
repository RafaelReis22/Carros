package models;

public class Econo extends Carro {
    private double kmRodados = 0;

    public Econo() {
        super("Gasolina", 20, 55);
    }

    public void rodar(double km) {
        kmRodados += km;
        if (kmRodados >= 5000) {
            consumoKmLitro = Math.max(consumoKmLitro - 1, 10);
        }
    }
}

