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
//Pendiente importar las clases del modelo y las excepciones
public class PanelJuego extends JPanel{
    private VentanaPrincipal ventana;
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
    private JPanel panelDibujo;
    
    public PanelJuego(VentanaPrincipal ventana){
        this.ventana=ventana;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        //Panel Superior
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
        
        //Panel central
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 10, 10));
        panelDibujo = new JPanel();
        panelDibujo.setBorder(BorderFactory.createTitledBorder("Ahorcado"));
        //Sustituir este panel por PanelDibujo (integrante A)
        
        JPanel panelLetras = new JPanel(new GridLayout(2, 1, 5, 5));
        lblAcertadas = new JLabel("Acertadas: ");
        lblErradas = new JLabel("Erradas: ");
        panelLetras.add(lblAcertadas);
        panelLetras.add(lblErradas);
        panelCentral.add(panelDibujo);
        panelCentral.add(panelLetras);
        add(panelCentral, BorderLayout.CENTER);
        
        //Panel inferior
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
        
        btnProbar.addActionListener(e -> probarLetra());
        txtLetra.addActionListener(e -> probarLetra());
        btnReiniciar.addActionListener(e -> reiniciarJuego());
        btnMenu.addActionListener(e -> ventana.mostrar(VentanaPrincipal.MENU));
        btnSonido.addActionListener(e -> cambiarSonido());
    }
    
    private void probarLetra(){
        String letra = txtLetra.getText();
        //Pendiente
        JOptionPane.showMessageDialog(this,"La logica del juego esta pendiente de integrar.");
        txtLetra.setText("");
    }
    
    private void reiniciarJuego(){
        //pendiente juego.reiniciar();
        lblMensaje.setText("Nueva partida pendiente de integrar.");
    }
    
    private void cambiarSonido(){
        //Pendiente
        lblMensaje.setText("Control de sonido pendiente de integrar.");
    }
    
}
