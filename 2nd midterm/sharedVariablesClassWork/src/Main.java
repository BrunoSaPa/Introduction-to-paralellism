public class Main {
    public static void main(String[] s) {
        DataCenter dc = new DataCenter();
        MyThread[] hilos = new MyThread[5];

        System.out.println("Valor al comenzar: " + dc.getValue() );

        for(int i=0;i<5;i++) {
            hilos[i] = new MyThread(dc, "Thread" + Integer.toString(i));
            hilos[i].start();
        }

        for(int i=0;i<5;i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException ie) {
            }
            System.out.println("Thread " + Integer.toString(i) + " was interrupted ");
        }
        System.out.println("Valor al terminar: " + dc.getValue() );
    }
}