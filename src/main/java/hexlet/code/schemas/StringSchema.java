package hexlet.code.schemas;


public class StringSchema extends BaseSchema {
    private static Object value;

    public StringSchema(Object o) {
        value = o;
    }

    public static Boolean required() {
        return isValid(value, "String") && String.valueOf(value).length() > 0;
    }

    public static Boolean minLength(int len) {
        return String.valueOf(value).length() >= len;
    }

    public static Boolean contains(String s) {
        return String.valueOf(value).contains(s);
    }
}
