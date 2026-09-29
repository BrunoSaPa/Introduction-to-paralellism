import java.awt.*;

public class Agent implements Runnable{
    private int x,y,px,py,pr=10,r=10;
    private Graphics graph;

    public Agent(int x, int y, Graphics graph){
        this.x = x;
        this.y = y;
        this.px = x;
        this.py = y;
        this.graph = graph;


    }

    private void drawCircle(){
        graph.fillOval(x,y,pr,r);
    }


    private Color getRandomColor() {
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);
        return new Color(red, green, blue);
    }

    private void drawStickman() {
        Graphics2D g2d = (Graphics2D) graph;

        // Make lines thick/chunky (default stroke is 1.0f)
        g2d.setStroke(new BasicStroke(4.0f));

        // Reduced horizontal spans to make the frame thinner
        int headRadius = 10;
        int bodyLength = 28;
        int armSpan = 8;  // Reduced width
        int legSpan = 8;  // Reduced width

        int neckY = y - bodyLength;
        int shoulderY = neckY + 8;
        int hipY = y;

        // 1. Head
        g2d.setColor(getRandomColor());
        g2d.drawOval(x - headRadius / 2, y - headRadius - bodyLength, headRadius, headRadius);

        // 2. Body (Torso)
        g2d.setColor(getRandomColor());
        g2d.drawLine(x, neckY, x, y);

        // 3. Left Arm
        g2d.setColor(getRandomColor());
        g2d.drawLine(x - armSpan, shoulderY + 8, x, shoulderY);

        // 4. Right Arm
        g2d.setColor(getRandomColor());
        g2d.drawLine(x, shoulderY, x + armSpan, shoulderY + 8);

        // 5. Left Leg
        g2d.setColor(getRandomColor());
        g2d.drawLine(x, hipY, x - legSpan, hipY + 18);

        // 6. Right Leg
        g2d.setColor(getRandomColor());
        g2d.drawLine(x, hipY, x + legSpan, hipY + 18);
    }


    private void work(){
        while(true){
            px = x;
            py = y;
            pr = r;
            int dirX = ((int) (Math.random()*10)<5) ? 1 : -1;
            int dirY = ((int) (Math.random()*10)<5) ? 1 : -1;

            int ranX = ((int) (Math.random()*15))*dirX;
            int ranY = ((int) (Math.random()*15))*dirX;

            x+=ranX;
            y+=ranY;

            try{
                Thread.sleep(250);
            }catch(InterruptedException ex){
                System.err.println(ex);
            }

            drawStickman();
        }
    }


    @Override
    public void run(){
        work();
    }
}
