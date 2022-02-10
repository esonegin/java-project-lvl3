package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    private static Object value;

    public NumberSchema(Object o) {
        value = o;
    }

    @Override
    public final Boolean isValid(Object v) {
        return v instanceof Integer;
    }

    @Override
    public final Boolean required(Object v) {
        return isValid(v);
    }

    public final Boolean positive(Object v) {
        return required(v) && (Integer) v > 0;
    }

    public static Boolean range(Object v, int min, int max) {
        return (Integer) v >= min && (Integer) v <= max;
    }
}
