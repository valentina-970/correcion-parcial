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

    protected abstract String describirDimensiones();

    public abstract void escalar(double factor);

    public abstract double calcularArea();

    public abstract double calcularPerimetro();

    public abstract double dimensionar();

    public void mostrarInformacion() {
        System.out.println("Tipo: " + getClass().getSimpleName());
        System.out.printf("Posición: (%.2f, %.2f)%n", posicion.getX(), posicion.getY());
        System.out.println("Dimensiones: " + describirDimensiones());
        System.out.printf("Área: %.2f%n", calcularArea());
        System.out.printf("Perímetro: %.2f%n", calcularPerimetro());
        System.out.printf("Dimensionar: %.2f%n", dimensionar());
        System.out.println("--------------------------------------");
    }
}