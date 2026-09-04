import javax.swing.*;

public class Orchestrator extends JFrame{

    private JFrame frame;
    private JButton button;
    private JButton button2;
    private JPanel panel;
    private JTextArea textArea;
    private JTextArea textArea;
    private JLabel label;
    private JLabel label2;

    Orchestrator(String name){
        this.setTitle("Window # "  + name);
        this.setSize(300,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        button = new JButton();
        label = new JLabel("Ammount of threads to add");
        textArea = new JTextArea();

        panel.add(button);
        panel.add(textArea)


    }
    
}
