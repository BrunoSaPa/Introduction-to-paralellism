import javax.swing.*;

public class Window{

    public Window(){
        createWindow();
    }
    public void createWindow(){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(800,600);

        //add label
        JLabel label = new JLabel("hee");
//        window.getContentPane().add(label);
        JTextArea textArea = new JTextArea();
        window.add(textArea);
        window.setVisible(true);

        while(true){
//            System.out.println("# of threads are: " + Thread.activeCount());
            textArea.setText("# of threads are: " + textArea.getText() + " " + Thread.activeCount());
            textArea.setText("\n");
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }


    }

    public static void main(String[] args){
        Window window = new Window();
//        Window window2 = new Window();
//        Window window3 = new Window();
//        Window window4 = new Window();
//        Window window5 = new Window();

    }
}