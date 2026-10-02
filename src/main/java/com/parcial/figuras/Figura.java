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
}