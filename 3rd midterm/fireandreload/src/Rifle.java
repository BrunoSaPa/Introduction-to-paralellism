public class Rifle {
    private int balas;

    public Rifle(int balasIniciales) {
        this.balas = balasIniciales;
    }

    public synchronized void fire(int n) {
        System.out.println(Thread.currentThread().getName() + " intentando disparar " + n + " balas.");

        for (int i = 0; i < n; i++) {
            //when bullets become 0, the thread must wait until there are bullets
            while (balas == 0) {
                try {
                    System.out.println("Sin balas. " + Thread.currentThread().getName() + " esperando...");
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            //bullets are fired by one
            balas--;
            System.out.println(Thread.currentThread().getName() + " disparó. Balas restantes: " + balas);
        }
    }

    public synchronized void reload() {
        int B = 10;
        balas += B;
        System.out.println(Thread.currentThread().getName() + " recargó " + B + " balas. Balas totales: " + balas);
        notifyAll();
    }
}