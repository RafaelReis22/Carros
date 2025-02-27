package models;

public class SUVFlex extends Carro {
    private double consumoKmLitroAlcool = 6;

    public SUVFlex(String combustivel) {
        super(combustivel, combustivel.equals("Gasolina") ? 8 : 6, 65);
    }

    @Override
    public double calcularAutonomia() {
        return nivelTanque * (combustivel.equals("Gasolina") ? consumoKmLitro : consumoKmLitroAlcool);
    }
}

