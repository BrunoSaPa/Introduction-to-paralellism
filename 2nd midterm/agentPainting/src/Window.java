import javax.swing.JFrame;

public class Window extends JFrame{

    Window(String name){
        this.setTitle(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,400);
        MyCanvas canvas = new MyCanvas();

        this.add(canvas);

        this.setVisible(true);
    }
}
