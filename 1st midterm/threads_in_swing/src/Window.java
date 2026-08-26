import javax.swing.*;

public class Window extends Thread {

    public Window() {
        start();
    }

    @Override
    public void run() {
        createWindow();
    }

    public void createWindow() {
        JFrame window = new JFrame();

        //window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        window.setSize(800, 600);

        // Add label
        JLabel label = new JLabel("hee");
        // window.getContentPane().add(label);

        JTextArea textArea = new JTextArea();
        window.add(textArea);
        window.setVisible(true);

        while (true) {
            textArea.append("\n# of threads are: " + Thread.activeCount());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        Window window1 = new Window();
        Window window2 = new Window();
//        Window window3 = new Window();
//        Window window4 = new Window();
//        Window window5 = new Window();
    }
}