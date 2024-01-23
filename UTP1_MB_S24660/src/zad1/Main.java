
/**
 * @author Michalik Barbara S24660
 */

package zad1;


import java.util.*;

public class Main {
    public Main() {
        List<Integer> src1 = Arrays.asList(1, 7, 9, 11, 12);
        System.out.println(test1(src1));

        List<String> src2 = Arrays.asList("a", "zzzz", "vvvvvvv");
        System.out.println(test2(src2));
    }

    public List<Integer> test1(List<Integer> src) {
        Selector<Integer> sel = element -> element < 10;
        Mapper<Integer, Integer> map = element -> element + 10;
        return ListCreator.collectFrom(src).when(sel).mapEvery(map);
    }

    public List<String> test2(List<String> src) {
        Selector<String> sel = element -> element.length() > 3;
        Mapper<String, String> map = element -> element + " - modified";
        return ListCreator.collectFrom(src).when(sel).mapEvery(map);
    }

    public static void main(String[] args) {
        new Main();
    }
}