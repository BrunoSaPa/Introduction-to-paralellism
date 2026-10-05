import java.awt.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Agent implements Runnable {
    private int id;
    private int x, y;
    private int r = 30;
    private Color color;
    private MyCanvas canvas;
    private volatile boolean running = true;
    private final List<Point> posiciones;

    public Agent(int id, int x, int y, MyCanvas canvas) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.canvas = canvas;
        this.color = getRandomColor(); // Asigna un color fijo por agente
        this.posiciones = Collections.synchronizedList(new ArrayList<>());
        this.posiciones.add(new Point(x, y));
    }

    public int getId() {
        return id;
    }

    public boolean isRunning() {
        return running;
    }

    public void stopAgent() {
        this.running = false;
    }

    private Color getRandomColor() {
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);
        return new Color(red, green, blue);
    }

    // Redibuja todos los puntos acumulados usando el contexto Graphics de paint()
    public void redrawHistory(Graphics g) {
        List<Point> copiaPosiciones;
        synchronized (posiciones) {
            copiaPosiciones = new ArrayList<>(posiciones);
        }

        for (Point p : copiaPosiciones) {
            drawCircleAt(g, p.x, p.y);
        }
    }

    private void drawCircleAt(Graphics g, int posX, int posY) {
        if (g == null) return;

        // Dibujar círculo
        g.setColor(color);
        g.fillOval(posX, posY, r, r);

        // Dibujar ID del hilo
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        FontMetrics fm = g.getFontMetrics();
        String text = String.valueOf(id);
        int textX = posX + (r - fm.stringWidth(text)) / 2;
        int textY = posY + (r + fm.getAscent() - fm.getDescent()) / 2;

        g.drawString(text, textX, textY);
    }

    private void guardarPosicionesEnArchivo() {
        String nombreArchivo = "posiciones_thread_" + id + ".txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            writer.println("ThreadID " + id + ":");
            synchronized (posiciones) {
                for (int i = 0; i < posiciones.size(); i++) {
                    Point p = posiciones.get(i);
                    writer.println("Paso " + (i + 1) + ": X=" + p.x + ", Y=" + p.y);
                }
            }
            System.out.println("Archivo '" + nombreArchivo + "' guardado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo para el thread " + id + ": " + e.getMessage());
        }
    }

    private void work() {
        if (canvas != null) canvas.repaint();

        while (running) {
            int dirX = ((int) (Math.random() * 10) < 5) ? 1 : -1;
            int dirY = ((int) (Math.random() * 10) < 5) ? 1 : -1;

            int ranX = ((int) (Math.random() * 15)) * dirX;
            int ranY = ((int) (Math.random() * 15)) * dirY;

            x += ranX;
            y += ranY;

            posiciones.add(new Point(x, y));

            // Solicita al canvas redibujar la pantalla completa
            if (canvas != null) {
                canvas.repaint();
            }

            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        guardarPosicionesEnArchivo();
    }

    @Override
    public void run() {
        work();
    }
}