/**
 * @author Michalik Barbara S24660
 */

package zad1;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Towar> kolejka = new ArrayBlockingQueue<>(1000);

        WatekA watekA = new WatekA(kolejka);
        WatekB watekB = new WatekB(kolejka);

        watekA.start();
        watekB.start();

        try {
            watekA.join();
            watekB.interrupt();
            watekB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
