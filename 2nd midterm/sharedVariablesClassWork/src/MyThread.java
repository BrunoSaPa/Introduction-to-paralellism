public class MyThread extends Thread {
    private DataCenter data;

    public MyThread(DataCenter dc, String name){
        super(name);
        this.data = dc;
    }

    @Override
    public void run(){
        for(int i =0; i < 1000000; i++){
            data.increment(Thread.currentThread().getName());
        }
    }
}
