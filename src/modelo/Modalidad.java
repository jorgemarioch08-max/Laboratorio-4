package modelo;

/**
 *
 * @author kendr
 */
public enum Modalidad {
    PALABRA_FIJA("Palabra fija"),
    AL_AZAR("Palabra al azar");

    private final String texto;

    Modalidad(String texto){
        this.texto=texto;
    }

    public String getTexto(){
        return texto;
    }
    
}
