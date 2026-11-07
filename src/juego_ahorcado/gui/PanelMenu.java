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
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import modelo.AdministradorPalabras;
import modelo.JuegoAhorcado;
import modelo.JuegoPalabraFija;
import modelo.JuegoAlAzar;
import modelo.Modalidad;
import excepciones.PalabraInvalidaException;
public class PanelMenu extends JPanel{
    private VentanaPrincipal ventana;
    private AdministradorPalabras admin;

    private JButton btnPalabraFija;
    private JButton btnPalabraAzar;
    private JButton btnAdministrar;
    private JButton btnSalir;

    public PanelMenu(AdministradorPalabras admin, VentanaPrincipal ventana) {

        this.admin = admin;
        this.ventana = ventana;

        setLayout(new GridBagLayout());
        JPanel columna = new JPanel(new GridLayout(5, 1, 10, 10));

        JLabel titulo = new JLabel("JUEGO DEL AHORCADO");
        titulo.setHorizontalAlignment(JLabel.CENTER);

        btnPalabraFija = new JButton(Modalidad.PALABRA_FIJA.getTexto());
        btnPalabraAzar = new JButton(Modalidad.AL_AZAR.getTexto());
        btnAdministrar = new JButton("Administrar palabras");
        btnSalir = new JButton("Salir");

        btnPalabraFija.setPreferredSize(new Dimension(200, 75));
        btnPalabraAzar.setMaximumSize(new Dimension(200, 75));
        btnPalabraAzar.setPreferredSize(new Dimension(200, 75));
        btnAdministrar.setMaximumSize(new Dimension(200, 75));
        btnAdministrar.setPreferredSize(new Dimension(200, 75));
        btnAdministrar.setMaximumSize(new Dimension(200, 75));
        btnSalir.setPreferredSize(new Dimension(200, 75));
        btnSalir.setMaximumSize(new Dimension(200, 75));


        columna.add(titulo);
        columna.add(btnPalabraFija);
        columna.add(btnPalabraAzar);
        columna.add(btnAdministrar);
        columna.add(btnSalir);

        add(columna);

        btnPalabraFija.addActionListener(e -> jugarPalabraFija());

        btnPalabraAzar.addActionListener(e -> jugarPalabraAzar());

        btnAdministrar.addActionListener(e ->ventana.mostrar(VentanaPrincipal.ADMIN));

        btnSalir.addActionListener(e -> System.exit(0));
    }

    private void jugarPalabraFija() {

        JPasswordField campo = new JPasswordField();

        int opcion = JOptionPane.showConfirmDialog(
                this,
                campo,
                "Ingrese la palabra secreta",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (opcion == JOptionPane.OK_OPTION) {

            String palabra = new String(campo.getPassword());

            try {

                JuegoAhorcado juego = new JuegoPalabraFija(palabra);

                ventana.getPanelJuego().iniciar(juego);

                ventana.mostrar(VentanaPrincipal.JUEGO);

            } catch (PalabraInvalidaException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Palabra invalida",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void jugarPalabraAzar() {

        try {

            JuegoAhorcado juego = new JuegoAlAzar(admin);

            ventana.getPanelJuego().iniciar(juego);

            ventana.mostrar(VentanaPrincipal.JUEGO);

        } catch (PalabraInvalidaException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

}
