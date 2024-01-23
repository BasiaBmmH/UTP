/**
 * @author Michalik Barbara S24660
 */

package zad1;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


public class Main {
    public static void test() {
        // Metoda of(...)
        String s = "aaa";
        Maybe<String> m1 = Maybe.of(s);
        System.out.println(m1);
        s = null;
        Maybe<String> m2 = Maybe.of(s);
        System.out.println(m2);

        // Metoda ifPresent(...)
        Integer num = null;
        Maybe<Integer> m4 = Maybe.of(num);
        // ZAMIAST
        if (num != null) System.out.println(num);
        // PISZEMY
        m4.ifPresent(n -> System.out.println(n));
        // A NAWET
        m4.ifPresent(System.out::println);

        Maybe<Integer> m5 = Maybe.of(10);
        m5.ifPresent(System.out::println);

        // Metoda map()
        Maybe<Integer> m6 = m5.map(n -> n + 10);
        System.out.println(m6);

        // Metoda get()
        System.out.println(m6.get());
        try {
            System.out.println(m4.get());
        } catch (Exception exc) {
            System.out.println(exc);
        }

        // Metoda orElse()
        // ZAMIAST
        String snum = null;
        if (num != null) snum = "Wartość wynosi: " + num;
        if (snum != null) System.out.println(snum);
        else System.out.println("Wartość niedostępna");

        //MOŻNA NAPISAĆ
        String res = Maybe.of(num).map(n -> "Wartość wynosi: " + n)
                .orElse("Wartość niedostępna");
        System.out.println(res);

        // I filter(...)

        String txt = "Pies";
        String msg = "";

        //ZAMIAST
        if (txt != null && txt.length() > 0) {
            msg = txt;
        } else {
            msg = "Txt is null or empty";
        }

        //MOŻNA NAPISAĆ
        msg = Maybe.of(txt)
                .filter(t -> t.length() > 0)
                .orElse("Txt is null or empty");
        System.out.println(msg);
    }

    public static void main(String[] args) {
        test();
    }
}

class Maybe<T> {
    private final T value;
    private final boolean isPresent;

    private Maybe(T value) {
        this.value = value;
        this.isPresent = value != null;
    }

    public static <T> Maybe<T> of(T value) {
        return new Maybe<>(value);
    }

    public void ifPresent(Consumer<? super T> cons) {
        if (isPresent) cons.accept(value);
    }

    public <R> Maybe<R> map(Function<? super T, ? extends R> func) {
        if (isPresent) {
            R result = func.apply(value);
            return Maybe.of(result);
        } else {
            return new Maybe<>(null);
        }
    }

    public T get() {
        if (!isPresent) throw new NoSuchElementException("maybe is empty");
        return value;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public T orElse(T defVal) {
        return isPresent ? value : defVal;
    }

    public Maybe<T> filter(Predicate<? super T> pred) {
        if (isPresent && pred.test(value)) {
            return this;
        } else {
            return new Maybe<>(null);
        }
    }

    @Override
    public String toString() {
        return isPresent ? "Maybe has value " + value : "Maybe is empty";
    }
}


// Wynik na konsoli:
/*    
  Maybe has value aaa
  Maybe is empty
  10
  Maybe has value 20
  20
  java.util.NoSuchElementException:  maybe is empty
  Wartość niedostępna
  Wartość niedostępna
  Pies
*/
