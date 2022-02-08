package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    private static Object value;

    public NumberSchema(Object o) {
        value = o;
    }

    public static Boolean required() {
        return isValid(value, "Integer");
    }

    public static Boolean positive() {
        return required() && (Integer) value > 0;
    }

    public static Boolean range(int min, int max) {
        return (Integer) value >= min && (Integer) value <= max;
    }
}
