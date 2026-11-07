package excepciones;

public class PalabraDuplicadaException extends AhorcadoException {
    public PalabraDuplicadaException(String message) {
        super(message);
    }

    private static final long serialVersionUID = 1L;
}
