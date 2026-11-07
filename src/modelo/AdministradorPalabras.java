package modelo;

import excepciones.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;

public class AdministradorPalabras implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> palabras;
    private Random random;

    public AdministradorPalabras() {
        this.palabras = new ArrayList<>();
        this.random = new Random();

        palabras.add("BALEADA");
        palabras.add("CASACA");
        palabras.add("CIPOTES");
        palabras.add("TANGAMANDAPIO");
        palabras.add("ENCURTIDO");
        palabras.add("CATRACHITAS");
        palabras.add("SIGUATEPEQUE");
        palabras.add("MONTAÑA");
        palabras.add("FRIJOLES");
        palabras.add("GUACAL");
    }

    public void agregar(String palabra) throws PalabraInvalidaException, PalabraDuplicadaException {
        if (!UtilTexto.esPalabraValida(palabra)) {
            throw new PalabraInvalidaException("La palabra debe tener entre 3 y 15 letras, sin espacios ni numeros.");
        }

        String normalizada = UtilTexto.normalizar(palabra);

        if (existe(normalizada, 0)) {
            throw new PalabraDuplicadaException("La palabra «" + normalizada + "» ya existe en la lista.");
        }

        palabras.add(normalizada);
    }

    public String obtenerAleatoria() {
        int indice = random.nextInt(palabras.size());
        return palabras.get(indice);
    }

    private boolean existe(String palabra, int indice) {
        if (indice == palabras.size()) {
            return false;
        }
        if (palabras.get(indice).equals(palabra)) {
            return true;
        }

        return existe(palabra, indice + 1);
    }

    public int cantidad() {
        return palabras.size();
    }

    public List<String> getPalabras() {
        return Collections.unmodifiableList(palabras);
    }


}
