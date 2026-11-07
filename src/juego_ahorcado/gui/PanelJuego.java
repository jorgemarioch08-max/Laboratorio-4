/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego_ahorcado.gui;

/**
 *
 * @author segio
 */
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import modelo.JuegoAhorcado;
import modelo.ResultadoLetra;
import modelo.EstadoPartida;
import modelo.Modalidad;

import juego_ahorcado.gui.sonido.EfectoSonido;

import excepciones.LetraInvalidaException;
import excepciones.LetraRepetidaException;
import excepciones.PartidaTerminadaException;
import excepciones.PalabraInvalidaException;

public class PanelJuego extends JPanel{
    private VentanaPrincipal ventana;
    private JuegoAhorcado juego;

    private JLabel lblPalabra;
    private JLabel lblIntentos;
    private JLabel lblAcertadas;
    private JLabel lblErradas;
    private JLabel lblMensaje;

    private JTextField txtLetra;

    private JButton btnProbar;
    private JButton btnReiniciar;
    private JButton btnMenu;
    private JButton btnSonido;

    private PanelDibujo panelDibujo;

    public PanelJuego(VentanaPrincipal ventana) {

        this.ventana = ventana;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Panel superior
        JPanel panelSuperior = new JPanel(new GridLayout(3, 1, 5, 5));

        JLabel titulo = new JLabel("JUEGO DEL AHORCADO");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        lblPalabra = new JLabel("_ _ _ _ _");
        lblPalabra.setHorizontalAlignment(SwingConstants.CENTER);
        lblPalabra.setFont(new Font("Arial", Font.BOLD, 30));

        lblIntentos = new JLabel("Intentos: 6 / 6");
        lblIntentos.setHorizontalAlignment(SwingConstants.CENTER);

        panelSuperior.add(titulo);
        panelSuperior.add(lblPalabra);
        panelSuperior.add(lblIntentos);

        add(panelSuperior, BorderLayout.NORTH);

        // Panel central
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 10, 10));

        panelDibujo = new PanelDibujo();

        panelDibujo.setBorder(
                BorderFactory.createTitledBorder("Ahorcado")
        );

        JPanel panelLetras = new JPanel(new GridLayout(2, 1, 5, 5));

        lblAcertadas = new JLabel("Acertadas: ");
        lblErradas = new JLabel("Erradas: ");

        lblAcertadas.setForeground(new Color(0, 130, 0));
        lblErradas.setForeground(Color.RED);

        panelLetras.add(lblAcertadas);
        panelLetras.add(lblErradas);

        panelCentral.add(panelDibujo);
        panelCentral.add(panelLetras);

        add(panelCentral, BorderLayout.CENTER);

        // Panel inferior
        JPanel panelInferior = new JPanel(new GridLayout(3, 1, 5, 5));

        JPanel panelEntrada = new JPanel(new FlowLayout());

        txtLetra = new JTextField(5);
        btnProbar = new JButton("Probar");

        panelEntrada.add(new JLabel("Ingrese una letra:"));
        panelEntrada.add(txtLetra);
        panelEntrada.add(btnProbar);

        lblMensaje = new JLabel("Ingrese una letra para comenzar.");
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());

        btnReiniciar = new JButton("Jugar de nuevo");
        btnMenu = new JButton("Volver al menu");
        btnSonido = new JButton("Sonido: Activado");

        panelBotones.add(btnReiniciar);
        panelBotones.add(btnMenu);
        panelBotones.add(btnSonido);

        panelInferior.add(panelEntrada);
        panelInferior.add(lblMensaje);
        panelInferior.add(panelBotones);

        add(panelInferior, BorderLayout.SOUTH);

        // Acciones de los botones
        btnProbar.addActionListener(e -> probarLetra());

        txtLetra.addActionListener(e -> probarLetra());

        btnReiniciar.addActionListener(e -> reiniciarJuego());

        btnMenu.addActionListener(e ->
                ventana.mostrar(VentanaPrincipal.MENU));

        btnSonido.addActionListener(e -> cambiarSonido());

        actualizarBotonSonido();
    }

    // Iniciar una partida
    public void iniciar(JuegoAhorcado nuevoJuego) {

        juego = nuevoJuego;

        txtLetra.setEnabled(true);
        btnProbar.setEnabled(true);
        txtLetra.setText("");

        lblMensaje.setText("Ingrese una letra para comenzar.");
        lblMensaje.setForeground(Color.BLACK);

        actualizarBotonSonido();
        actualizar();

        txtLetra.requestFocusInWindow();
    }

    // Actualizar la informacion del juego
    private void actualizar() {

        if (juego == null) {
            return;
        }

        String palabra = juego.getPalabraMostrada();
        String palabraSeparada = "";

        for (int i = 0; i < palabra.length(); i++) {
            palabraSeparada += palabra.charAt(i) + " ";
        }

        lblPalabra.setText(palabraSeparada);

        lblIntentos.setText(
                "Intentos: " + juego.getIntentosRestantes() + " / 6"
        );

        lblAcertadas.setText(
                "Acertadas: " + juego.getLetrasAcertadas()
        );

        lblErradas.setText(
                "Erradas: " + juego.getLetrasErradas()
        );

        // Actualizar el dibujo del ahorcado
        panelDibujo.setEtapa(juego.getEtapa());
    }

    // Probar la letra ingresada
    private void probarLetra() {

        if (juego == null) {
            return;
        }

        String letra = txtLetra.getText();

        try {

            ResultadoLetra resultado = juego.ingresarLetra(letra);

            actualizar();

            if (juego.getEstado() == EstadoPartida.GANADA) {

                lblMensaje.setText(
                        "¡Felicidades! Ganaste la partida."
                );

                lblMensaje.setForeground(Color.GREEN);

                txtLetra.setEnabled(false);
                btnProbar.setEnabled(false);

                ventana.getSonido().reproducir(EfectoSonido.VICTORIA);

            } else if (juego.getEstado() == EstadoPartida.PERDIDA) {

                lblMensaje.setText(
                        "Perdiste. La palabra era: "
                        + juego.getPalabraSecreta()
                );

                lblMensaje.setForeground(Color.RED);

                txtLetra.setEnabled(false);
                btnProbar.setEnabled(false);

                ventana.getSonido().reproducir(EfectoSonido.DERROTA);

            } else if (resultado == ResultadoLetra.CORRECTO) {

                lblMensaje.setText("¡Letra correcta!");
                lblMensaje.setForeground(new Color(0, 130, 0));

                ventana.getSonido().reproducir(EfectoSonido.ACIERTO);

            } else {

                lblMensaje.setText("Letra incorrecta.");
                lblMensaje.setForeground(Color.RED);

                ventana.getSonido().reproducir(EfectoSonido.ERROR);
            }

        } catch (LetraInvalidaException ex) {

            lblMensaje.setText(ex.getMessage());
            lblMensaje.setForeground(Color.RED);

        } catch (LetraRepetidaException ex) {

            lblMensaje.setText(ex.getMessage());
            lblMensaje.setForeground(Color.RED);

        } catch (PartidaTerminadaException ex) {

            lblMensaje.setText(ex.getMessage());
            lblMensaje.setForeground(Color.RED);
        }

        txtLetra.setText("");

        if (txtLetra.isEnabled()) {
            txtLetra.requestFocusInWindow();
        }
    }

    // Reiniciar la partida
    private void reiniciarJuego() {

        if (juego == null) {
            return;
        }

        if (juego.getModalidad() == Modalidad.PALABRA_FIJA) {

            ventana.mostrar(VentanaPrincipal.MENU);
            return;
        }

        try {

            juego.reiniciar();

            txtLetra.setEnabled(true);
            btnProbar.setEnabled(true);

            txtLetra.setText("");

            lblMensaje.setText("Nueva partida iniciada.");
            lblMensaje.setForeground(Color.BLACK);

            actualizar();

            txtLetra.requestFocusInWindow();

        } catch (PalabraInvalidaException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Activar o desactivar el sonido
    private void cambiarSonido() {

        boolean silenciado = ventana.getSonido().isSilenciado();

        ventana.getSonido().setSilenciado(!silenciado);

        actualizarBotonSonido();
    }

    // Actualizar el texto del boton de sonido
    private void actualizarBotonSonido() {

        if (!ventana.getSonido().isDisponible()) {

            btnSonido.setText("Sonido: No disponible");
            btnSonido.setEnabled(false);

        } else if (ventana.getSonido().isSilenciado()) {

            btnSonido.setText("Sonido: Desactivado");
            btnSonido.setEnabled(true);

        } else {

            btnSonido.setText("Sonido: Activado");
            btnSonido.setEnabled(true);
        }
    }
    
}
