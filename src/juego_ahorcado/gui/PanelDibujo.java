package juego_ahorcado.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import modelo.EtapaAhorcado;

/**
 * Panel dibujar
 *
 */
public class PanelDibujo extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    
    private static final double ANCHO_LOGICO = 200;
    private static final double ALTO_LOGICO = 260;

    private static final Color COLOR_HORCA = new Color(110, 70, 30);
    private static final Color COLOR_CUERDA = new Color(150, 120, 70);
    private static final Color COLOR_CUERPO = new Color(30, 30, 30);

    private EtapaAhorcado etapa;

    public PanelDibujo() {
        this.etapa = EtapaAhorcado.VACIO;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(240, 312));
        setMinimumSize(new Dimension(120, 156));
    }

    public EtapaAhorcado getEtapa() {
        return etapa;
    }

    public void setEtapa(EtapaAhorcado etapa) {
        this.etapa = (etapa == null) ? EtapaAhorcado.VACIO : etapa;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

            double escala = Math.min(getWidth() / ANCHO_LOGICO, getHeight() / ALTO_LOGICO);
            double desplazamientoX = (getWidth() - ANCHO_LOGICO * escala) / 2;
            double desplazamientoY = (getHeight() - ALTO_LOGICO * escala) / 2;
            g2.translate(desplazamientoX, desplazamientoY);
            g2.scale(escala, escala);

            dibujarHorca(g2);
            dibujarCuerpo(g2);
        } finally {
            g2.dispose();
        }
    }

    private void dibujarHorca(Graphics2D g2) {
        g2.setColor(COLOR_HORCA);
        g2.setStroke(new BasicStroke(7f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.draw(new Line2D.Double(20, 240, 150, 240));   // base
        g2.draw(new Line2D.Double(50, 240, 50, 20));     // poste
        g2.draw(new Line2D.Double(50, 20, 130, 20));     // viga
        g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.draw(new Line2D.Double(50, 60, 90, 20));      // refuerzo

        g2.setColor(COLOR_CUERDA);
        g2.setStroke(new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.draw(new Line2D.Double(130, 20, 130, 55));    // cuerda
    }

    private void dibujarCuerpo(Graphics2D g2) {
        g2.setColor(COLOR_CUERPO);
        g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        if (alcanzo(EtapaAhorcado.CABEZA)) {
            g2.draw(new Ellipse2D.Double(110, 55, 40, 40));
        }
        if (alcanzo(EtapaAhorcado.TORSO)) {
            g2.draw(new Line2D.Double(130, 95, 130, 160));
        }
        if (alcanzo(EtapaAhorcado.BRAZO_IZQ)) {
            g2.draw(new Line2D.Double(130, 112, 100, 142));
        }
        if (alcanzo(EtapaAhorcado.BRAZO_DER)) {
            g2.draw(new Line2D.Double(130, 112, 160, 142));
        }
        if (alcanzo(EtapaAhorcado.PIERNA_IZQ)) {
            g2.draw(new Line2D.Double(130, 160, 105, 208));
        }
        if (alcanzo(EtapaAhorcado.PIERNA_DER)) {
            g2.draw(new Line2D.Double(130, 160, 155, 208));
        }
    }

    private boolean alcanzo(EtapaAhorcado parte) {
        return etapa.compareTo(parte) >= 0;
    }
}
