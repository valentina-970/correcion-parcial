package com.parcial.figuras;

public class ComparadorFiguras {

    public void comparar(Figura f1, Figura f2) {
        String nombre1 = f1.getClass().getSimpleName();
        String nombre2 = f2.getClass().getSimpleName();

        if (f1.getClass() != f2.getClass()) {
            System.out.println("No se pueden comparar figuras de distinto tipo: "
                    + nombre1 + " y " + nombre2);
            return;
        }

        double d1 = f1.dimensionar();
        double d2 = f2.dimensionar();
        int resultado = Double.compare(d1, d2);

        System.out.printf("Comparando dos %s (%.2f vs %.2f): ", nombre1, d1, d2);
        if (resultado > 0) {
            System.out.println("la primera es MAYOR que la segunda");
        } else if (resultado < 0) {
            System.out.println("la primera es MENOR que la segunda");
        } else {
            System.out.println("son IGUALES");
        }
    }
}