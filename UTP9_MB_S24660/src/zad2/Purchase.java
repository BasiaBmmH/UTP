/**
 * @author Michalik Barbara S24660
 */

package zad2;


import java.beans.*;
import java.io.Serializable;

public class Purchase implements Serializable {
    private String prod;
    private String data;
    private Double price;

    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private VetoableChangeSupport vcs = new VetoableChangeSupport(this);

    public Purchase(String prod, String data, Double price) {
        this.prod = prod;
        this.data = data;
        this.price = price;
    }

    public String getProd() {
        return prod;
    }

    public void setProd(String prod) {
        this.prod = prod;
    }

    public String getData() {
        return data;
    }

    public void setData(String newData) {
        String oldData = this.data;
        this.data = newData;
        pcs.firePropertyChange("data", oldData, newData);
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double newPrice) throws PropertyVetoException {
        Double oldPrice = this.price;
        vcs.fireVetoableChange("price", oldPrice, newPrice);
        this.price = newPrice;
        pcs.firePropertyChange("price", oldPrice, newPrice);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void addVetoableChangeListener(VetoableChangeListener listener) {
        vcs.addVetoableChangeListener(listener);
    }

    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        vcs.removeVetoableChangeListener(listener);
    }

    @Override
    public String toString() {
        return "Purchase [prod=" + prod + ", data=" + data + ", price=" + price + "]";
    }
}