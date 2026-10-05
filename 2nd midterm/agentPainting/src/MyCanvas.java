import javax.swing.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyCanvas extends Canvas implements MouseListener {
    private JTextField txtThreadId;
    private Map<Integer, Agent> agentesMap = new ConcurrentHashMap<>();
    private Map<Integer, Thread> hilosMap = new ConcurrentHashMap<>();
    private int contadorId = 1;

    public MyCanvas(JTextField txtThreadId) {
        this.txtThreadId = txtThreadId;
        this.setBackground(Color.GRAY);
        this.addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // Limpia el canvas con el color de fondo

        // Redibuja el historial completo de todos los agentes activos e inactivos
        for (Agent agente : agentesMap.values()) {
            agente.redrawHistory(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (SwingUtilities.isLeftMouseButton(me)) {
            int id = contadorId++;
            int posX = me.getX();
            int posY = me.getY();

            // Pasa el canvas actual al agente
            Agent agent = new Agent(id, posX, posY, this);
            Thread t = new Thread(agent);

            agentesMap.put(id, agent);
            hilosMap.put(id, t);

            t.start();
        } else if (SwingUtilities.isRightMouseButton(me)) {
            matarThread();
        }
    }

    private void matarThread() {
        if (txtThreadId == null) return;

        String textoInput = txtThreadId.getText().trim();
        if (textoInput.isEmpty()) return;

        try {
            int idBusqueda = Integer.parseInt(textoInput);
            Agent agente = agentesMap.get(idBusqueda);

            if (agente != null && agente.isRunning()) {
                agente.stopAgent();

                Thread hilo = hilosMap.get(idBusqueda);
                if (hilo != null && hilo.isAlive()) {
                    hilo.interrupt();
                }
            }
        } catch (NumberFormatException e) {
            // Entrada invalida
        }
    }

    @Override public void mousePressed(MouseEvent me) {}
    @Override public void mouseReleased(MouseEvent me) {}
    @Override public void mouseEntered(MouseEvent me) {}
    @Override public void mouseExited(MouseEvent me) {}
}