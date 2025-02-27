import models.*;

public class Main {
    public static void main(String[] args) {
        Carro esportivo = new Esportivo();
        esportivo.abastecer(10);
        System.out.println(esportivo);

        Carro utilitario = new Utilitario();
        utilitario.abastecer(20);
        System.out.println(utilitario);

        Carro suv = new SUV();
        suv.abastecer(30);
        System.out.println(suv);

        Carro suvFlex = new SUVFlex("Álcool");
        suvFlex.abastecer(40);
        System.out.println(suvFlex);

        Econo econo = new Econo();
        econo.rodar(6000);
        System.out.println(econo);
    }
}

