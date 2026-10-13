import java.util.HashMap;


public class DataCenter {

    private volatile static int value =0;
    volatile private boolean lock = false;

    public boolean increment(){
        if(lock) return false;
        else{
         lock = true;
         value++;
         lock = false;
         return true;
        }
    }

    public void decrement(String name){
        value--;
    }

    public int getValue(){
        return value;
    }
}
