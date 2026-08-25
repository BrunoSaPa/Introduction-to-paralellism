import java.util.concurrent.ThreadLocalRandom;

class Animal implements Runnable {

    public void sound() {
        System.out.println("Animal sound");
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {

            System.out.println(
                    "Hi, I am " + Thread.currentThread().getName()
            );

            sound();

            try {
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


class Dog extends Animal {

    @Override
    public void sound() {
        System.out.println("Woof!");
    }
}


class Cat extends Animal {

    @Override
    public void sound() {
        System.out.println("Meow!");
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