package modelo;
/**
 *
 * @author kendr
 */
public enum EtapaAhorcado {
    VACIO,
    CABEZA,
    TORSO,
    BRAZO_IZQ,
    BRAZO_DER,
    PIERNA_IZQ,
    PIERNA_DER;

    public static EtapaAhorcado dePorErrores(int errores) {

        if(errores<0){
            errores=0;
        }
        if(errores>6){
            errores=6;
        }

        return values()[errores];
    }
}

