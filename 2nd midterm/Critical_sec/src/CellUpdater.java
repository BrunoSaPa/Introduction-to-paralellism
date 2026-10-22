import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.concurrent.locks.ReentrantLock;

public class CellUpdater implements Runnable{

    private final DefaultTableModel model;
    private final int threadRow;
    private final int sleepTime;
    private volatile boolean running = true;
    private final DataCenter datos;
    private final ReentrantLock lock;
    private volatile int threadState = 0;
    private double x, y, dx, dy;




    public CellUpdater(DefaultTableModel model, int threadRow, int sleepTime, DataCenter data, ReentrantLock lock) {
        this.model = model;
        this.threadRow = threadRow;
        this.sleepTime = sleepTime;
        this.datos = data;
        this.lock = lock;
        this.x = Math.random() * 400;
        this.y = Math.random() * 400;
        this.dx = (Math.random() > 0.5 ? 1 : -1) * (2 + Math.random() * 3);
        this.dy = (Math.random() > 0.5 ? 1 : -1) * (2 + Math.random() * 3);
    }

    public int getThreadState() { return threadState; }
    public int getThreadId() { return threadRow; }
    public double getX() { return x; }
    public double getY() { return y; }

    public void stopWorker() {
        this.running = false; // Breaks the while-loop
    }

    public void move(int width, int height) {
        if (threadState == 3) return; // Dead threads stop moving
        x += dx;
        y += dy;
        // Bounce off edges
        if (x < 0 || x > width - 30) dx *= -1;
        if (y < 0 || y > height - 30) dy *= -1;
    }

    public void work(){
        while (running) {
            // waiting state
            threadState = 0;
            SwingUtilities.invokeLater(() -> model.setValueAt("X", threadRow, 2));

            // critical section
            lock.lock();
            try {
                if (!running) break;

                threadState = 1;
                SwingUtilities.invokeLater(() -> {
                    model.setValueAt("", threadRow, 2);
                    model.setValueAt("X", threadRow, 3);
                });

                try {
                    Thread.sleep(this.sleepTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }

                int currentAccesses = datos.incrementar(threadRow, 0);
                SwingUtilities.invokeLater(() -> {
                    model.setValueAt(currentAccesses, threadRow, 1);
                    model.setValueAt("", threadRow, 3);
                });
            } finally {
                lock.unlock();
            }
            // Yield briefly so other threads can take the lock
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        // dying
        threadState = 2;
        SwingUtilities.invokeLater(() -> {
            model.setValueAt("", threadRow, 2);
            model.setValueAt("", threadRow, 3);
            model.setValueAt("X", threadRow, 4);
        });

        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // dead
        threadState = 3;
        SwingUtilities.invokeLater(() -> {
            model.setValueAt("", threadRow, 4);
            model.setValueAt("X", threadRow, 5);
        });
    }

    @Override
    public void run(){
        work();
    }
}