package com.parcial.figuras;

public class ComparadorFiguras {

    public boolean sonComparables(Figura primera, Figura segunda) {
        return primera.getClass() == segunda.getClass();
    }

    public String obtenerResultado(Figura primera, Figura segunda) {
        if (!sonComparables(primera, segunda)) {
            return "No se pueden comparar figuras de distinto tipo: "
                    + primera.getNombre() + " y " + segunda.getNombre();
        }
        double valorPrimera = primera.dimensionar();
        double valorSegunda = segunda.dimensionar();
        return String.format("Comparando dos %s (%.2f vs %.2f): %s",
                primera.getNombre(), valorPrimera, valorSegunda,
                describirComparacion(Double.compare(valorPrimera, valorSegunda)));
    }

    public void comparar(Figura primera, Figura segunda) {
        System.out.println(obtenerResultado(primera, segunda));
    }

    private String describirComparacion(int resultado) {
        if (resultado > 0) {
            return "la primera es MAYOR que la segunda";
        }
        if (resultado < 0) {
            return "la primera es MENOR que la segunda";
        }
        return "son IGUALES";
    }
}