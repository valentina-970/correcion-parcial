package com.parcial.figuras.ui;

import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;

/** Convierte el texto de los campos en números y marca en rojo el campo con error. */
public final class LectorCampos {
    private static final Color COLOR_ERROR = new Color(254, 226, 226);

    private LectorCampos() {
    }

    public static double leer(JTextField campo, String nombreCampo) {
        String texto = campo.getText().trim().replace(',', '.');
        if (texto.isEmpty()) {
            marcarError(campo);
            throw new IllegalArgumentException("El campo \"" + nombreCampo + "\" está vacío");
        }
        try {
            double valor = Double.parseDouble(texto);
            quitarError(campo);
            return valor;
        } catch (NumberFormatException e) {
            marcarError(campo);
            throw new IllegalArgumentException(
                    "El campo \"" + nombreCampo + "\" debe ser numérico. Valor recibido: " + campo.getText());
        }
    }

    public static double[] leerVarios(JTextField[] campos, String prefijo) {
        double[] valores = new double[campos.length];
        for (int i = 0; i < campos.length; i++) {
            valores[i] = leer(campos[i], prefijo + (i + 1));
        }
        return valores;
    }

    public static void limpiar(JTextField... campos) {
        for (JTextField campo : campos) {
            campo.setText("");
            quitarError(campo);
        }
    }

    private static void marcarError(JTextField campo) {
        campo.setBackground(COLOR_ERROR);
    }

    private static void quitarError(JTextField campo) {
        campo.setBackground(UIManager.getColor("TextField.background"));
    }
}