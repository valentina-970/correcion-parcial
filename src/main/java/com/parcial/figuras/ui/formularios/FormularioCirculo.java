package com.parcial.figuras.ui.formularios;

import com.parcial.figuras.Circulo;
import com.parcial.figuras.Figura;
import com.parcial.figuras.Punto;
import com.parcial.figuras.ui.FormularioFigura;
import com.parcial.figuras.ui.LectorCampos;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormularioCirculo implements FormularioFigura {
    private JPanel panel;
    private JTextField txtRadio;

    @Override
    public String getTitulo() {
        return "Configurar círculo";
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    @Override
    public Figura crearFigura(Punto posicion) {
        double radio = LectorCampos.leer(txtRadio, "Radio");
        return new Circulo(posicion, radio);
    }

    @Override
    public void limpiar() {
        LectorCampos.limpiar(txtRadio);
    }
}