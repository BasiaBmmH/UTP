
/**
 * @author Michalik Barbara S24660
 */

package zad1;

import java.util.ArrayList;
import java.util.List;

public class ListCreator<T, R> {
    private final List<T> list;

    private ListCreator(List<T> list) {
        this.list = list;
    }

    public static <T> ListCreator<T, T> collectFrom(List<T> list) {
        return new ListCreator<>(list);
    }

    public ListCreator<T, T> when(Selector<T> selector) {
        List<T> filteredList = new ArrayList<>();
        for (T element : list) {
            if (selector.select(element)) {
                filteredList.add(element);
            }
        }
        return new ListCreator<>(filteredList);
    }

    public List<R> mapEvery(Mapper<T, R> mapper) {
        List<R> mappedList = new ArrayList<>();
        for (T element : list) {
            mappedList.add(mapper.map(element));
        }
        return mappedList;
    }
}

