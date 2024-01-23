package zad1;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class Tabel extends AbstractTableModel {

    ArrayList<ArrayList<String>> offers;
    String[] columns;

    public Tabel(ArrayList<ArrayList<String>> oferty, String[] nazwyKolumn) {
        this.offers = oferty;
        this.columns = nazwyKolumn;
    }

    @Override
    public int getRowCount() {
        return offers.size();
    }

    @Override
    public int getColumnCount() {
        if (offers.size() == 0) return 0;
        return offers.get(0).size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return offers.get(rowIndex).get(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}
