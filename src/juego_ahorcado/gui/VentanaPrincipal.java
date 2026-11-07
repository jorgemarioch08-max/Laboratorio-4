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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

//importar la clase de GestorSonido(integrante B)
//Importar tambien la clase de administradoraPalabras(integrante B)
public class VentanaPrincipal extends JFrame{
    
    public static final String MENU="MENU";
    public static final String JUEGO="JUEGO";
    public static final String ADMIN="ADMIN";
    private CardLayout cardLayout;
    private JPanel contenedor;
    
    //Constructor provisional, hace falta administradora de palabras
    public VentanaPrincipal(){
        setTitle("Juego del Ahorcado");
        setSize(800,900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);
        JPanel menu = new JPanel();
        menu.add(new JLabel("Menu Principal"));
        JPanel juego = new JPanel();
        juego.add(new JLabel("Pantalla del Juego"));
        JPanel admin = new JPanel();
        admin.add(new JLabel("Administrar Palabras"));
        contenedor.add(menu, MENU);
        contenedor.add(juego, JUEGO);
        contenedor.add(admin, ADMIN);
        add(contenedor);
        mostrar(MENU);

    }
    
    public void mostrar(String pantalla){
        cardLayout.show(contenedor,pantalla);
    }
}
