package com.parcial.figuras.ui;

import com.parcial.figuras.Figura;
import com.parcial.figuras.Punto;

import javax.swing.JPanel;

/**
 * Contrato de los sub-formularios de cada figura.
 * Para agregar una figura nueva basta con crear otra clase que lo implemente.
 */
public interface FormularioFigura {

    String getTitulo();

    JPanel getPanel();

    Figura crearFigura(Punto posicion);

    void limpiar();
}