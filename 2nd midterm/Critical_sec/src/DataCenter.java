public class DataCenter {
    private final int[][] valores;

    public DataCenter(int filas, int columnas) {
        valores = new int[filas][columnas];
    }

    public synchronized int incrementar(int fila, int columna) {
        valores[fila][columna]++;
        return valores[fila][columna];
    }

    public synchronized int obtener(int fila, int columna) {
        return valores[fila][columna];
    }
}