package hexlet.code.schemas;


public class StringSchema extends BaseSchema {
    private static Object value;

    public StringSchema(Object o) {
        value = o;
    }

    @Override
    public final Boolean isValid() {
        return value instanceof String;
    }

    @Override
    public final Boolean required() {
        return isValid() && String.valueOf(value).length() > 0;
    }

    public static Boolean minLength(int len) {
        return String.valueOf(value).length() >= len;
    }

    public static Boolean contains(String s) {
        return String.valueOf(value).contains(s);
    }
}
