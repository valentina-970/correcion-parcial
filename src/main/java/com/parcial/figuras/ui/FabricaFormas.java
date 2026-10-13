package com.parcial.figuras.ui;

import com.parcial.figuras.Circulo;
import com.parcial.figuras.Cuadrilatero;
import com.parcial.figuras.Figura;
import com.parcial.figuras.PentagonoRegular;
import com.parcial.figuras.Punto;
import com.parcial.figuras.Triangulo;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/** Convierte cada figura del modelo en una forma de Java2D (coordenadas del plano, Y hacia abajo). */
public class FabricaFormas {
    private static final int LADOS_PENTAGONO = 5;

    private final Map<Class<? extends Figura>, Function<Figura, Shape>> creadores = new HashMap<>();

    public FabricaFormas() {
        registrar(Circulo.class, figura -> crearCirculo((Circulo) figura));
        registrar(Triangulo.class, figura -> crearTriangulo((Triangulo) figura));
        registrar(Cuadrilatero.class, figura -> crearCuadrilatero((Cuadrilatero) figura));
        registrar(PentagonoRegular.class, figura -> crearPentagono((PentagonoRegular) figura));
    }

    public final void registrar(Class<? extends Figura> tipo, Function<Figura, Shape> creador) {
        creadores.put(tipo, creador);
    }

    public Shape crearForma(Figura figura) {
        Function<Figura, Shape> creador = creadores.get(figura.getClass());
        if (creador == null) {
            throw new IllegalStateException("No hay forma registrada para " + figura.getNombre());
        }
        return creador.apply(figura);
    }

    private Shape crearCirculo(Circulo circulo) {
        Punto centro = circulo.getPosicion();
        double radio = circulo.getRadio();
        return new Ellipse2D.Double(centro.getX() - radio, centro.getY() - radio, 2 * radio, 2 * radio);
    }

    /** Vértice A en la posición, lado 1 = AB horizontal, lado 2 = BC, lado 3 = CA (ley de cosenos). */
    private Shape crearTriangulo(Triangulo triangulo) {
        Punto a = triangulo.getPosicion();
        double ab = triangulo.getLado1();
        double bc = triangulo.getLado2();
        double ca = triangulo.getLado3();
        double desplazamientoC = (ab * ab + ca * ca - bc * bc) / (2 * ab);
        double alturaC = Math.sqrt(Math.max(0, ca * ca - desplazamientoC * desplazamientoC));

        Path2D.Double forma = new Path2D.Double();
        forma.moveTo(a.getX(), a.getY());
        forma.lineTo(a.getX() + ab, a.getY());
        forma.lineTo(a.getX() + desplazamientoC, a.getY() - alturaC);
        forma.closePath();
        return forma;
    }

    private Shape crearCuadrilatero(Cuadrilatero cuadrilatero) {
        double[] puntosX = cuadrilatero.getPuntosX();
        double[] puntosY = cuadrilatero.getPuntosY();
        Path2D.Double forma = new Path2D.Double();
        forma.moveTo(puntosX[0], puntosY[0]);
        for (int i = 1; i < puntosX.length; i++) {
            forma.lineTo(puntosX[i], puntosY[i]);
        }
        forma.closePath();
        return forma;
    }

    /** Pentágono centrado en la posición, con radio circunscrito R = lado / (2 · sen 36°). */
    private Shape crearPentagono(PentagonoRegular pentagono) {
        Punto centro = pentagono.getPosicion();
        double radio = pentagono.getLado() / (2 * Math.sin(Math.PI / LADOS_PENTAGONO));
        Path2D.Double forma = new Path2D.Double();
        for (int i = 0; i < LADOS_PENTAGONO; i++) {
            double angulo = -Math.PI / 2 + i * 2 * Math.PI / LADOS_PENTAGONO;
            double x = centro.getX() + radio * Math.cos(angulo);
            double y = centro.getY() + radio * Math.sin(angulo);
            if (i == 0) {
                forma.moveTo(x, y);
            } else {
                forma.lineTo(x, y);
            }
        }
        forma.closePath();
        return forma;
    }
}