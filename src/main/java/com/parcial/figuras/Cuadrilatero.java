package com.parcial.figuras;

import java.util.Arrays;

public class Cuadrilatero extends Figura {
    private static final int NUMERO_PUNTOS = 4;

    private double base;
    private double altura;
    private double l1;
    private double l2;
    private double l3;
    private double l4;
    private final double[] puntosX;
    private final double[] puntosY;

    public Cuadrilatero(Punto posicion, double base, double altura,
                        double l1, double l2, double l3, double l4,
                        double[] puntosX, double[] puntosY) {
        super(posicion);
        validarDimension(base);
        validarDimension(altura);
        validarDimension(l1);
        validarDimension(l2);
        validarDimension(l3);
        validarDimension(l4);
        if (puntosX == null || puntosY == null
                || puntosX.length != NUMERO_PUNTOS || puntosY.length != NUMERO_PUNTOS) {
            throw new IllegalArgumentException("Se requieren exactamente 4 puntos (X e Y)");
        }
        this.base = base;
        this.altura = altura;
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
        this.l4 = l4;
        this.puntosX = puntosX.clone();
        this.puntosY = puntosY.clone();
    }

    @Override
    protected String describirDimensiones() {
        return String.format("base=%.2f, altura=%.2f, lados=(%.2f, %.2f, %.2f, %.2f), X=%s, Y=%s",
                base, altura, l1, l2, l3, l4,
                Arrays.toString(puntosX), Arrays.toString(puntosY));
    }

    @Override
    public void escalar(double factor) {
        validarDimension(factor);
        base *= factor;
        altura *= factor;
        l1 *= factor;
        l2 *= factor;
        l3 *= factor;
        l4 *= factor;
        for (int i = 0; i < NUMERO_PUNTOS; i++) {
            puntosX[i] *= factor;
            puntosY[i] *= factor;
        }
    }

    }

    @Override
    public double calcularArea() {
        return base * altura;
    }

    @Override
    public double calcularPerimetro() {
        return l1 + l2 + l3 + l4;
    }

    /** Suma de las distancias de los cuatro puntos al centro del plano (0,0). */
    @Override
    public double dimensionar() {
        double suma = 0;
        for (int i = 0; i < NUMERO_PUNTOS; i++) {
            suma += Math.hypot(puntosX[i], puntosY[i]);
        }
        return suma;
    }
}