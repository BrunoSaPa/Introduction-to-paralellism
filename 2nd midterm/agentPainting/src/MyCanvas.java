import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;


public class MyCanvas extends Canvas implements MouseListener{
    Graphics gall;

    public MyCanvas(){
        this.setBackground(Color.GRAY);
        //listener for the component
        this.addMouseListener(this);
    }

    public void paint(Graphics g){
        gall = g.create();
        //g.setColor(Color.WHITE);
        //g.fillOval(150,150,100,100);
    }

    @Override
    public void mouseClicked(MouseEvent me){
        System.out.println("Click");
        gall.setColor(Color.RED);
        int posX = me.getX();
        int posY = me.getY();

        gall.fillOval(posX-5,posY-5,10,10);
        gall.setColor(Color.WHITE);
        Agent agent = new Agent(posX,posY,gall);
        Thread t = new Thread(agent);
        t.start();
    }


    public void mousePressed(MouseEvent me){
    }

    public void mouseReleased(MouseEvent me){}

    public void mouseEntered(MouseEvent me){}

    public void mouseExited(MouseEvent me){}


}
