package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private List<Predicate<Object>> checks = new ArrayList<>();
    private Boolean required = false;


    public abstract BaseSchema required();

    protected final void setPredicate(Predicate<?> predicate) {
        checks.add((Predicate<Object>) predicate);
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

    public final void setChecks(List<Predicate<Object>> check) {
        checks = check;
    }

    public final void setRequired(Boolean req) {
        required = req;
    }

}

