import javax.swing.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class MyCanvas extends Canvas implements MouseListener {
    private Graphics gall;
    private JTextField txtThreadId;
    private Map<Integer, Agent> agentesMap = new HashMap<>();
    private Map<Integer, Thread> hilosMap = new HashMap<>();
    private int contadorId = 1;

    public MyCanvas(JTextField txtThreadId) {
        this.txtThreadId = txtThreadId;
        this.setBackground(Color.GRAY);
        this.addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        gall = g.create();
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (gall == null) {
            gall = getGraphics();
        }

        // clic izquierdo
        if (SwingUtilities.isLeftMouseButton(me)) {
            int id = contadorId++;
            int posX = me.getX();
            int posY = me.getY();

            Agent agent = new Agent(id, posX, posY, gall);
            Thread t = new Thread(agent);

            agentesMap.put(id, agent);
            hilosMap.put(id, t);

            t.start();
        }
        //clic derecho
        else if (SwingUtilities.isRightMouseButton(me)) {
            matarThread();
        }
    }

    private void matarThread() {
        if (txtThreadId == null) return;

        String textoInput = txtThreadId.getText().trim();

        if (textoInput.isEmpty()) {
            return;
        }

        try {
            int idBusqueda = Integer.parseInt(textoInput);
            Agent agente = agentesMap.get(idBusqueda);

            // Validar si no existe o si ya no está activo
            if (agente == null || !agente.isRunning()) {
                //do something if needed, i dont want to :)
            } else {
                //stop agent
                agente.stopAgent();

                // Interrumpir el hilo si se encuentra en sleep
                Thread hilo = hilosMap.get(idBusqueda);
                if (hilo != null && hilo.isAlive()) {
                    hilo.interrupt();
                }
                //thread finalizado
            }
        } catch (NumberFormatException e) {
            //number not valid
        }
    }

    @Override public void mousePressed(MouseEvent me) {}
    @Override public void mouseReleased(MouseEvent me) {}
    @Override public void mouseEntered(MouseEvent me) {}
    @Override public void mouseExited(MouseEvent me) {}
}