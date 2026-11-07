package modelo;

import excepciones.LetraInvalidaException;
import excepciones.LetraRepetidaException;
import excepciones.PartidaTerminadaException;
import excepciones.PalabraInvalidaException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author kendr
 */
public abstract class JuegoBase implements JuegoAhorcado, Serializable {
    private static final long serialVersionUID=1L;

    // Atributos
    protected String palabraSecreta;
    protected char[] palabraMostrada;
    protected int intentosRestantes;

    protected static final int MAX_INTENTOS=6;

    protected List<Character> letrasAcertadas;
    protected List<Character> letrasErradas;

    protected EtapaAhorcado etapa;
    protected EstadoPartida estado;

    
    public JuegoBase(){

        letrasAcertadas=new ArrayList<>();
        letrasErradas=new ArrayList<>();

        intentosRestantes=MAX_INTENTOS;
        etapa=EtapaAhorcado.VACIO;
        estado=EstadoPartida.JUGANDO;
    }

    // Poner palabra secreta
    @Override
    public void establecerPalabraSecreta(String palabra)
            throws PalabraInvalidaException {

        if (palabra==null) {
            throw new PalabraInvalidaException( "La palabra no puede estar vacia");
        }

        String normalizada = UtilTexto.normalizar(palabra);

        if (normalizada.length()<3||normalizada.length()>15) {
            throw new PalabraInvalidaException( "La palabra debe tener entre 3 y 15 letras");
        }

        for (int i=0;i<normalizada.length(); i++) {

            if (!UtilTexto.esLetra(normalizada.charAt(i))) {
                throw new PalabraInvalidaException(
                        "La palabra no puede contener espacios, numeros o simbolos"
                );
            }
        }

        palabraSecreta=normalizada;

        palabraMostrada=new char[palabraSecreta.length()];

        for (int i=0;i<palabraMostrada.length; i++) {
            palabraMostrada[i]='_';
        }

        intentosRestantes=MAX_INTENTOS;

        letrasAcertadas.clear();
        letrasErradas.clear();

        etapa=EtapaAhorcado.VACIO;
        estado=EstadoPartida.JUGANDO;
    }

    // cuando se ingresa una letra
    @Override
    public ResultadoLetra ingresarLetra(String entrada)
            throws LetraInvalidaException,
                   LetraRepetidaException,
                   PartidaTerminadaException {

        // Paso 1
        if (estado != EstadoPartida.JUGANDO) {
            throw new PartidaTerminadaException("La partida termino, presione Jugar de nuevo"
            );
        }

        // Paso 2
        if (entrada==null||entrada.length()!=1) {
            throw new LetraInvalidaException("Ingrese una letra");
        }

        // Paso 3
        
        
        String letraNormalizada=UtilTexto.normalizar(entrada);

        char letra=letraNormalizada.charAt(0);

        if (!UtilTexto.esLetra(letra)) {
            throw new LetraInvalidaException(
                    "Ingrese una letra"
            );
        }
                // Paso 4
        if (letrasAcertadas.contains(letra)||letrasErradas.contains(letra)) {
                throw new LetraRepetidaException("La letra "+letra+" ya fue ingresada, pruebe con otra", letra);
        }

        // Paso 5
        ResultadoLetra resultado;
        if (palabraSecreta.indexOf(letra)>=0) {
            letrasAcertadas.add(letra);
            revelarLetra(letra, 0);
            resultado = ResultadoLetra.CORRECTO;

        } else {

            letrasErradas.add(letra);
            intentosRestantes--;
            etapa=EtapaAhorcado.dePorErrores(MAX_INTENTOS-intentosRestantes);
            resultado=ResultadoLetra.ERROR;
        }

        // Paso 7
        if (haGanado(0)){
            estado=EstadoPartida.GANADA;
        } 
        else if (haPerdido()){
            estado = EstadoPartida.PERDIDA;
        }
        return resultado;
    }

    // revelar letras
    protected void revelarLetra(char letra, int posicion) {
        if (posicion>=palabraSecreta.length()) {
            return;
        }

        if (palabraSecreta.charAt(posicion)==letra) {
            palabraMostrada[posicion]=letra;
        }
        revelarLetra(letra, posicion+1);
    }

       //victoria
    protected boolean haGanado(int posicion) {
        if (posicion>=palabraSecreta.length()) {
            return true;
        }
  
        if (palabraMostrada[posicion]!=palabraSecreta.charAt(posicion)) {
            return false;
        }
        return haGanado(posicion+1);
    }

    //derrota
    protected boolean haPerdido() {
        return intentosRestantes<=0;
    }

    //reinicio
    @Override
    public void reiniciar()throws PalabraInvalidaException {

        String nuevaPalabra=elegirPalabra();

        establecerPalabraSecreta(nuevaPalabra);
    }

   
    @Override
    public String getPalabraMostrada() {
        return new String(palabraMostrada);
    }

    @Override
    public int getIntentosRestantes() {
        return intentosRestantes;
    }

    @Override
    public List<Character> getLetrasAcertadas() {
        return Collections.unmodifiableList(
                new ArrayList<>(letrasAcertadas)
        );
    }

    @Override
    public List<Character> getLetrasErradas() {
        return Collections.unmodifiableList(
                new ArrayList<>(letrasErradas)
        );
    }

    @Override
    public EtapaAhorcado getEtapa() {
        return etapa;
    }

    @Override
    public EstadoPartida getEstado() {
        return estado;
    }

    @Override
    public String getPalabraSecreta() {
        return palabraSecreta;
    }

    // Métodos abstractos
    protected abstract String elegirPalabra()
            throws PalabraInvalidaException;

    @Override
    public abstract Modalidad getModalidad();
}
