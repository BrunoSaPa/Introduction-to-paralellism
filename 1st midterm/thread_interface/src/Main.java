import java.util.concurrent.ThreadLocalRandom;

class Dog implements Runnable {

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {

            System.out.println(
                    "Hi, I am " + Thread.currentThread().getName()
            );

            try {
                // Random pause between 500 ms and 2000 ms
                int pause = ThreadLocalRandom.current()
                        .nextInt(500, 2001);

                Thread.sleep(pause);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}


class Cat implements Runnable {

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {

            System.out.println(
                    "Hi, I am " + Thread.currentThread().getName()
            );

            try {
                // Random pause between 500 ms and 2000 ms
                int pause = ThreadLocalRandom.current()
                        .nextInt(500, 2001);

                Thread.sleep(pause);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}


public class Main {

    public static void main(String[] args) {

        System.out.println("Hola Mundo");

        Thread perro1 = new Thread(new Dog(), "dog red");
        Thread perro2 = new Thread(new Dog(), "dog blue");

        Thread gatito1 = new Thread(new Cat(), "cat red");
        Thread gatito2 = new Thread(new Cat(), "cat blue");

        perro1.start();
        perro2.start();
        gatito1.start();
        gatito2.start();
    }
}