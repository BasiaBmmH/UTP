package zad1;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

public class Offer {
    Locale lokalizacjaPL;
    String country;
    Date departureDate;
    Date returnDate;
    String miejsce;
    double price;
    String currency;
    Locale lokalizacjaKraju;

    public Offer(String locale,
                 String country,
                 Date departureDate,
                 Date returnDate,
                 String place,
                 String price,
                 String currency) {
        String[] language = locale.split("_");
        Locale lokalizacjaPoczatkowa = language.length == 1 ? new Locale(language[0]) : new Locale(language[0], language[1]);
        this.lokalizacjaPL = new Locale("pl", "PL");
        for (Locale l : Locale.getAvailableLocales()) {
            if (l.getDisplayCountry(lokalizacjaPoczatkowa).equals(country)) {
                lokalizacjaKraju = l;
                break;
            }
        }

        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.currency = currency;

        if (language[0].equals("pl")) {
            this.country = country;
            this.miejsce = place;
            try {
                this.price = (double) NumberFormat.getInstance(lokalizacjaPL).parse(price);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            ResourceBundle pakietLokalizacjiDocelowej = ResourceBundle.getBundle("JezykResource", lokalizacjaPL);
            ResourceBundle pakietLokalizacjiPoczatkowej = ResourceBundle.getBundle("JezykResource", lokalizacjaPoczatkowa);

            try {
                this.price = (double) NumberFormat.getInstance(lokalizacjaPoczatkowa).parse(price);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.country = lokalizacjaKraju.getDisplayCountry(lokalizacjaPL);

            String keyPlace = "";
            Set<String> set = pakietLokalizacjiDocelowej.keySet();
            for (String key : set) {
                if (pakietLokalizacjiPoczatkowej.getString(key).equals(place)) {
                    keyPlace = key;
                    break;
                }
            }

            this.miejsce = pakietLokalizacjiDocelowej.getString(keyPlace);
        }
    }
}
