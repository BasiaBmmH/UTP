package zad2;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil {
    public static void processDir(String dirName, String resultFileName) {
        Charset cp1250 = Charset.forName("Cp1250");
        Charset utf8 = StandardCharsets.UTF_8;
        Path outputPath = Paths.get(resultFileName);

        try (BufferedWriter writer = Files.newBufferedWriter(outputPath, utf8)) {
            Files.walkFileTree(Paths.get(dirName), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().endsWith(".txt")) {
                        Files.lines(file, cp1250).forEach(line -> {
                            try {
                                writer.write(line);
                                writer.newLine();
                            } catch (IOException e) {
                                System.err.println("Błąd podczas zapisywania pliku: " + e.getMessage());
                            }
                        });
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.err.println("Błąd podczas przetwarzania katalogów: " + e.getMessage());
        }
    }
}
