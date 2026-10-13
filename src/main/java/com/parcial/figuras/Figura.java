package com.parcial.figuras;

import java.util.Objects;

public abstract class Figura {
    protected final Punto posicion;

    protected Figura(Punto posicion) {
        this.posicion = Objects.requireNonNull(posicion, "La posición no puede ser nula");
    }

    public void desplazar(double deltaX, double deltaY) {
        posicion.desplazar(deltaX, deltaY);
    }

    protected void validarDimension(double valor) {
        if (!(valor > 0)) {
            throw new IllegalArgumentException(
                    "Las dimensiones deben ser mayores que cero. Valor recibido: " + valor);
        }
    }

    /** Devuelve una copia para que nadie modifique la posición desde fuera. */
    public Punto getPosicion() {
        return new Punto(posicion.getX(), posicion.getY());
    }

    public abstract String getNombre();

    public abstract String describirDimensiones();

    public abstract void escalar(double factor);

    public abstract double calcularArea();

    public abstract double calcularPerimetro();

    public abstract double dimensionar();

    public String obtenerInformacion() {
        return String.format("Tipo: %s%n", getNombre())
                + String.format("Posición: (%.2f, %.2f)%n", posicion.getX(), posicion.getY())
                + String.format("Dimensiones: %s%n", describirDimensiones())
                + String.format("Área: %.2f%n", calcularArea())
                + String.format("Perímetro: %.2f%n", calcularPerimetro())
                + String.format("Dimensionar: %.2f%n", dimensionar());
    }

    public void mostrarInformacion() {
        System.out.print(obtenerInformacion());
        System.out.println("--------------------------------------");
    }
}