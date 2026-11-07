package juego_ahorcado.gui.sonido;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class GestorSonido   {

    private static final float FRECUENCIA_MUESTREO = 44100;
    private static final double VOLUMEN = 0.3;

    private final AudioFormat formato;
    private boolean silenciado;
    private volatile boolean disponible;

    public GestorSonido() {
        formato = new AudioFormat(FRECUENCIA_MUESTREO, 16, 1, true, false);
        silenciado = false;

        try {
            AudioSystem.getSourceDataLine(formato);
            disponible = true;
        } catch (LineUnavailableException e) {
            disponible = false;
        } catch (IllegalArgumentException e) {
            disponible = false;
        }
    }

    public void reproducir(EfectoSonido efecto) {
        if (silenciado || !disponible) {
            return;
        }

        final EfectoSonido efectoFinal = efecto;

        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                reproducirEnHilo(efectoFinal);
            }
        });
        hilo.start();
    }

    private void reproducirEnHilo(EfectoSonido efecto) {
        try {
            SourceDataLine linea = AudioSystem.getSourceDataLine(formato);
            linea.open(formato);
            linea.start();

            int[] frecuencias = efecto.getFrecuencias();
            int[] duraciones = efecto.getDuraciones();

            for (int i = 0; i < frecuencias.length; i++) {
                byte[] datos = generarTono(frecuencias[i], duraciones[i]);
                linea.write(datos, 0, datos.length);
            }

            linea.drain();
            linea.close();
        } catch (LineUnavailableException e) {
            disponible = false;
        }
    }

    private byte[] generarTono(int frecuencia, int duracionMs) {
        int muestras = (int) (FRECUENCIA_MUESTREO * duracionMs / 1000);
        byte[] datos = new byte[muestras * 2];

        for (int i = 0; i < muestras; i++) {
            double angulo = 2 * Math.PI * frecuencia * i / FRECUENCIA_MUESTREO;
            short valor = (short) (Math.sin(angulo) * VOLUMEN * Short.MAX_VALUE);

            datos[2 * i] = (byte) (valor & 0xFF);
            datos[2 * i + 1] = (byte) ((valor >> 8) & 0xFF);
        }

        return datos;
    }

    public void setSilenciado(boolean silenciado) {
        this.silenciado = silenciado;
    }

    public boolean isSilenciado() {
        return silenciado;
    }

    public boolean isDisponible() {
        return disponible;
    }

}
