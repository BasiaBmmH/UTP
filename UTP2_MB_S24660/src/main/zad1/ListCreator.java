/**
 * @author Michalik Barbara S24660
 */

package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Function;

class ListCreator<T> {
    private List<T> list;

    private ListCreator(List<T> list) {
        this.list = list;
    }

    public static <T> ListCreator<T> collectFrom(List<T> list) {
        return new ListCreator<>(list);
    }

    public ListCreator<T> when(Predicate<T> predicate) {
        list.removeIf(predicate.negate());
        return this;
    }

    public <R> List<R> mapEvery(Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(function.apply(item));
        }
        return result;
    }
}