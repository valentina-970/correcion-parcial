package com.parcial.figuras;

import com.parcial.figuras.ui.VentanaFiguras;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::abrirVentana);
    }

    private static void abrirVentana() {
        JFrame ventana = new JFrame("GeoFiguras");
        ventana.setContentPane(new VentanaFiguras().getPanelPrincipal());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
