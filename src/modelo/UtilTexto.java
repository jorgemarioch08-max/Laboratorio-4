package modelo;

import java.text.Normalizer;

public class UtilTexto {

    public UtilTexto() {}

    public static String normalizar(String texto) {
        if (texto == null) {
            return "";
        }

        String mayuscula = texto.toUpperCase();
        String resultado = "";

        for (int i = 0; i < mayuscula.length(); i++) {
            char c = mayuscula.charAt(i);

            if (c == 'Ñ') {
                resultado += c;
            }
            else {
                String descompuesto = Normalizer.normalize(String.valueOf(c), Normalizer.Form.NFD);
                resultado += descompuesto.charAt(0);
            }
        }
        return resultado;
    }

    public static boolean esLetra(char c) {
        return (c >= 'A' && c <= 'Z') || c == 'Ñ';
    }

    public static boolean esPalabraValida(String palabra) {
        String normalizada = normalizar(palabra);

        if (normalizada.length() < 3 || normalizada.length() > 15) {
            return false;
        }

        for (int i = 0; i < normalizada.length(); i++) {
            if (!esLetra(normalizada.charAt(i))) {
                return false;
            }
        }

        return true;
    }

}
