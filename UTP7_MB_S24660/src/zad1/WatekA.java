package zad1;

import java.io.*;
import java.util.concurrent.BlockingQueue;

public class WatekA extends Thread {
    private BlockingQueue<Towar> kolejka;

    public WatekA(BlockingQueue<Towar> kolejka) {
        this.kolejka = kolejka;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader("../Towary.txt"))) {
            String line;
            int liczbaObiektow = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int id = Integer.parseInt(parts[0]);
                double waga = Double.parseDouble(parts[1]);
                kolejka.put(new Towar(id, waga));
                liczbaObiektow++;
                if (liczbaObiektow % 200 == 0) {
                    System.out.println("utworzono " + liczbaObiektow + " obiektów");
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
