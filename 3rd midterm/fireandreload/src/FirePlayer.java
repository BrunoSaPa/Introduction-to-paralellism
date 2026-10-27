import java.util.Random;

public class FirePlayer extends Thread {
    private final Rifle rifle;
    private final int N;

    public FirePlayer(Rifle rifle, int nIteraciones, String nombre) {
        super(nombre);
        this.rifle = rifle;
        this.N = nIteraciones;
        this.setPriority(Thread.MAX_PRIORITY); //set as high priority
    }

    @Override
    public void run() {
        Random random = new Random();

        for (int i = 0; i < N; i++) {
            //random number of bullets to fire from 1 to 15
            int bulletsToFire = random.nextInt(15) + 1;
            rifle.fire(bulletsToFire);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}