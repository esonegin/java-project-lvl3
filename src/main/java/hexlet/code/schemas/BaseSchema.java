package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final List<Predicate<Object>> checks = new ArrayList<>();
    private Boolean required = false;

    public abstract BaseSchema required();

    protected final void req(Class obj) {
        addCheck(obj::isInstance);
        required = true;
    }

    protected final void addCheck(Predicate<Object> predicate) {
        checks.add(predicate);
    }

    public final Boolean isValid(Object v) {
        if (v == null && !required) {
            return true;
        }
        for (Predicate<Object> check : checks) {
            if (!check.test(v)) {
                return false;
            }
        }
        return true;
    }
}

