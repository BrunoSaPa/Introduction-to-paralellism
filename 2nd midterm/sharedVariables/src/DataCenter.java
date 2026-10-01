import java.util.HashMap;


public class DataCenter {

    private HashMap<String, Integer> values = new HashMap();

    public void increment(String name){
        if(values.get(name) != null){
            int currentValue = values.get(name);
            values.replace(name, currentValue + 1);
        }else{
            values.put(name, 1);
        }
    }

    public void decrement(String name){
        if(values.get(name) != null){
            int currentValue = values.get(name);
            values.replace(name, currentValue-1);
        }else{
            values.put(name, -1);
        }
    }

    public int getValue(){
        int sum = 0;
        for(Integer value : values.values()){
            //System.out.println(value + "\n");
            sum += value;
        }
        return sum;
    }
}
