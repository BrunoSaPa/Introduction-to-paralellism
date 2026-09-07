import javax.swing.*;
import java.util.Random;

public class FindingPrimes implements Runnable{

    private String name;
    public boolean alive = true;

    private JFrame window;
    private JScrollPane pane;
    private JTextArea textArea;

    private Random random;


    FindingPrimes(String name){
        this.name = name;
        this.random = new Random();
        createWindow();
    }

    public void createWindow(){

        window = new JFrame();

        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(400,400);
        window.setTitle(name);

        textArea = new JTextArea();
        pane = new JScrollPane(textArea);

        window.add(pane);

        window.setVisible(true);

        System.out.println("created "+name);

    }

    private void work(){
        while(alive){
            //calc rand number
            int rand = random.nextInt(1000) + 1;

            if(isPrime(rand)){
                textArea.append("Number: " + rand +" is prime\n");
            }else{
                textArea.append("Number: " + rand +" is NOT prime\n");
            }

            try{
                Thread.sleep(500);
            } catch(InterruptedException e){
                System.err.print(e);
            }
        }
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = n / 2; i > 1; i--) {
            if (n % i == 0) return false;
        }
        return true;
    }

    @Override
    public void run(){
        work();
    }




}