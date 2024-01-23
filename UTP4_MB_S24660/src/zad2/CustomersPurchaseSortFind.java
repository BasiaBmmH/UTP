/**
 * @author Michalik Barbara S24660
 */

package zad2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CustomersPurchaseSortFind {
    private List<Purchase> purchases;

    public CustomersPurchaseSortFind() {
        this.purchases = new ArrayList<>();
    }

    public void readFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    String clientId = parts[0];
                    String name = parts[1];
                    String productName = parts[2];
                    double price = Double.parseDouble(parts[3]);
                    double quantity = Double.parseDouble(parts[4]);
                    Purchase purchase = new Purchase(clientId, name, productName, price, quantity);
                    purchases.add(purchase);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSortedBy(String category) {
        switch (category) {
            case "Nazwiska":
                System.out.println("Nazwiska");
                Collections.sort(purchases, Comparator.comparing(Purchase::getName).thenComparing(Purchase::getClientId));
                break;
            case "Koszty":
                System.out.println("Koszty");
                Collections.sort(purchases, Comparator.comparing(Purchase::getTotalCost).reversed().thenComparing(Purchase::getClientId));
                break;
            default:
                System.out.println("Nieznana kategoria: " + category);
                return;
        }

        for (Purchase purchase : purchases) {
            System.out.println(purchase);
        }
        System.out.println();
    }

    public void showPurchaseFor(String clientId) {
        System.out.println("Klient " + clientId);
        for (Purchase purchase : purchases) {
            if (purchase.getClientId().equals(clientId)) {
                System.out.println(purchase);
            }
        }
        System.out.println();
    }
}