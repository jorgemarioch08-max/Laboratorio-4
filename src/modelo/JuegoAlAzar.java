package modelo;
import excepciones.PalabraInvalidaException;
import java.io.Serializable;
/**
 *locura
 * @author kendr
 */
public class JuegoAlAzar extends JuegoBase implements Serializable {

    private static final long serialVersionUID = 1L;

    private AdministradorPalabras administrador;

    // Constructor
    public JuegoAlAzar(AdministradorPalabras administrador)
            throws PalabraInvalidaException {

        super();

        this.administrador = administrador;

        reiniciar();
    }

    // Elegir una palabra aleatoria
    @Override
    protected String elegirPalabra() throws PalabraInvalidaException {

        return administrador.obtenerAleatoria();
    }

    // Obtener la modalidad
    @Override
    public Modalidad getModalidad() {

        return Modalidad.AL_AZAR;
    }
}
