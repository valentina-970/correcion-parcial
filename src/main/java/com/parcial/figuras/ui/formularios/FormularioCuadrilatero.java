package com.parcial.figuras.ui.formularios;
import com.parcial.figuras.Cuadrilatero;
import com.parcial.figuras.Figura;
import com.parcial.figuras.Punto;
import com.parcial.figuras.ui.FormularioFigura;
import com.parcial.figuras.ui.LectorCampos;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class FormularioCuadrilatero implements FormularioFigura {
    private JPanel panel;
    private JTextField txtBase;
    private JTextField txtAltura;
    private JTextField txtLado1;
    private JTextField txtLado2;
    private JTextField txtLado3;
    private JTextField txtLado4;
    private JTextField txtX1;
    private JTextField txtX2;
    private JTextField txtX3;
    private JTextField txtX4;
    private JTextField txtY1;
    private JTextField txtY2;
    private JTextField txtY3;
    private JTextField txtY4;
    @Override
    public String getTitulo() {
        return "Configurar cuadrilátero";
    }
    @Override
    public JPanel getPanel() {
        return panel;
    }
    @Override
    public Figura crearFigura(Punto posicion) {
        double base = LectorCampos.leer(txtBase, "Base");
        double altura = LectorCampos.leer(txtAltura, "Altura");
        double[] lados = LectorCampos.leerVarios(
                new JTextField[]{txtLado1, txtLado2, txtLado3, txtLado4}, "Lado ");
        double[] puntosX = LectorCampos.leerVarios(new JTextField[]{txtX1, txtX2, txtX3, txtX4}, "X");
        double[] puntosY = LectorCampos.leerVarios(new JTextField[]{txtY1, txtY2, txtY3, txtY4}, "Y");
        return new Cuadrilatero(posicion, base, altura,
                lados[0], lados[1], lados[2], lados[3], puntosX, puntosY);
    }
    @Override
    public void limpiar() {
        LectorCampos.limpiar(txtBase, txtAltura, txtLado1, txtLado2, txtLado3, txtLado4,
                txtX1, txtX2, txtX3, txtX4, txtY1, txtY2, txtY3, txtY4);
    }
}
