import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class MonitorWindow extends JFrame {

    private final DefaultTableModel monitorModel;
    private final List<Thread> targetThreads;
    private final Timer refreshTimer;

    public MonitorWindow(List<Thread> threads) {
        this.targetThreads = threads;

        this.setTitle("Thread State Monitor");
        this.setSize(350, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Define columns for the monitor table
        String[] columns = {"Thread ID", "Thread Name", "State"};
        monitorModel = new DefaultTableModel(columns, 0);

        JTable monitorTable = new JTable(monitorModel);
        this.add(new JScrollPane(monitorTable));

        // Periodically refresh the table every 100ms
        refreshTimer = new Timer(100, e -> updateThreadStates());
        refreshTimer.start();

        // Stop timer when window is closed
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                refreshTimer.stop();
            }
        });

        this.setVisible(true);
    }

    private void updateThreadStates() {
        monitorModel.setRowCount(0); // Clear current rows

        for (Thread thread : targetThreads) {
            monitorModel.addRow(new Object[]{
                    thread.getId(),
                    thread.getName(),
                    thread.getState() // Retrieves RUNNABLE, TIMED_WAITING, etc.
            });
        }
    }
}