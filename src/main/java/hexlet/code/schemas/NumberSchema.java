package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {

    private static List<Predicate<Object>> checks;

    public NumberSchema() {
        checks = new ArrayList<>();
    }

    private Boolean required = false;


    @Override
    public final Boolean isValid(Object v) {
        if (!required && v == null) {
            return true;
        }
        for (Predicate<Object> check : checks) {
            if (!check.test(v)) {
                return false;
            }
        }
        return true;
    }

    public final NumberSchema required() {
        checks.add(x -> x instanceof Integer);
        required = true;
        return this;
    }

    public final NumberSchema positive() {
        checks.add(Objects::nonNull);
        checks.add(x -> (Integer) x > 0);
        return this;
    }

    public final NumberSchema range(int min, int max) {
        checks.add(x -> (Integer) x >= min && (Integer) x <= max);
        return this;
    }
}
