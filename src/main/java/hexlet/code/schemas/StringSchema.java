package hexlet.code.schemas;


public class StringSchema extends BaseSchema {
    private static Object value;

    public StringSchema(Object o) {
        value = o;
    }

    @Override
    public final Boolean isValid(Object v) {
        return v instanceof String;
    }

    @Override
    public final Boolean required(Object v) {
        return isValid(v) && String.valueOf(v).length() > 0;
    }

    public static Boolean minLength(Object v, int len) {
        return String.valueOf(v).length() >= len;
    }

    public static Boolean contains(Object v, String s) {
        return String.valueOf(v).contains(s);
    }
}
