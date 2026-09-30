import java.awt.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Agent implements Runnable {
    private int id;
    private int x, y;
    private int px, py;
    private int r = 30;
    private Graphics graph;
    private volatile boolean running = true;
    private List<Point> posiciones;

    public Agent(int id, int x, int y, Graphics graph) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.px = x;
        this.py = y;
        this.graph = graph;
        this.posiciones = new ArrayList<>();
        this.posiciones.add(new Point(x, y));//sabe final position
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

    private void drawCircle() {
        if (graph == null) return;

        //draw circle
        graph.setColor(getRandomColor());
        graph.fillOval(x, y, r, r);

        //draw number
        graph.setColor(Color.WHITE);
        graph.setFont(new Font("Arial", Font.BOLD, 12));
        FontMetrics fm = graph.getFontMetrics();
        String text = String.valueOf(id);
        int textX = x + (r - fm.stringWidth(text)) / 2;
        int textY = y + (r + fm.getAscent() - fm.getDescent()) / 2;

        graph.drawString(text, textX, textY);
    }

    private Color getRandomColor() {
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);
        return new Color(red, green, blue);
    }

    private void guardarPosicionesEnArchivo() {
        String nombreArchivo = "posiciones_thread_" + id + ".txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            writer.println("ThreadID " + id + ":");
            for (int i = 0; i < posiciones.size(); i++) {
                Point p = posiciones.get(i);
                writer.println("Paso " + (i + 1) + ": X=" + p.x + ", Y=" + p.y);
            }
            System.out.println("Archivo '" + nombreArchivo + "' guardado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo para el thread " + id + ": " + e.getMessage());
        }
    }

    private void work() {
        // Dibujar posición inicial
        drawCircle();

        while (running) {
            px = x;
            py = y;

            int dirX = ((int) (Math.random() * 10) < 5) ? 1 : -1;
            int dirY = ((int) (Math.random() * 10) < 5) ? 1 : -1;

            int ranX = ((int) (Math.random() * 15)) * dirX;
            int ranY = ((int) (Math.random() * 15)) * dirY;

            x += ranX;
            y += ranY;

            // Registrar la nueva posición
            posiciones.add(new Point(x, y));

            drawCircle();

            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                // Si se interrumpe durante el sleep, rompemos el ciclo
                Thread.currentThread().interrupt();
                break;
            }
        }

        // create file
        guardarPosicionesEnArchivo();
    }

    @Override
    public void run() {
        work();
    }
}