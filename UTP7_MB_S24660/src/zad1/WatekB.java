package zad1;

import java.util.concurrent.BlockingQueue;

public class WatekB extends Thread {
    private BlockingQueue<Towar> kolejka;
    private double sumaWagi = 0;
    private int przetworzono = 0;

    public WatekB(BlockingQueue<Towar> kolejka) {
        this.kolejka = kolejka;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Towar towar = kolejka.take();
                sumaWagi += towar.getWaga();
                przetworzono++;
                if (przetworzono % 100 == 0) {
                    System.out.println("policzono wage " + przetworzono + " towarów");
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Sumaryczna waga towarów: " + sumaWagi);
        }
    }
}
