import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CanvasWindow extends JFrame {
    private final List<CellUpdater> updaters;

    public CanvasWindow(List<CellUpdater> updaters) {
        this.updaters = updaters;
        this.setTitle("Thread Canvas");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        CanvasPanel panel = new CanvasPanel();
        this.add(panel);

        Timer timer = new Timer(33, e -> panel.repaint());
        timer.start();

        this.setVisible(true);
    }

    private class CanvasPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // Clears the canvas so they don't leave a trail
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            for (CellUpdater u : updaters) {
                u.move(getWidth(), getHeight());

                // Color coding for the 4 states
                switch (u.getThreadState()) {
                    case 0: g2d.setColor(Color.YELLOW); break;     // Waiting
                    case 1: g2d.setColor(Color.GREEN); break;      // Critical Section
                    case 2: g2d.setColor(Color.ORANGE); break;     // Dying
                    case 3: g2d.setColor(Color.DARK_GRAY); break;  // Dead
                }

                int x = (int) u.getX();
                int y = (int) u.getY();
                int diameter = 30;

                g2d.fillOval(x, y, diameter, diameter);

                // Draw the thread number on the circle
                g2d.setColor(Color.BLACK);
                g2d.drawString(String.valueOf(u.getThreadId()), x + 10, y + 20);
            }
        }
    }
}