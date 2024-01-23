/**
 * @author Michalik Barbara S24660
 */

package zad1;

import java.io.*;
import java.nio.file.*;
import java.util.regex.*;

public class Finder {
    private Path filePath;

    public Finder(String file) {
        this.filePath = Paths.get(file);
    }

    public int getIfCount() throws IOException {
        String content = new String(Files.readAllBytes(filePath));
        return countMatches(content, "\\bif\\b", true);
    }

    public int getStringCount(String word) throws IOException {
        String content = new String(Files.readAllBytes(filePath));
        return countMatches(content, "\\b" + Pattern.quote(word) + "\\b", false);
    }

    private int countMatches(String content, String pattern, boolean ignoreInStringsAndComments) {
        if (ignoreInStringsAndComments) {
            content = removeStringsAndComments(content);
        }
        Matcher m = Pattern.compile(pattern).matcher(content);
        int count = 0;
        while (m.find()) {
            count++;
        }
        return count;
    }

    private String removeStringsAndComments(String content) {
        return content.replaceAll("(\".*?\")|(/\\*.*?\\*/)|(//.*?$)", "");
    }
}

