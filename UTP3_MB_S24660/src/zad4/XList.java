package zad4;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList<T> extends ArrayList<T> {

    public XList(T... args) {
        super(Arrays.asList(args));
    }

    public XList(Collection<T> collection) {
        super(collection);
    }

    public static <T> XList<T> of(T... args) {
        return new XList<>(args);
    }

    public static XList<Character> charsOf(String string) {
        return new XList<>(string.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
    }

    public static XList<String> tokensOf(String string, String... sep) {
        String regex = sep.length > 0 ? sep[0] : "\\s+";
        return new XList<>(Arrays.asList(string.split(regex)));
    }

    public XList<T> union(Collection<? extends T> collection) {
        XList<T> newList = new XList<>(this);
        newList.addAll(collection);
        return newList;
    }

    public XList<T> diff(XList<Integer> collection) {
        XList<T> newList = new XList<>(this);
        newList.removeAll(collection);
        return newList;
    }

    public XList<T> unique() {
        return new XList<>(new HashSet<>(this));
    }

    public <R> XList<R> collect(Function<? super T, ? extends R> mapper) {
        return this.stream().map(mapper).collect(Collectors.toCollection(XList::new));
    }

    public String join(String... sep) {
        String delimiter = sep.length > 0 ? sep[0] : "";
        return this.stream().map(Object::toString).collect(Collectors.joining(delimiter));
    }

    public void forEachWithIndex(BiConsumer<T, Integer> consumer) {
        for (int i = 0; i < size(); i++) {
            consumer.accept(get(i), i);
        }
    }

    public <E> XList<XList<E>> combine() {
        if (this.isEmpty() || !(this.get(0) instanceof Collection)) {
            throw new IllegalArgumentException("XList.combine is called on a list that does not contain collections.");
        }

        // Rozpoczęcie od listy zawierającej pustą listę kombinacji
        List<List<E>> combinations = Arrays.asList(Arrays.asList());

        for (T part : this) {
            // Tworzenie nowych kombinacji poprzez dodanie każdego elementu z part do każdej kombinacji
            List<List<E>> newCombinations = new ArrayList<>();
            for (List<E> combination : combinations) {
                for (E item : (Collection<E>) part) {
                    List<E> newCombination = new ArrayList<>(combination);
                    newCombination.add(item);
                    newCombinations.add(newCombination);
                }
            }
            combinations = newCombinations;
        }

        XList<XList<E>> xCombinations = new XList<>();
        for (List<E> combination : combinations) {
            xCombinations.add(new XList<>(combination));
        }
        return xCombinations;
    }


}