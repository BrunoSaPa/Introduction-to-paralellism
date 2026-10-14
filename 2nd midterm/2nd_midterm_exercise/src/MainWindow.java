import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    JTextField rows = new JTextField(5);
    JTextField collumns = new JTextField(5);


    public MainWindow(){

        this.setLayout(new FlowLayout());
        this.setSize(400,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(new JLabel("Rows: "));
        this.add(rows);
        this.add(new JLabel("Collumns: "));
        this.add(collumns);

        JButton button = new JButton("add table");
        this.add(button);

        button.addActionListener(e-> {
            System.out.println("Creating new window");
            int rows = Integer.parseInt(this.rows.getText());
            int collumns = Integer.parseInt(this.collumns.getText());
            int threadCount = 5;
            new TableWindow(rows,collumns,threadCount);
        });

        this.setVisible(true);


    }
}
