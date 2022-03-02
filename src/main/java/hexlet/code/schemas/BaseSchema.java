package hexlet.code.schemas;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private static List<Predicate<Object>> checks = new ArrayList<>();
    private static Boolean required = false;

    private static Boolean shape = false;

    public abstract BaseSchema required();

    protected static void setPredicate(Predicate<?> predicate) {
        checks.add((Predicate<Object>) predicate);
    }

    public static Boolean isValid(Object v) {
        if (v instanceof Map<?, ?>) {
            if (!checks.get(checks.size() - 1).test(v)) {
                return false;
            }
        }
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

    public static List<Predicate<Object>> getChecks() {
        return checks;
    }

    public static void setChecks(List<Predicate<Object>> check) {
        checks = check;
    }

    public static Boolean getRequired() {
        return required;
    }

    public static void setRequired(Boolean req) {
        required = req;
    }

    public static Boolean getShape() {
        return shape;
    }

    public static void setShape(Boolean shape) {
        BaseSchema.shape = shape;
    }
}

