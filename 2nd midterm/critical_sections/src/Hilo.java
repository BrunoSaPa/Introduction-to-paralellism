public class Hilo extends Thread {
    private DataCenter data;
    public Hilo(DataCenter r, String name) {
        super(name);
        this.data = r;
    }
    @Override
    public void run() {    
            for (int i = 0; i < 1000; i++) {
                //
//                synchronized(data){
//                    data.incrementa();
//                }
                data.incrementa();
            }            
    }
}

