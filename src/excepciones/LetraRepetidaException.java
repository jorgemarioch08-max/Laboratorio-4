package excepciones;

public class LetraRepetidaException extends AhorcadoException {

    private final char letra;
    private static final long serialVersionUID = 1L;

    public LetraRepetidaException(String message, char letra) {
        super(message);

        this.letra = letra;
    }

    public char getLetra() {
        return letra;
    }

}
