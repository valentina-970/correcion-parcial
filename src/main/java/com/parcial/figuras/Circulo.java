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
        throw new UnsupportedOperationException("pendiente");
    }

    @Override
    public double calcularArea() {
        throw new UnsupportedOperationException("pendiente");
    }

    @Override
    public double calcularPerimetro() {
        throw new UnsupportedOperationException("pendiente");
    }

    @Override
    public double dimensionar() {
        throw new UnsupportedOperationException("pendiente");
    }
}