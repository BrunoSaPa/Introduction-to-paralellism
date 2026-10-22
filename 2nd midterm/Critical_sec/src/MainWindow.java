import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    JTextField numberOfThreads = new JTextField(5);
    JTextField timedCritical = new JTextField(5);
    private TableWindow currentWindow; // Stores the active instance

    public MainWindow(){

        this.setLayout(new FlowLayout());
        this.setSize(400,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(new JLabel("# threads: "));
        this.add(numberOfThreads);
        this.add(new JLabel("ms in critical section: "));
        this.add(timedCritical);

        JButton buttonE = new JButton("exec");
        this.add(buttonE);

        JButton buttonK = new JButton("kill");
        this.add(buttonK);

        buttonE.addActionListener(e-> {
            int threadCount = Integer.parseInt(this.numberOfThreads.getText());
            int time = Integer.parseInt(this.timedCritical.getText());

            currentWindow = new TableWindow(threadCount, time);
        });

        buttonK.addActionListener(e ->{
            if (currentWindow != null) {
                currentWindow.killAllThreads();
            }
        });

        this.setVisible(true);
    }
}