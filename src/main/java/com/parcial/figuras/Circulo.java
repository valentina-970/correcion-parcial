package com.parcial.figuras;

public class Circulo extends Figura {
    private double radio;

    public Circulo(Punto posicion, double radio) {
        super(posicion);
        validarDimension(radio);
        this.radio = radio;
    }

    @Override
    protected String describirDimensiones() {
        return String.format("radio=%.2f", radio);
    }

    @Override
    public void escalar(double factor) {
        validarDimension(factor);
        radio *= factor;
    }

    @Override
    public double calcularArea() {
        return Math.PI * radio * radio;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * radio;
    }

    @Override
    public double dimensionar() {
        return calcularArea();
    }
}