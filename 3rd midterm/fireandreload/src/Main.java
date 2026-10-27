import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número de iteraciones (N): ");
        int N = scanner.nextInt();

        System.out.println("\nExperimento 1");
        //create the shared object rifle, an instance of Rifle
        Rifle rifleExp1 = new Rifle(10);

        //initialize the FirePlayer and ReloadPlayer threads
        FirePlayer fp1 = new FirePlayer(rifleExp1, N, "FirePlayer 1");
        ReloadPlayer rp1 = new ReloadPlayer(rifleExp1, N, "ReloadPlayer 1");

        fp1.start();
        rp1.start();

        try {
            fp1.join();
            rp1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nexp 2");
        //create the shared object rifle and pass it to fires and reloaders
        Rifle rifleExp2 = new Rifle(10);

        //initialize several FirePlayer and several ReloadPlayer threads
        FirePlayer[] firePlayers = new FirePlayer[3];
        ReloadPlayer[] reloadPlayers = new ReloadPlayer[2];

        for (int i = 0; i < 3; i++) {
            firePlayers[i] = new FirePlayer(rifleExp2, N, "FirePlayer " + (i + 1));
            firePlayers[i].start();
        }

        for (int i = 0; i < 2; i++) {
            reloadPlayers[i] = new ReloadPlayer(rifleExp2, N, "ReloadPlayer " + (i + 1));
            reloadPlayers[i].start();
        }
    }
}