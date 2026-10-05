import java.util.HashMap;


public class DataCenter {

    public static int value =0;

    public void increment(String name){
        value++;
    }

    public void decrement(String name){
        value--;
    }

    public int getValue(){
        return value;
    }
}
