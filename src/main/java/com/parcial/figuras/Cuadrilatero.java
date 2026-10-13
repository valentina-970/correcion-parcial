package com.parcial.figuras;

import java.util.Arrays;

public class Cuadrilatero extends Figura {
    private static final int NUMERO_PUNTOS = 4;

    private double base;
    private double altura;
    private double lado1;
    private double lado2;
    private double lado3;
    private double lado4;
    private final double[] puntosX;
    private final double[] puntosY;

    public Cuadrilatero(Punto posicion, double base, double altura,
                        double lado1, double lado2, double lado3, double lado4,
                        double[] puntosX, double[] puntosY) {
        super(posicion);
        validarDimension(base);
        validarDimension(altura);
        validarDimension(lado1);
        validarDimension(lado2);
        validarDimension(lado3);
        validarDimension(lado4);
        if (puntosX == null || puntosY == null
                || puntosX.length != NUMERO_PUNTOS || puntosY.length != NUMERO_PUNTOS) {
            throw new IllegalArgumentException("Se requieren exactamente 4 puntos (X e Y)");
        }
        this.base = base;
        this.altura = altura;
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
        this.lado4 = lado4;
        this.puntosX = puntosX.clone();
        this.puntosY = puntosY.clone();
    }

    public double getBase() {
        return base;
    }

    public double getAltura() {
        return altura;
    }

    public double[] getPuntosX() {
        return puntosX.clone();
    }

    public double[] getPuntosY() {
        return puntosY.clone();
    }

    @Override
    public String getNombre() {
        return "Cuadrilátero";
    }

    @Override
    public String describirDimensiones() {
        return String.format("base=%.2f, altura=%.2f, lados=(%.2f, %.2f, %.2f, %.2f), X=%s, Y=%s",
                base, altura, lado1, lado2, lado3, lado4,
                Arrays.toString(puntosX), Arrays.toString(puntosY));
    }

    /** Además de la posición, mueve los cuatro puntos para que dimensionar siga siendo coherente. */
    @Override
    public void desplazar(double deltaX, double deltaY) {
        super.desplazar(deltaX, deltaY);
        for (int i = 0; i < NUMERO_PUNTOS; i++) {
            puntosX[i] += deltaX;
            puntosY[i] += deltaY;
        }
    }

    @Override
    public void escalar(double factor) {
        validarDimension(factor);
        base *= factor;
        altura *= factor;
        lado1 *= factor;
        lado2 *= factor;
        lado3 *= factor;
        lado4 *= factor;
        for (int i = 0; i < NUMERO_PUNTOS; i++) {
            puntosX[i] *= factor;
            puntosY[i] *= factor;
        }
    }

    @Override
    public double calcularArea() {
        return base * altura;
    }

    @Override
    public double calcularPerimetro() {
        return lado1 + lado2 + lado3 + lado4;
    }

    /** Suma de las distancias de los cuatro puntos al centro del plano (0,0). */
    @Override
    public double dimensionar() {
        double suma = 0;
        for (int i = 0; i < NUMERO_PUNTOS; i++) {
            suma += new Punto(puntosX[i], puntosY[i]).calcularDistanciaAlOrigen();
        }
        return suma;
    }
}