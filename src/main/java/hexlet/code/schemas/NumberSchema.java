package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    private static Object value;

    public NumberSchema(Object o) {
        value = o;
    }

    @Override
    public final Boolean isValid() {
        return NumberSchema.value instanceof Integer;
    }

    @Override
    public final Boolean required() {
        return isValid();
    }

    public final Boolean positive() {
        return required() && (Integer) value > 0;
    }

    public static Boolean range(int min, int max) {
        return (Integer) value >= min && (Integer) value <= max;
    }
}
