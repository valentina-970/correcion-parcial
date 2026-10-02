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
    }
}