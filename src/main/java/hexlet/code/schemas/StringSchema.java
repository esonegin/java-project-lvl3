package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StringSchema extends BaseSchema {

    private static List<Predicate<Object>> checks;

    public StringSchema() {
        checks = new ArrayList<>();
    }

    @Override
    public final Boolean isValid(Object v) {
        if (checks.size() != 0 && v == null) {
            return false;
        }
        for (Predicate<Object> check : checks) {
            if (!check.test(v)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final StringSchema required() {
        checks.add(x -> x instanceof String);
        checks.add(x -> x.toString().length() > 0);
        return this;
    }

    public final StringSchema minLength(int len) {
        checks.add(x -> x.toString().length() >= len);
        return this;
    }

    public final StringSchema contains(String s) {
        checks.add(x -> x.toString().contains(s));
        return this;
    }
}
