
public class Main {
    void main() {
        DataCenter dc = new DataCenter();
        Hilo[] hilos = new Hilo[5];
        System.out.println("Valor al comenzar: " + dc.getValor() );
        for(int i=0;i<5;i++){
            hilos[i] = new Hilo(dc, "Thread" + Integer.toString(i));
            hilos[i].start();
        }
        for(int i=0;i<5;i++){
            try{
                hilos[i].join();
            }catch(InterruptedException ie){
                System.out.println("Thread " + Integer.toString(i) + " was interrupted");
            }
        }
        System.out.println("Valor al terminar: " + dc.getValor() );
    }
}

