package com.parcial.figuras.ui;

import com.parcial.figuras.Figura;
import com.parcial.figuras.Punto;
import com.parcial.figuras.ui.formularios.FormularioCirculo;
import com.parcial.figuras.ui.formularios.FormularioCuadrilatero;
import com.parcial.figuras.ui.formularios.FormularioPentagono;
import com.parcial.figuras.ui.formularios.FormularioTriangulo;
import com.parcial.figuras.ComparadorFiguras;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import java.awt.CardLayout;
import java.awt.Color;

public class VentanaFiguras {
    private static final Color COLOR_ESTADO = new Color(47, 65, 86);
    private static final Color COLOR_ERROR = new Color(185, 28, 28);
    private final ComparadorFiguras comparador = new ComparadorFiguras();

    // ---- Componentes inyectados por el .form (no se instancian a mano) ----
    private JPanel panelPrincipal;
    private JToggleButton btnCirculo;
    private JToggleButton btnTriangulo;
    private JToggleButton btnCuadrilatero;
    private JToggleButton btnPentagono;
    private JLabel lblTituloConfig;
    private JTextField txtX;
    private JTextField txtY;
    private JPanel panelCampos;
    private JButton btnDibujar;
    private JButton btnLimpiar;
    private JPanel panelDibujo;
    private JLabel lblEscala;
    private JList<FiguraRegistrada> listaFiguras;
    private JTextField txtDeltaX;
    private JTextField txtDeltaY;
    private JButton btnDesplazar;
    private JTextField txtFactor;
    private JButton btnEscalar;
    private JComboBox<FiguraRegistrada> cmbFigura1;
    private JComboBox<FiguraRegistrada> cmbFigura2;
    private JButton btnComparar;
    private JLabel lblResultadoComparacion;
    private JLabel lblTipo;
    private JLabel lblPosicion;
    private JLabel lblDimensiones;
    private JLabel lblArea;
    private JLabel lblPerimetro;
    private JLabel lblDimensionar;
    private JButton btnMostrarInformacion;
    private JButton btnCargarEjemplos;
    private JLabel lblEstado;

    // ---- Estado de la ventana ----
    private PanelDibujo lienzo;
    private final DefaultListModel<FiguraRegistrada> modeloLista = new DefaultListModel<>();
    private final DefaultComboBoxModel<FiguraRegistrada> modeloCombo1 = new DefaultComboBoxModel<>();
    private final DefaultComboBoxModel<FiguraRegistrada> modeloCombo2 = new DefaultComboBoxModel<>();
    private FormularioFigura formularioActual;
    private int contadorFiguras;

