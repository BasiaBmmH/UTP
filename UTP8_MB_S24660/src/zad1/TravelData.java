package zad1;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TravelData {

    File folder;
    ArrayList<Offer> offer;

    public TravelData(File folder) {
        this.folder = folder;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        offer = downloadOffer(format);
    }

    public List<String> getOffersDescriptionsList(String locale, String dateFormat) {
        String[] language = locale.split("_");
        Locale lokalizacjaDocelowa = language.length == 1 ? Locale.forLanguageTag(language[0]) : new Locale(language[0], language[1]);
        ResourceBundle pakietLokalizacjiDocelowej = ResourceBundle.getBundle("JezykResource", lokalizacjaDocelowa);
        SimpleDateFormat formatDaty = new SimpleDateFormat(dateFormat);
        List<String> ofertyString = new ArrayList<>();
        for (Offer oferta : offer) {
            ResourceBundle pakietLokalizacjiPoczatkowej = ResourceBundle.getBundle("JezykResource", oferta.lokalizacjaPL);
            StringBuilder sb = new StringBuilder("");
            String kraj = oferta.lokalizacjaKraju.getDisplayCountry(lokalizacjaDocelowa);
            sb.append(kraj).append(' ');
            sb.append(formatDaty.format(oferta.departureDate)).append(' ').append(formatDaty.format(oferta.returnDate)).append(' ');

            String miejsceKlucz = "";
            Set<String> set = pakietLokalizacjiDocelowej.keySet();
            for (String klucz : set) {
                if (pakietLokalizacjiPoczatkowej.getString(klucz).equals(oferta.miejsce)) {
                    miejsceKlucz = klucz;
                    break;
                }
            }

            String miejsce = pakietLokalizacjiDocelowej.getString(miejsceKlucz);
            sb.append(miejsce).append(' ');
            sb.append(NumberFormat.getInstance(lokalizacjaDocelowa).format(oferta.price)).append(' ');
            sb.append(oferta.currency);

            ofertyString.add(sb.toString());
        }
        return ofertyString;
    }

    private ArrayList<Offer> downloadOffer(SimpleDateFormat dateFormat) {
        ArrayList<Offer> oferty = new ArrayList<>();
        try {
            Files.walkFileTree(folder.toPath(), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                    List<String> input = Files.readAllLines(file, StandardCharsets.UTF_8);
                    String[] dane = input.get(0).split("\t");
                    try {
                        oferty.add(new Offer(
                                dane[0],
                                dane[1],
                                dateFormat.parse(dane[2]),
                                dateFormat.parse(dane[3]),
                                dane[4],
                                dane[5],
                                dane[6])
                        );
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return oferty;
    }
}
