public class MyThread extends Thread {
    private DataCenter data;

    public MyThread(DataCenter dc, String name){
        super(name);
        this.data = dc;
    }

    @Override
    public void run(){
        int executed = 0;

        for(int i =0; i < 1000000; i++){
            if(data.increment()) executed ++;
            else i--;
        }
        System.out.println(getName() + " executed increment " + executed + " times");
    }
}
