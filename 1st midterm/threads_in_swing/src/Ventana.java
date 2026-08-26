import javax.swing.*;

public class Ventana extends JFrame {
    private JButton button;
    private JPanel panel;
    private JTextField textField;


    Ventana(String name){
        this.setTitle(name);
        this.setSize(300,200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel = new JPanel();
        button = new JButton("button");
        textField = new JTextField(20);

        Listener listener = new Listener(textField);
        button.addActionListener(listener);
        panel.add(button);
        panel.add(textField);

        this.add(panel);
        this.setVisible(true);



    }

}
