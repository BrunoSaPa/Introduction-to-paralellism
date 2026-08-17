public class MyClass implements Runnable{

    @Override
    public void run(){
        System.out.println("This is a class. The executor is: " + Thread.currentThread().toString());
    }
}