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
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import modelo.AdministradorPalabras;
import juego_ahorcado.gui.sonido.GestorSonido;


public class VentanaPrincipal extends JFrame{
    public static final String MENU = "MENU";
    public static final String JUEGO = "JUEGO";
    public static final String ADMIN = "ADMIN";

    private CardLayout cardLayout;
    private JPanel contenedor;

    private PanelMenu panelMenu;
    private PanelJuego panelJuego;
    private GestorSonido sonido;

    public VentanaPrincipal(AdministradorPalabras admin) {

        setTitle("Juego del Ahorcado");
        setSize(800, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        sonido = new GestorSonido();

        panelMenu = new PanelMenu(admin, this);
        panelJuego = new PanelJuego(this);

        contenedor.add(panelMenu, MENU);
        contenedor.add(panelJuego, JUEGO);

        contenedor.add(
                new PanelAdministracion(admin, this, sonido),
                ADMIN
        );

        add(contenedor);

        mostrar(MENU);

        if (!sonido.isDisponible()) {

            JOptionPane.showMessageDialog(
                    this,
                    "El sonido no esta disponible. El juego continuara sin sonido.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    public void mostrar(String pantalla) {
        cardLayout.show(contenedor, pantalla);
    }

    public PanelJuego getPanelJuego() {
        return panelJuego;
    }

    public GestorSonido getSonido() {
        return sonido;
    }
}
