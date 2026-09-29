public class Main{
    public static void task(int id){
        System.out.println("Thread is running" + id);
    }

    public static void main(String [] args) throws InterruptedException{
        Thread t1 = new Thread(() -> task(1));
        Thread t2 = new Thread(() -> task(2));

        t1.start();
        t1.join();


        t2.start();
        t2.join();

        System.out.println("main finshed");
    }
}