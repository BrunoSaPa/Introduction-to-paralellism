import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableWindow extends JFrame {


    public TableWindow(int rows, int columns,int numberOfThreads){
        String[] titles = new String[columns];

        this.setSize(400,400);

        for(int i = 0; i < columns; i++){
            titles[i] = "Collumn " + (i+1);
        }

        DefaultTableModel model = new DefaultTableModel(titles,rows);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                model.setValueAt(0, r, c);
            }
        }

        JTable table = new JTable(model);
        this.add(new JScrollPane(table));

        this.setVisible(true);

        List<Thread> workerThreads = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = new Thread(new CellUpdater(model, rows, columns));
            thread.setDaemon(true); // Terminate threads automatically when window closes
            workerThreads.add(thread);
            thread.start();
        }
        new MonitorWindow(workerThreads);
    }
}
