package com.parcial.figuras.ui.formularios;
import com.parcial.figuras.Figura;
import com.parcial.figuras.Punto;
import com.parcial.figuras.Triangulo;
import com.parcial.figuras.ui.FormularioFigura;
import com.parcial.figuras.ui.LectorCampos;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class FormularioTriangulo implements FormularioFigura {
    private JPanel panel;
    private JTextField txtBase;
    private JTextField txtAltura;
    private JTextField txtLado1;
    private JTextField txtLado2;
    private JTextField txtLado3;
    @Override
    public String getTitulo() {
        return "Configurar triángulo";
    }
    @Override
    public JPanel getPanel() {
        return panel;
    }
    @Override
    public Figura crearFigura(Punto posicion) {
        double base = LectorCampos.leer(txtBase, "Base");
        double altura = LectorCampos.leer(txtAltura, "Altura");
        double lado1 = LectorCampos.leer(txtLado1, "Lado 1");
        double lado2 = LectorCampos.leer(txtLado2, "Lado 2");
        double lado3 = LectorCampos.leer(txtLado3, "Lado 3");
        return new Triangulo(posicion, base, altura, lado1, lado2, lado3);
    }
    @Override
    public void limpiar() {
        LectorCampos.limpiar(txtBase, txtAltura, txtLado1, txtLado2, txtLado3);
    }
}
