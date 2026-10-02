package com.parcial.figuras;

public class Triangulo extends Figura {
    private double base;
    private double altura;
    private double l1;
    private double l2;
    private double l3;

    public Triangulo(Punto posicion, double base, double altura,
                     double l1, double l2, double l3) {
        super(posicion);
        validarDimension(base);
        validarDimension(altura);
        validarDimension(l1);
        validarDimension(l2);
        validarDimension(l3);
        if (l1 + l2 <= l3 || l1 + l3 <= l2 || l2 + l3 <= l1) {
            throw new IllegalArgumentException("Los lados no forman un triángulo válido");
        }
        this.base = base;
        this.altura = altura;
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
    }

    @Override
    protected String describirDimensiones() {
        return String.format("base=%.2f, altura=%.2f, lados=(%.2f, %.2f, %.2f)",
                base, altura, l1, l2, l3);
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