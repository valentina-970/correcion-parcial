package com.parcial.figuras;

import java.util.Arrays;

public class PentagonoRegular extends Figura {
    private static final int NUMERO_LADOS = 5;

    private double lado;
    private double apotema;
    private final double[] puntosX;

    public PentagonoRegular(Punto posicion, double lado, double apotema, double[] puntosX) {
        super(posicion);
        validarDimension(lado);
        validarDimension(apotema);
        if (puntosX == null || puntosX.length != NUMERO_LADOS) {
            throw new IllegalArgumentException("Se requieren exactamente 5 coordenadas X");
        }
        this.lado = lado;
        this.apotema = apotema;
        this.puntosX = puntosX.clone();
    }

    @Override
    protected String describirDimensiones() {
        return String.format("lado=%.2f, apotema=%.2f, X=%s",
                lado, apotema, Arrays.toString(puntosX));
    }

    @Override
    public void escalar(double factor) {
        validarDimension(factor);
        lado *= factor;
        apotema *= factor;
        for (int i = 0; i < NUMERO_LADOS; i++) {
            puntosX[i] *= factor;
        }
    }

    @Override
    public double calcularArea() {
        return calcularPerimetro() * apotema / 2;
    }

    @Override
    public double calcularPerimetro() {
        return NUMERO_LADOS * lado;
    }

    /** Suma de las coordenadas X de cada punto. */
    @Override
    public double dimensionar() {
        return Arrays.stream(puntosX).sum();
    }
}