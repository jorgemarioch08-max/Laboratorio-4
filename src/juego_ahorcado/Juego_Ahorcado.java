package juego_ahorcado;

/**Laboratorio de programacion II, semana 4
 *
 * 7/11/2026
 */
import javax.swing.SwingUtilities;
import juego_ahorcado.gui.VentanaPrincipal;
public class Juego_Ahorcado {

    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setVisible(true);
        });
    }
    
}
