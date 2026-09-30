import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;

public class Window extends JFrame {

    public Window(String name) {
        this.setTitle(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 600);
        this.setLayout(new BorderLayout());

        //id campo de texto
        JTextField txtThreadId = new JTextField(10);
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("kill thread # "));
        controlPanel.add(txtThreadId);

        // Se pasa el JTextField al canvas para que lea el valor al hacer clic derecho
        MyCanvas canvas = new MyCanvas(txtThreadId);

        // Disposición de componentes en la ventana
        this.add(controlPanel, BorderLayout.NORTH);
        this.add(canvas, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Window("main");
    }
}