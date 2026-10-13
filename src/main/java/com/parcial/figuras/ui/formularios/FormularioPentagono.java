package com.parcial.figuras.ui.formularios;
import com.parcial.figuras.Figura;
import com.parcial.figuras.PentagonoRegular;
import com.parcial.figuras.Punto;
import com.parcial.figuras.ui.FormularioFigura;
import com.parcial.figuras.ui.LectorCampos;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class FormularioPentagono implements FormularioFigura {
    private JPanel panel;
    private JTextField txtLado;
    private JTextField txtApotema;
    private JTextField txtX1;
    private JTextField txtX2;
    private JTextField txtX3;
    private JTextField txtX4;
    private JTextField txtX5;
    @Override
    public String getTitulo() {
        return "Configurar pentágono regular";
    }
    @Override
    public JPanel getPanel() {
        return panel;
    }
    @Override
    public Figura crearFigura(Punto posicion) {
        double lado = LectorCampos.leer(txtLado, "Lado");
        double apotema = LectorCampos.leer(txtApotema, "Apotema");
        double[] puntosX = LectorCampos.leerVarios(
                new JTextField[]{txtX1, txtX2, txtX3, txtX4, txtX5}, "X");
        return new PentagonoRegular(posicion, lado, apotema, puntosX);
    }
    @Override
    public void limpiar() {
        LectorCampos.limpiar(txtLado, txtApotema, txtX1, txtX2, txtX3, txtX4, txtX5);
    }
}