    public VentanaFiguras() {
        listaFiguras.setModel(modeloLista);
        cmbFigura1.setModel(modeloCombo1);
        cmbFigura2.setModel(modeloCombo2);

        registrarFormulario(btnCirculo, new FormularioCirculo());
        registrarFormulario(btnTriangulo, new FormularioTriangulo());
        registrarFormulario(btnCuadrilatero, new FormularioCuadrilatero());
        registrarFormulario(btnPentagono, new FormularioPentagono());
        btnCirculo.doClick();

        btnDibujar.addActionListener(e -> ejecutar(this::dibujar));
        btnLimpiar.addActionListener(e -> limpiar());
        btnMostrarInformacion.addActionListener(e -> ejecutar(this::mostrarInformacionCompleta));
        listaFiguras.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarFigura(obtenerFiguraSeleccionada());
            }
        });

        configurarOperaciones();
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /** IntelliJ llama a este método porque panelDibujo tiene marcada la opción "Custom Create". */
    private void createUIComponents() {
        lienzo = new PanelDibujo();
        panelDibujo = lienzo;
    }

    // ---------------------------------------------------------------
    // Selección y creación de figuras
    // ---------------------------------------------------------------

    private void registrarFormulario(JToggleButton boton, FormularioFigura formulario) {
        panelCampos.add(formulario.getPanel(), formulario.getTitulo());
        boton.addActionListener(e -> seleccionarFormulario(formulario));
    }

    private void seleccionarFormulario(FormularioFigura formulario) {
        formularioActual = formulario;
        ((CardLayout) panelCampos.getLayout()).show(panelCampos, formulario.getTitulo());
        lblTituloConfig.setText(formulario.getTitulo());
    }

    private void dibujar() {
        double x = LectorCampos.leer(txtX, "Coordenada X");
        double y = LectorCampos.leer(txtY, "Coordenada Y");
        Figura figura = formularioActual.crearFigura(new Punto(x, y));
        agregarFigura(figura);
        mostrarEstado(figura.getNombre() + " dibujado · Posición y medidas actualizadas");
    }

    private void agregarFigura(Figura figura) {
        contadorFiguras++;
        FiguraRegistrada registrada = new FiguraRegistrada(contadorFiguras, figura);
        modeloLista.addElement(registrada);
        modeloCombo1.addElement(registrada);
        modeloCombo2.addElement(registrada);
        listaFiguras.setSelectedValue(registrada, true);
    }

    private void limpiar() {
        formularioActual.limpiar();
        LectorCampos.limpiar(txtX, txtY);
        listaFiguras.clearSelection();
        mostrarFigura(null);
        mostrarEstado("Campos y vista previa limpios");
    }

    // ---------------------------------------------------------------
    // Información de la figura
    // ---------------------------------------------------------------

    private Figura obtenerFiguraSeleccionada() {
        FiguraRegistrada seleccionada = listaFiguras.getSelectedValue();
        return seleccionada == null ? null : seleccionada.getFigura();
    }

    private Figura exigirFiguraSeleccionada() {
        Figura figura = obtenerFiguraSeleccionada();
        if (figura == null) {
            throw new IllegalArgumentException("Primero dibuja o selecciona una figura de la lista");
        }
        return figura;
    }

    private void mostrarFigura(Figura figura) {
        lienzo.setFigura(figura);
        if (figura == null) {
            lblTipo.setText("-");
            lblPosicion.setText("-");
            lblDimensiones.setText("-");
            lblArea.setText("-");
            lblPerimetro.setText("-");
            lblDimensionar.setText("-");
            lblEscala.setText(" ");
            return;
        }
        Punto posicion = figura.getPosicion();
        lblTipo.setText(figura.getNombre());
        lblPosicion.setText(String.format("X: %.2f px · Y: %.2f px", posicion.getX(), posicion.getY()));
        lblDimensiones.setText("<html><body style='width:200px'>"
                + figura.describirDimensiones() + "</body></html>");
        lblArea.setText(String.format("%.2f px²", figura.calcularArea()));
        lblPerimetro.setText(String.format("%.2f px", figura.calcularPerimetro()));
        lblDimensionar.setText(String.format("%.2f", figura.dimensionar()));
        lienzo.paintImmediately(lienzo.getVisibleRect());
        lblEscala.setText(String.format("Escala x%.2f", lienzo.getEscalaActual()));
        listaFiguras.repaint();
        cmbFigura1.repaint();
        cmbFigura2.repaint();
    }

    private void mostrarInformacionCompleta() {
        Figura figura = exigirFiguraSeleccionada();
        figura.mostrarInformacion();
        JOptionPane.showMessageDialog(panelPrincipal, figura.obtenerInformacion(),
                "Información de la figura", JOptionPane.INFORMATION_MESSAGE);
    }

    // ---------------------------------------------------------------
    // Operaciones: desplazar, escalar, comparar y ejemplos
    // ---------------------------------------------------------------

    private void configurarOperaciones() {
        btnDesplazar.addActionListener(e -> ejecutar(this::desplazar));
        btnEscalar.addActionListener(e -> ejecutar(this::escalar));
        btnComparar.addActionListener(e -> ejecutar(this::comparar));
    }

    private void desplazar() {
        Figura figura = exigirFiguraSeleccionada();
        double deltaX = LectorCampos.leer(txtDeltaX, "Desplazar X");
        double deltaY = LectorCampos.leer(txtDeltaY, "Desplazar Y");
        figura.desplazar(deltaX, deltaY);
        mostrarFigura(figura);
        mostrarEstado(String.format("%s desplazado (%.2f, %.2f)", figura.getNombre(), deltaX, deltaY));
    }

    private void escalar() {
        Figura figura = exigirFiguraSeleccionada();
        double factor = LectorCampos.leer(txtFactor, "Factor");
        figura.escalar(factor);
        mostrarFigura(figura);
        mostrarEstado(String.format("%s escalado x%.2f", figura.getNombre(), factor));
    }

    private void comparar() {
        FiguraRegistrada primera = (FiguraRegistrada) cmbFigura1.getSelectedItem();
        FiguraRegistrada segunda = (FiguraRegistrada) cmbFigura2.getSelectedItem();
        if (primera == null || segunda == null) {
            throw new IllegalArgumentException("Se necesitan dos figuras para comparar");
        }
        String resultado = comparador.obtenerResultado(primera.getFigura(), segunda.getFigura());
        comparador.comparar(primera.getFigura(), segunda.getFigura());
        boolean comparables = comparador.sonComparables(primera.getFigura(), segunda.getFigura());
        lblResultadoComparacion.setForeground(comparables ? COLOR_ESTADO : COLOR_ERROR);
        lblResultadoComparacion.setText("<html><body style='width:200px'>" + resultado + "</body></html>");
        mostrarEstado("Comparación realizada");
    }

    // ---------------------------------------------------------------
    // Mensajes y manejo de errores
    // ---------------------------------------------------------------

    /** Ejecuta una acción y, si el modelo rechaza los datos, muestra el error sin cerrar la ventana. */
    private void ejecutar(Runnable accion) {
        try {
            accion.run();
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    private void mostrarEstado(String mensaje) {
        lblEstado.setForeground(COLOR_ESTADO);
        lblEstado.setText(mensaje);
    }

    private void mostrarError(String mensaje) {
        lblEstado.setForeground(COLOR_ERROR);
        lblEstado.setText("Error: " + mensaje);
        JOptionPane.showMessageDialog(panelPrincipal, mensaje, "Dato no válido", JOptionPane.ERROR_MESSAGE);
    }
}
