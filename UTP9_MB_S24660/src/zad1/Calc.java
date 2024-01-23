package zad1;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

public class Calc {

    private final Map<String, BiFunction<BigDecimal, BigDecimal, BigDecimal>> operations;

    public Calc() {
        operations = new HashMap<>();
        operations.put("+", BigDecimal::add);
        operations.put("-", BigDecimal::subtract);
        operations.put("*", BigDecimal::multiply);
        operations.put("/", (a, b) -> a.divide(b, 7, BigDecimal.ROUND_HALF_UP));
    }

    public String doCalc(String cmd) {
        try {
            String[] parts = cmd.split("\\s+");
            BigDecimal num1 = new BigDecimal(parts[0]);
            String operation = parts[1];
            BigDecimal num2 = new BigDecimal(parts[2]);

            return Optional.ofNullable(operations.get(operation))
                    .map(func -> func.apply(num1, num2).toString())
                    .orElse("Invalid command to calc");
        } catch (Exception e) {
            return "Invalid command to calc";
        }
    }

    public static void main(String[] args) {
        Calc c = new Calc();
        String wynik = Optional.ofNullable(args.length > 0 ? args[0] : null)
                .map(c::doCalc)
                .orElse("Invalid command to calc");
        System.out.println(wynik);
    }
}
