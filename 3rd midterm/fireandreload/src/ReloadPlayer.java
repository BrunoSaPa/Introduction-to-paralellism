public class ReloadPlayer extends Thread {
    private final Rifle rifle;
    private final int N;

    public ReloadPlayer(Rifle rifle, int nIteraciones, String nombre) {
        super(nombre);
        this.rifle = rifle;
        this.N = nIteraciones;
        this.setPriority(Thread.MIN_PRIORITY);
    }

    @Override
    public void run() {
        for (int i = 0; i < N; i++) {
            rifle.reload();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}