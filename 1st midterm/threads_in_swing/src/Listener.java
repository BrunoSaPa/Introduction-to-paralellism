import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener {

    private JTextField textField;

    Listener (JTextField textField) {
        this.textField = textField;
    }

    public void actionPerformed(ActionEvent e) {
        String msg  = "write sum pls";
        if (!textField.getText().equals("")) msg = textField.getText();
        JOptionPane.showMessageDialog(null,msg,"Message",JOptionPane.INFORMATION_MESSAGE);
    }
}
