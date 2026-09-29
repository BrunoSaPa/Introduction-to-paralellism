import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Interrupting extends Thread {
    private int id;
    private String threadName;
    private boolean alive = true;
    private volatile boolean wasInterrupted = false;


    // Watcher thread for displaying thread states in a separate JFrame
    private static class StateWatcher extends Thread {
        private List<Interrupting> list;
        private int t_milli;
        private JTextArea textArea;
        private JTextArea interruptArea;


        public StateWatcher(List<Interrupting> threadList, int milli) {
            this.list = threadList;
            this.t_milli = milli;

            JFrame frame = new JFrame("Live Thread States");
            frame.setSize(400, 500);
            frame.setLocation(0, 0);

            JPanel panel = new JPanel(new GridLayout(2, 1));

            textArea = new JTextArea();
            textArea.setEditable(false);

            interruptArea = new JTextArea();
            interruptArea.setEditable(false);

            panel.add(new JScrollPane(textArea));
            panel.add(new JScrollPane(interruptArea));

            frame.add(panel);

            frame.setVisible(true);
        }


        @Override
        public void run() {
            while (!isInterrupted()) {
                try {

                    StringBuilder stateSB = new StringBuilder("Thread States:\n\n");
                    StringBuilder interruptSB = new StringBuilder("Interrupted Threads:\n\n");

                    for (Interrupting thread : this.list) {

                        // Current state
                        stateSB.append(thread.getName())
                                .append(" is in state ")
                                .append(thread.getState())
                                .append("\n");

                        // Has this thread ever been interrupted?
                        if (thread.wasInterrupted) {
                            interruptSB.append(thread.getName())
                                    .append("\n");
                        }
                    }

                    SwingUtilities.invokeLater(() -> {
                        textArea.setText(stateSB.toString());
                        interruptArea.setText(interruptSB.toString());
                    });

                    Thread.sleep(this.t_milli);

                } catch (InterruptedException e) {
                    System.out.println("State watcher interrupted, exiting.");
                    return;
                }
            }
        }
    }




    Interrupting(int id, String threadName) {
        this.id = id;
        this.threadName = threadName;
    }


    public void run() {
        // Create a Window with random sizex and sizey
        int sizeX = 200 + (int)(Math.random() * 300);
        int sizeY = 200 + (int)(Math.random() * 300);

        JFrame frame = new JFrame(this.threadName);
        frame.setSize(sizeX, sizeY);
        frame.setLocation(100 + (int)(Math.random()*500), 100 + (int)(Math.random()*400));

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        frame.add(new JScrollPane(textArea));
        frame.setVisible(true);


        int num = 0;


        while(alive){
            try {
                num = (int)(Math.random() +2);


                String state = "prime";
                for(int i = 2;  i <= num/2; i++){
                    if(num % i == 0){
                        state = "not prime";
                        break;
                    }
                }


                String displayText = "Thread Name: " + this.id + ": " + " number: " + num + " is " + state +"\n\n";

                textArea.append(displayText);


                // Pause with Sleep()
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                wasInterrupted = true;
                textArea.append("\n\n"+this.threadName + " interrupted.\n\n" );
                try{
                    Thread.sleep(4000);
                }catch(Exception a){}

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }


    public static void main(String[] args) {


        JFrame frame = new JFrame("Main");
        frame.setSize(400, 400);


        JPanel panel = new JPanel();


        JButton addButton = new JButton("add");
        JButton killButton = new JButton("Kill");
        JButton interruptButton = new JButton("Interrupt");


        JTextArea addArea = new JTextArea(1,6);
        JTextArea killArea = new JTextArea(1,6);
        JTextArea interruptArea = new JTextArea(1,6);


        panel.add(addArea);
        panel.add(addButton);


        panel.add(killArea);
        panel.add(killButton);
        panel.add(interruptArea);
        panel.add(interruptButton);


        frame.add(panel);


        frame.setVisible(true);




        List<Interrupting> workers = new ArrayList<>();


        addButton.addActionListener( a -> {


            int n = Integer.parseInt(addArea.getText());
            // The main thread must call N threads
            for (int i = 0; i < n; i++) {
                Interrupting worker = new Interrupting(i, "Thread #" + i);
                workers.add(worker);
            }




            StateWatcher stateWatcher = new StateWatcher(workers, 250);


            stateWatcher.start();


            // Start worker threads
            for (Thread worker : workers) {
                worker.start();
                System.out.println("Starting worker" );
            }




        });




        killButton.addActionListener(a->{
            int n = Integer.parseInt(killArea.getText());


            for (Interrupting worker : workers) {
                if(n == worker.id){
                    worker.alive = false;
                }
            }
        });


        interruptButton.addActionListener(a->{
            int n = Integer.parseInt(interruptArea.getText());


            for (Interrupting worker : workers) {
                if(n == worker.id){
                    worker.interrupt();
                }
            }
        });


    }
}
