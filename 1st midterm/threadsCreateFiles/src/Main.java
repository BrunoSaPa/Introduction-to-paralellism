
public static void main(String[] args) {

}


public class fileCreator extends Thread{
    private int N,T,M;

    fileCreator(int n, int t,int m){
        N=n;
        T= t;
        M=m;
        this.start();
    }

    @Override
    public void run() {


        FileOutputStream fos =  new FileOutputStream('');


        calcPersonValue();

    }

    private int calcPersonValue(){
        int C,H,A;
        //rand from 1 to 100

        int m = (int)(Math.random()*(M-1)+1);
        int t = (int)(Math.random()*(T-10)+10);
        int sum = 0;
        for(int i=0;i<m;i++){
            C = (int)(Math.random()*99+1);
            H = (int)(Math.random()*99+1);
            A = (int)(Math.random()*99+1);
            try{
                Thread.sleep(t);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            sum = sum + (C+H)*A;
        }
        sum/= m;
        return sum;
    }
}
