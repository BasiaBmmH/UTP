package zad1;

import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Database {

    String url;
    TravelData travelData;
    Connection connection;
    Statement statement;
    Locale obecnaLokalizacja;
    SimpleDateFormat format;

    public Database(String url, TravelData travelData) {
        this.url = url;
        this.travelData = travelData;
        format = new SimpleDateFormat("yyyy-MM-dd");
        obecnaLokalizacja = new Locale("pl", "PL");
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            statement.executeUpdate("DROP TABLE OFERTY");
            statement.executeUpdate("CREATE TABLE OFERTY (offerID int PRIMARY KEY, kraj varchar(20), dataOd Date, dataDo Date, miejsce varchar(20), cena double, symbol_waluty varchar(10), lokalizacja_kraju varchar(20))");
            int i = 0;
            for (Offer oferta : travelData.offer) {
                statement.executeUpdate("INSERT INTO OFERTY VALUES ("
                        + i++ + ", '" +
                        oferta.country + "', '" +
                        format.format(oferta.departureDate) + "', '" +
                        format.format(oferta.returnDate) + "', '" +
                        oferta.miejsce + "', " +
                        oferta.price + ", '" +
                        oferta.currency + "', '" +
                        oferta.lokalizacjaKraju.getISO3Language() + "_" + oferta.lokalizacjaKraju.getISO3Country() + "')");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showGui() {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("SELECT kraj, dataOd, dataDo, miejsce, cena, symbol_waluty, lokalizacja_kraju FROM OFERTY");
            SwingUtilities.invokeLater(() -> new Window(resultSet));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
