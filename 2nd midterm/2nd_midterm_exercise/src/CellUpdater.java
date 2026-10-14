import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Random;

public class CellUpdater implements Runnable{

    private final DefaultTableModel model;
    private final int totalRows;
    private final int totalCols;
    private final Random random = new Random();
    private volatile boolean running = true;

    public CellUpdater(DefaultTableModel model, int totalRows, int totalCols) {
        this.model = model;
        this.totalRows = totalRows;
        this.totalCols = totalCols;
    }

    public void stopWorker() {
        this.running = false;
    }

    public void work(){
        while (running) {
            // Pick a random cell
            int row = random.nextInt(totalRows);
            int col = random.nextInt(totalCols);

            // Swing UI updates must run on the Event Dispatch Thread (EDT)
            SwingUtilities.invokeLater(() -> {
                Object currentValue = model.getValueAt(row, col);
                int count = 0;

                if (currentValue != null && !currentValue.toString().isEmpty()) {
                    try {
                        count = Integer.parseInt(currentValue.toString());
                    } catch (NumberFormatException e) {
                        count = 0;
                    }
                }

                //increment and update table
                model.setValueAt(count + 1, row, col);
            });

            // Pause briefly so updates are visible
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    @Override
    public void run(){
        work();
    }
}
