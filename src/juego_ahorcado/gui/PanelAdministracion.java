package juego_ahorcado.gui;

import excepciones.*;
import juego_ahorcado.gui.*;
import juego_ahorcado.gui.sonido.*;
import modelo.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelAdministracion extends JPanel {

    private final AdministradorPalabras admin;
    private final VentanaPrincipal ventana;
    private final GestorSonido sonido;

    private JTextField campoPalabra;
    private DefaultListModel<String> modeloLista;
    private JLabel etiquetaMensaje;
    private JLabel etiquetaCantidad;

    public PanelAdministracion(AdministradorPalabras admin, VentanaPrincipal ventana, GestorSonido sonido) {
        this.admin = admin;
        this.ventana = ventana;
        this.sonido = sonido;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("Administrar palabras", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));

        campoPalabra = new JTextField(15);
        JButton botonAgregar = new JButton("Agregar");

        JPanel panelAgregar = new JPanel();
        panelAgregar.add(new JLabel("Nueva palabra:"));
        panelAgregar.add(campoPalabra);
        panelAgregar.add(botonAgregar);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(titulo, BorderLayout.NORTH);
        panelSuperior.add(panelAgregar, BorderLayout.CENTER);

        modeloLista = new DefaultListModel<>();
        JList<String> lista = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(lista);

        etiquetaMensaje = new JLabel(" ", SwingConstants.CENTER);
        etiquetaCantidad = new JLabel("", SwingConstants.CENTER);
        JButton botonVolver = new JButton("Volver al menú");

        JPanel panelInferior = new JPanel(new GridLayout(3, 1, 5, 5));
        panelInferior.add(etiquetaMensaje);
        panelInferior.add(etiquetaCantidad);
        panelInferior.add(botonVolver);

        add(panelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        ActionListener accionAgregar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPalabra();
            }
        };
        botonAgregar.addActionListener(accionAgregar);
        campoPalabra.addActionListener(accionAgregar);

        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                etiquetaMensaje.setText(" ");
                ventana.mostrar(VentanaPrincipal.MENU);
            }
        });

        actualizarLista();
    }

    private void agregarPalabra() {
        try {
            admin.agregar(campoPalabra.getText());
            etiquetaMensaje.setForeground(new Color(0, 130, 0));
            etiquetaMensaje.setText("Palabra agregada correctamente.");
            campoPalabra.setText("");
            actualizarLista();
        } catch (PalabraInvalidaException e) {
            mostrarError(e.getMessage());
        } catch (PalabraDuplicadaException e) {
            mostrarError(e.getMessage());
        }
        campoPalabra.requestFocus();
    }

    private void mostrarError(String mensaje) {
        etiquetaMensaje.setForeground(Color.RED);
        etiquetaMensaje.setText(mensaje);
        sonido.reproducir(EfectoSonido.ALERTA);
    }

    private void actualizarLista() {
        modeloLista.clear();
        List<String> palabras = admin.getPalabras();
        for (int i = 0; i < palabras.size(); i++) {
            modeloLista.addElement(palabras.get(i));
        }
        etiquetaCantidad.setText("Total de palabras: " + admin.cantidad());
    }


}
