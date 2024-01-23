package zad3;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Predicate;

public class ProgLang {
    private Map<String, Set<String>> langsMap = new HashMap<>();
    private Map<String, Set<String>> progsMap = new HashMap<>();

    public ProgLang(String nazwaPliku) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(nazwaPliku))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length >= 2) {
                    String lang = parts[0];
                    Set<String> programmers = new HashSet<>(Arrays.asList(parts).subList(1, parts.length));

                    langsMap.put(lang, programmers);

                    for (String programmer : programmers) {
                        progsMap.computeIfAbsent(programmer, k -> new HashSet<>()).add(lang);
                    }
                }
            }
        }
    }

    public Map<String, Set<String>> getLangsMap() {
        return langsMap;
    }

    public Map<String, Set<String>> getProgsMap() {
        return progsMap;
    }

    public Map<String, Set<String>> getLangsMapSortedByNumOfProgs() {
        return sorted(langsMap, (entry1, entry2) -> {
            int size1 = entry1.getValue().size();
            int size2 = entry2.getValue().size();
            if (size1 != size2) {
                return size2 - size1;
            } else {
                return entry1.getKey().compareTo(entry2.getKey());
            }
        });
    }

    public Map<String, Set<String>> getProgsMapSortedByNumOfLangs() {
        return sorted(progsMap, (entry1, entry2) -> {
            int size1 = entry1.getValue().size();
            int size2 = entry2.getValue().size();
            if (size1 != size2) {
                return size2 - size1;
            } else {
                return entry1.getKey().compareTo(entry2.getKey());
            }
        });
    }

    public Map<String, Set<String>> getProgsMapForNumOfLangsGreaterThan(int n) {
        return filtered(progsMap, entry -> entry.getValue().size() > n);
    }

    public <K, V> Map<K, V> sorted(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
        return map.entrySet()
                .stream()
                .sorted(comparator)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public <K, V> Map<K, V> filtered(Map<K, V> map, Predicate<Map.Entry<K, V>> predicate) {
        return map.entrySet()
                .stream()
                .filter(predicate)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}