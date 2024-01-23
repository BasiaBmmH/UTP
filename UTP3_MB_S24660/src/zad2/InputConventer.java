package zad2;

import java.util.function.Function;

public class InputConverter<T> {
    private T source;

    public InputConverter(T source) {
        this.source = source;
    }

    @SafeVarargs
    public final <R> R convertBy(Function... functions) {
        Object result = source;
        for (Function function : functions) {
            result = function.apply(result);
        }
        return (R) result;
    }
}
