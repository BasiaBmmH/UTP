/**
 * @author Michalik Barbara S24660
 */

package zad1;

import java.io.*;
import java.util.*;

public class Anagrams {
    private Map<String, List<String>> anagramMap;

    public Anagrams(String allWordsFilePath) throws IOException {
        anagramMap = new HashMap<>();
        loadWordsFromFile(allWordsFilePath);
    }

    private void loadWordsFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    String sortedWord = sortWord(word);
                    anagramMap.computeIfAbsent(sortedWord, k -> new ArrayList<>()).add(word);
                }
            }
        }
    }

    private String sortWord(String word) {
        char[] charArray = word.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    public List<List<String>> getSortedByAnQty() {
        List<List<String>> result = new ArrayList<>(anagramMap.values());
        result.sort((list1, list2) -> {
            if (list1.size() != list2.size()) {
                return Integer.compare(list2.size(), list1.size());
            } else {
                return list1.get(0).compareTo(list2.get(0));
            }
        });
        return result;
    }

    public String getAnagramsFor(String wordToFind) {
        String sortedWord = sortWord(wordToFind);
        List<String> anagrams = anagramMap.get(sortedWord);
        if (anagrams != null) {
            return wordToFind + ": " + anagrams;
        } else {
            return wordToFind + ": []";
        }
    }
}
