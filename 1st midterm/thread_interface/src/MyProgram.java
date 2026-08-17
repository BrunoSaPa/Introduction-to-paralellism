public class MyProgram {
    public static void main(String[] args){
        System.out.println("Main class");
        MyClass myClass = new MyClass();
        MyClass myClass2 = new MyClass();
        myClass.run();
        myClass2.run();

        //worker
        Thread worker = new Thread(myClass);
        worker.start();
        worker =  new Thread(myClass2);
        worker.setPriority(Thread.MIN_PRIORITY);
        worker.start();
    }
}
