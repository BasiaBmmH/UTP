package zad1;

import javax.swing.*;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class Window extends JFrame {

    ResultSet resultSet;

    public Window(ResultSet resultSet) {
        this.resultSet = resultSet;
        //setIconImage(new Image("image\\jpg.jpg"));
        generateFrame();

    }

    public void generateFrame() {

        Tabel model = new Tabel(przetlumacz(
                new Locale("pl", "PL")),
                new String[]{"kraj", "data wyjazdu", "data powrotu", "miejsce", "cena"}
        );

        JTable tabela = new JTable(model);
        getContentPane().setBackground(Color.GRAY);

        ImageIcon backgroundImage = new ImageIcon("image\\mountains.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());
        setContentPane(backgroundLabel);

        ImageIcon icon = new ImageIcon("image\\jpg.jpg");
        setIconImage(icon.getImage());

//        pack();
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setVisible(true);
//        setLocationRelativeTo(null);


        JLabel info = new JLabel("Wybierz lokalizacje:");
        info.setHorizontalAlignment(SwingConstants.LEFT);
        info.setBorder(BorderFactory.createEmptyBorder(-110, 10, 0, 0));


        ImageIcon polskaFlag = new ImageIcon("image\\polishFlag.jpg");
        // setIconImage(polskaFlag.getImage());
        ImageIcon englandFlag = new ImageIcon("image\\englishFlag.jpg");
        ImageIcon norgeFlag = new ImageIcon("image\\norwayFlag.jpg");

        JRadioButton polskaPrzycisk = new JRadioButton(" Polska ");
        polskaPrzycisk.addActionListener(e -> {
            tabela.setModel(new Tabel(
                    przetlumacz(new Locale("pl", "PL")),
                    new String[]{"Kraj: ", "Data wyjazdu: ", "Data powrotu: ", "Miejsce: ", "Cena: "}
            ));
            tabela.repaint();
            tabela.invalidate();
            model.fireTableDataChanged();
            info.setText("Wybierz lokalizacje: ");
            setTitle("Oferty: ");
            //setIconImage("image\\jpg.jpg");
        });
        JRadioButton angielskaPrzycisk = new JRadioButton(" England ");
        angielskaPrzycisk.addActionListener(e -> {
            tabela.setModel(new Tabel(
                    przetlumacz(new Locale("en", "GB")),
                    new String[]{"Country: ", "Departure date: ", "Return date: ", "Place: ", "Price: "}
            ));
            tabela.repaint();
            tabela.invalidate();
            model.fireTableDataChanged();
            info.setText("Choose your localisation: ");
            setTitle("Offers: ");
        });

        JRadioButton niemieckaPrzycisk = new JRadioButton(" Norge ");
        niemieckaPrzycisk.addActionListener(e -> {
            tabela.setModel(new Tabel(
                    przetlumacz(new Locale("no", "NO")),
                    new String[]{"Land: ", "Avreisedato: ", "Returdato: ", "Sted: ", "Pris: "}
            ));
            tabela.repaint();
            tabela.invalidate();
            model.fireTableDataChanged();
            info.setText("Velg steder: ");
            setTitle("Tilbud: ");
        });

        ButtonGroup lokalizacjePrzyciski = new ButtonGroup();
        lokalizacjePrzyciski.add(polskaPrzycisk);
        lokalizacjePrzyciski.add(angielskaPrzycisk);
        lokalizacjePrzyciski.add(niemieckaPrzycisk);

        JPanel panelPrzycisk = new JPanel();
        panelPrzycisk.setLayout(new GridLayout(0, 1));
        panelPrzycisk.add(polskaPrzycisk);
        panelPrzycisk.add(angielskaPrzycisk);
        panelPrzycisk.add(niemieckaPrzycisk);

        JScrollPane panelTabeli = new JScrollPane(tabela);

        setTitle("Oferty");
        setLayout(new GridLayout(0, 1));
        add(panelTabeli);
        add(info);
        add(panelPrzycisk);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public ArrayList<ArrayList<String>> przetlumacz(Locale lokalizacjaDocelowa) {
        ArrayList<ArrayList<String>> oferty = new ArrayList<>();
        try {
            while (resultSet.next()) {
                ArrayList<String> oferta = new ArrayList<>();
                ResourceBundle pakietLokalizacjiDocelowej = ResourceBundle.getBundle("JezykResource", lokalizacjaDocelowa);
                String[] lokCzesci = resultSet.getString("lokalizacja_kraju").split("_");
                Locale lokalizacjaPoczatkowa = new Locale("pl", "PL");
                Locale lokalizacjaKraju = new Locale(lokCzesci[0], lokCzesci[1]);
                for (Locale l : Locale.getAvailableLocales()) {
                    if (l.getDisplayCountry(lokalizacjaPoczatkowa).equals(resultSet.getString("kraj"))) {
                        lokalizacjaKraju = l;
                        break;
                    }
                }
                oferta.add(lokalizacjaKraju.getDisplayCountry(lokalizacjaDocelowa));
                oferta.add(resultSet.getString("dataOd"));
                oferta.add(resultSet.getString("dataDo"));
                oferta.add(pakietLokalizacjiDocelowej.getString(resultSet.getString("miejsce")));
                double cena = Double.parseDouble(resultSet.getString("cena"));

                oferta.add(NumberFormat.getInstance(lokalizacjaDocelowa).format(cena) + " " + resultSet.getString("symbol_waluty"));
                oferty.add(oferta);
            }
            resultSet.beforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oferty;
    }
}

