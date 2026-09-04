import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class PersonalScore implements Runnable {
    private int t;
    private int m;
    private int id;
    private float avg;
    private float good;
    private float bad;

    private static class ThreadWatcher extends Thread {
// just print the status of each of the threads each t milliseconds
        private List<Thread> list;
        private int t_milli;

        public ThreadWatcher(List<Thread> threadList, int milli) {
            this.list = threadList;
            this.t_milli = milli;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    for (Thread thread : this.list) {
                        var tn = thread.getName();
                        var tst = thread.getState().toString();
                        System.out.println(tn + " is in state " + tst);
                    }
                    Thread.sleep(this.t_milli);
                } catch (InterruptedException e) {
                    System.out.println("signal received, exiting watcher thread");
                    return;
                }
            }
        }
    }

    PersonalScore(int t, int m, int id, float good, float bad) {
        this.t = Math.max(10, (int) (Math.random() * t));
        this.m = (int) (Math.random() * m);
        this.id = id;
        this.good = good;
        this.bad = bad;
    }

    public void run() {
        int sm = 0;
        var logPath = Path.of("output" + this.id + ".txt");

        try {
            Files.deleteIfExists(logPath);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        for (int i = 0; i < this.m; i++) {
            int c = (int) (Math.random() * 100);
            int h = (int) (Math.random() * 100);
            int a = (int) (Math.random() * 100);
            int p = (c + h) * a;
            sm += p;

            try {
                Files.writeString(logPath, "Value of worth of person is: " + p + "\n",
                        StandardOpenOption.CREATE,
                        StandardOpenOption.WRITE,
                        StandardOpenOption.APPEND);
                Thread.sleep(this.t);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

// naive average calculation (guard against m == 0)
        this.avg = this.m > 0 ? (float) sm / this.m : 0f;
        System.out.println("Average worth of thread #" + this.id + " is " + this.avg);
        if (this.avg <= this.bad) {
            System.out.println(" that score is considered bad");
        } else if (this.avg < this.good) {
            System.out.println(" that score is considered average");
        } else {
            System.out.println(" that score is considered good");
        }
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("usage: java src/PersonalScore.java <n> <t> <m>");
            System.exit(-1);
        }

        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        int m = Integer.parseInt(args[2]);
        var workers = new ArrayList<Thread>();
        for (int i = 0; i < n; i++) {
            workers.add(new Thread(new PersonalScore(t, m, i, 5100, 5500), "Thread #" + i));
            workers.get(i).start();
        }
        ThreadWatcher tWatcher = new ThreadWatcher(workers, 250);
        tWatcher.start();

        for (int i = 0; i < n; i++) {
            try {
                workers.get(i).join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        tWatcher.interrupt();
    }
}