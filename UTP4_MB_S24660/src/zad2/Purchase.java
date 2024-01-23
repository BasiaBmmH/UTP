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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Purchase {
    private String clientId;
    private String name;
    private String productName;
    private double price;
    private double quantity;

    public Purchase(String clientId, String name, String productName, double price, double quantity) {
        this.clientId = clientId;
        this.name = name;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getTotalCost() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return clientId + ";" + name + ";" + productName + ";" + price + ";" + quantity;
    }
}

