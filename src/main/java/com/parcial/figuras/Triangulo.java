package com.parcial.figuras;

public class Triangulo extends Figura {
    private double base;
    private double altura;
    private double lado1;
    private double lado2;
    private double lado3;

    public Triangulo(Punto posicion, double base, double altura,
                     double lado1, double lado2, double lado3) {
        super(posicion);
        validarDimension(base);
        validarDimension(altura);
        validarDimension(lado1);
        validarDimension(lado2);
        validarDimension(lado3);
        if (lado1 + lado2 <= lado3 || lado1 + lado3 <= lado2 || lado2 + lado3 <= lado1) {
            throw new IllegalArgumentException("Los lados no forman un triángulo válido");
        }
        this.base = base;
        this.altura = altura;
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
    }

    public double getBase() {
        return base;
    }

    public double getAltura() {
        return altura;
    }

    public double getLado1() {
        return lado1;
    }

    public double getLado2() {
        return lado2;
    }

    public double getLado3() {
        return lado3;
    }

    @Override
    public String getNombre() {
        return "Triángulo";
    }

    @Override
    public String describirDimensiones() {
        return String.format("base=%.2f, altura=%.2f, lados=(%.2f, %.2f, %.2f)",
                base, altura, lado1, lado2, lado3);
    }

    @Override
    public void escalar(double factor) {
        validarDimension(factor);
        base *= factor;
        altura *= factor;
        lado1 *= factor;
        lado2 *= factor;
        lado3 *= factor;
    }

    @Override
    public double calcularArea() {
        return base * altura / 2;
    }

    @Override
    public double calcularPerimetro() {
        return lado1 + lado2 + lado3;
    }

    @Override
    public double dimensionar() {
        return calcularPerimetro();
    }
}