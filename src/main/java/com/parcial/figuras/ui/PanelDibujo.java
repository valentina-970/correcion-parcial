package com.parcial.figuras.ui;

import com.parcial.figuras.Figura;
import com.parcial.figuras.Punto;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/** Vista previa: dibuja los ejes del plano y la figura seleccionada, ajustada al tamaño del panel. */
public class PanelDibujo extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int MARGEN = 40;
    private static final double ESCALA_MAXIMA = 20;
    private static final Color COLOR_EJES = new Color(148, 163, 184);
    private static final Color COLOR_BORDE = new Color(37, 99, 235);
    private static final Color COLOR_RELLENO = new Color(37, 99, 235, 30);
    private static final Color COLOR_TEXTO = new Color(100, 116, 139);

    private final transient FabricaFormas fabricaFormas = new FabricaFormas();
    private transient Figura figura;
    private double escalaActual = 1;

    public PanelDibujo() {
        setBackground(Color.WHITE);
    }

    public void setFigura(Figura figura) {
        this.figura = figura;
        repaint();
    }

    public void limpiar() {
        setFigura(null);
    }

    public double getEscalaActual() {
        return escalaActual;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics.create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(getFont().deriveFont(Font.PLAIN, 11f));

        if (figura == null) {
            dibujarMensajeVacio(g);
        } else {
            dibujarFigura(g);
        }
        g.dispose();
    }

    private void dibujarMensajeVacio(Graphics2D g) {
        String mensaje = "Sin figura dibujada";
        g.setColor(COLOR_TEXTO);
        int ancho = g.getFontMetrics().stringWidth(mensaje);
        g.drawString(mensaje, (getWidth() - ancho) / 2, getHeight() / 2);
    }

    private void dibujarFigura(Graphics2D g) {
        Shape forma = fabricaFormas.crearForma(figura);
        Rectangle2D limites = forma.getBounds2D().createUnion(new Rectangle2D.Double(0, 0, 0, 0));
        AffineTransform transformacion = calcularTransformacion(limites);

        dibujarEjes(g, transformacion, limites);

        Shape formaEnPantalla = transformacion.createTransformedShape(forma);
        g.setColor(COLOR_RELLENO);
        g.fill(formaEnPantalla);
        g.setColor(COLOR_BORDE);
        g.setStroke(new BasicStroke(2f));
        g.draw(formaEnPantalla);

        Punto posicion = figura.getPosicion();
        Point2D puntoEnPantalla = transformacion.transform(
                new Point2D.Double(posicion.getX(), posicion.getY()), null);
        g.fillOval((int) puntoEnPantalla.getX() - 3, (int) puntoEnPantalla.getY() - 3, 6, 6);

        String etiqueta = String.format("(%.2f, %.2f)", posicion.getX(), posicion.getY());
        int anchoEtiqueta = g.getFontMetrics().stringWidth(etiqueta);
        Rectangle2D limitesEnPantalla = formaEnPantalla.getBounds2D();
        g.setColor(COLOR_TEXTO);
        g.drawString(etiqueta, (int) (limitesEnPantalla.getCenterX() - anchoEtiqueta / 2.0),
                (int) Math.min(getHeight() - 4, limitesEnPantalla.getMaxY() + 16));
    }

    private AffineTransform calcularTransformacion(Rectangle2D limites) {
        double anchoDisponible = Math.max(1, getWidth() - 2.0 * MARGEN);
        double altoDisponible = Math.max(1, getHeight() - 2.0 * MARGEN);
        double anchoFigura = Math.max(limites.getWidth(), 1);
        double altoFigura = Math.max(limites.getHeight(), 1);
        escalaActual = Math.min(ESCALA_MAXIMA,
                Math.min(anchoDisponible / anchoFigura, altoDisponible / altoFigura));

        AffineTransform transformacion = new AffineTransform();
        transformacion.translate(MARGEN, MARGEN);
        transformacion.scale(escalaActual, escalaActual);
        transformacion.translate(-limites.getMinX(), -limites.getMinY());
        return transformacion;
    }

    private void dibujarEjes(Graphics2D g, AffineTransform transformacion, Rectangle2D limites) {
        Point2D origen = transformacion.transform(new Point2D.Double(0, 0), null);
        Point2D finX = transformacion.transform(new Point2D.Double(limites.getMaxX(), 0), null);
        Point2D finY = transformacion.transform(new Point2D.Double(0, limites.getMaxY()), null);
        double finalX = Math.max(finX.getX(), origen.getX()) + MARGEN / 2.0;
        double finalY = Math.max(finY.getY(), origen.getY()) + MARGEN / 2.0;

        g.setColor(COLOR_EJES);
        g.setStroke(new BasicStroke(1f));
        g.draw(new Line2D.Double(origen.getX(), origen.getY(), finalX, origen.getY()));
        g.draw(new Line2D.Double(origen.getX(), origen.getY(), origen.getX(), finalY));
        g.drawString("0", (int) origen.getX() - 12, (int) origen.getY() - 4);
        g.drawString("X", (int) finalX - 8, (int) origen.getY() + 14);
        g.drawString("Y", (int) origen.getX() - 14, (int) finalY);
    }
}