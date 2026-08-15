public class MyClass extends Thread{
    public String message;

    public void Myclass(String message){
        this.message = message;
    }

    public void run(){
        //work
        while(true){
            System.out.println("Running in a MyClass thread");
        }
    }
}

