import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class TableWindow extends JFrame {

    private final DataCenter datos;
    private final List<CellUpdater> updaters = new ArrayList<>();

    public TableWindow(int threadCount, int time){
        this.datos = new DataCenter(threadCount, 1);
        String[] titles = {"Name", "Access", "Waiting", "Critical section", "Dying", "Dead"};

        this.setSize(700,500);

        DefaultTableModel model = new DefaultTableModel(titles, threadCount);
        ReentrantLock sharedLock = new ReentrantLock(true);

        for (int r = 0; r < threadCount; r++) {
            model.setValueAt("Thread " + r, r, 0); // Display the Thread name
            model.setValueAt(0, r, 1);
        }

        JTable table = new JTable(model);
        this.add(new JScrollPane(table));

        for (int i = 0; i < threadCount; i++) {
            // Pass the sharedLock to ensure only 1 accesses the CS at a time
            CellUpdater updater = new CellUpdater(model, i, time, datos, sharedLock);
            updaters.add(updater);

            Thread thread = new Thread(updater);
            thread.setDaemon(true);
            thread.start();
        }
        new CanvasWindow(updaters);
        this.setVisible(true);
    }

    public void killAllThreads() {
        for (CellUpdater updater : updaters) {
            updater.stopWorker();
        }
    }
}