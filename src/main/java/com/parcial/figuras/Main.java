package com.parcial.figuras;

public class Main {

    public static void main(String[] args) {
        Figura circulo = new Circulo(new Punto(0, 0), 5);
        Figura triangulo = new Triangulo(new Punto(1, 1), 6, 4, 5, 5, 6);
        Figura cuadrilatero = new Cuadrilatero(new Punto(2, 2), 4, 3, 4, 3, 4, 3,
                new double[]{0, 4, 4, 0}, new double[]{0, 0, 3, 3});
        Figura pentagono = new PentagonoRegular(new Punto(3, 3), 6, 4.13,
                new double[]{1, 2, 3, 4, 5});

        System.out.println("=== INFORMACIÓN INICIAL ===");
        for (Figura f : new Figura[]{circulo, triangulo, cuadrilatero, pentagono}) {
            f.mostrarInformacion();
        }

        System.out.println("=== DESPLAZAR ===");
        circulo.desplazar(2, -3);
        circulo.mostrarInformacion();

        System.out.println("=== ESCALAR x2 ===");
        triangulo.escalar(2);
        triangulo.mostrarInformacion();

        System.out.println("=== COMPARACIONES ===");
        ComparadorFiguras comparador = new ComparadorFiguras();
        comparador.comparar(new Circulo(new Punto(0, 0), 3), new Circulo(new Punto(0, 0), 7));
        comparador.comparar(circulo, new Circulo(new Punto(0, 0), 5));
        comparador.comparar(circulo, triangulo);

        System.out.println("=== DIMENSIONES NO VÁLIDAS ===");
        probarInvalido("Círculo con radio 0", () -> new Circulo(new Punto(0, 0), 0));
        probarInvalido("Círculo con radio negativo", () -> new Circulo(new Punto(0, 0), -4));
        probarInvalido("Escalar con factor 0", () -> circulo.escalar(0));
        probarInvalido("Triángulo con lados imposibles",
                () -> new Triangulo(new Punto(0, 0), 3, 2, 1, 1, 10));
    }

    private static void probarInvalido(String descripcion, Runnable accion) {
        try {
            accion.run();
            System.out.println("[FALLO] " + descripcion + ": no lanzó excepción");
        } catch (IllegalArgumentException e) {
            System.out.println("[OK] " + descripcion + " -> " + e.getMessage());
        }
    }
}