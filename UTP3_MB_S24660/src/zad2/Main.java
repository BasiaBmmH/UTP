/**
 * @author Michalik Barbara S24660
 */

package zad2;


import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        Function<String, List<String>> flines = fname -> {
            try {
                return Files.readAllLines(Paths.get(fname));
            } catch (IOException e) {
                e.printStackTrace();
                return Collections.emptyList();
            }
        };

        Function<List<String>, String> join = list -> String.join("", list);

        Function<String, List<Integer>> collectInts = str -> Arrays.stream(str.split("\\D+"))
                .filter(s -> !s.isEmpty())
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        Function<List<Integer>, Integer> sum = list -> list.stream().reduce(0, Integer::sum);

        String fname = System.getProperty("user.home") + "/LamComFile.txt";
        InputConverter<String> fileConv = new InputConverter<>(fname);
        List<String> lines = fileConv.convertBy(flines);
        String text = fileConv.convertBy(flines, join);
        List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
        Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

        System.out.println(lines);
        System.out.println(text);
        System.out.println(ints);
        System.out.println(sumints);

        List<String> arglist = Arrays.asList(args);
        InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
        sumints = slistConv.convertBy(join, collectInts, sum);
        System.out.println(sumints);
    }
}
