
public class DataCenter {

    private int valor=0;

    public synchronized void incrementa() {
        valor++;
    }

    public synchronized void decrementa() {
        valor--;
    }

    public int getValor() {
        return valor;
    }

}
