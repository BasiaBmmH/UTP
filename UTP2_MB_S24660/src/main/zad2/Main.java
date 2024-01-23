/**
 * @author Michalik Barbara S24660
 */

package zad2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> dest = Arrays.asList(
                "bleble bleble 2000",
                "WAW HAV 1200",
                "xxx yyy 789",
                "WAW DPS 2000",
                "WAW HKT 1000"
        );
        double ratePLNvsEUR = 4.30;
        List<String> result = new ArrayList<>();

        for (String d : dest) {
            String[] parts = d.split(" ");
            String departure = parts[0];
            String destination = parts[1];
            double priceEUR = Double.parseDouble(parts[2]);
            if (departure.equals("WAW")) {
                double pricePLN = priceEUR * ratePLNvsEUR;
                result.add("to " + destination + " - price in PLN:\t" + (int) pricePLN);
            }
        }


        for (String r : result) System.out.println(r);
    }
}
