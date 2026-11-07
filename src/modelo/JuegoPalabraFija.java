package modelo;

import excepciones.PalabraInvalidaException;
import java.io.Serializable;
/**
 *
 * @author kendr
 */
public class JuegoPalabraFija extends JuegoBase implements Serializable {
    private static final long serialVersionUID = 1L;
    private String palabraConfigurada;

    // Constructor
    public JuegoPalabraFija(String palabra) throws PalabraInvalidaException {
        super();
        palabraConfigurada=palabra;
        establecerPalabraSecreta(palabraConfigurada);
    }

    public void cambiarPalabra(String palabra) {
        palabraConfigurada=palabra;
    }

    //
    @Override
    protected String elegirPalabra() throws PalabraInvalidaException {

        return palabraConfigurada;
    }

    //
    @Override
    public Modalidad getModalidad() {
        return Modalidad.PALABRA_FIJA;
    }
}
