import javax.swing.*;

public class Orchestrator extends JFrame{

    private JButton button;
    private JButton button2;

    private JPanel panel;

    private JTextArea textArea;
    private JTextArea textArea2;

    private JLabel label;
    private JLabel label2;


    private FindingPrimes[] workers;


    Orchestrator(){

            this.setSize(500, 150);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Orchestrator");

            panel = new JPanel();

            // First input
            label = new JLabel("Amount of threads to add\n");
            textArea = new JTextArea(1, 10);
            button = new JButton("Add Threads");

            // Second input
            label2 = new JLabel("kill thread # ");
            textArea2 = new JTextArea(1, 10);
            button2 = new JButton("kill");

            button2.setEnabled(false);


            //add
            button.addActionListener(e ->{
                int number = Integer.parseInt(textArea.getText());
                createThreads(number);
                button.setEnabled(false);
                button2.setEnabled(true);
            });

            //kill
            button2.addActionListener(e ->{
                int index = Integer.parseInt(textArea2.getText());
               killThread(index);
            });


            panel.add(label);
            panel.add(textArea);
            panel.add(button);

            panel.add(label2);
            panel.add(textArea2);
            panel.add(button2);


            this.add(panel);
            this.setVisible(true);


    }

    private void createThreads(int number){
        workers = new FindingPrimes[number];
        for(int i = 0; i < number; i++){
            workers[i] = new FindingPrimes("Thread # " + i);

            Thread th = new Thread(workers[i]);
            th.start();
        }
    }

    private void killThread(int index){
        workers[index].alive = false;
    }
    
}
