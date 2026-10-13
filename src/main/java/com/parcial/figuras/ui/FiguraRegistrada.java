package com.parcial.figuras.ui;

import com.parcial.figuras.Figura;

/** Figura creada en la ventana, con un número para identificarla en la lista y en los combos. */
public class FiguraRegistrada {
    private final int numero;
    private final Figura figura;

    public FiguraRegistrada(int numero, Figura figura) {
        this.numero = numero;
        this.figura = figura;
    }

    public Figura getFigura() {
        return figura;
    }

    @Override
    public String toString() {
        return String.format("#%d %s · dimensionar %.2f", numero, figura.getNombre(), figura.dimensionar());
    }
}
