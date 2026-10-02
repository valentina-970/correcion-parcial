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
}