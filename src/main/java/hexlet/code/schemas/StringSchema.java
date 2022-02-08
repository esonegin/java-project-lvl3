package hexlet.code.schemas;


public class StringSchema extends BaseSchema {
    Object value;

    public StringSchema(Object o) {
        this.value = o;
    }

    public Boolean required() {
        return isValid(value, "String") && String.valueOf(value).length() > 0;
    }

    public Boolean minLength(int len) {
        return String.valueOf(value).length() >= len;
    }

    public Boolean contains(String s) {
        return String.valueOf(value).contains(s);
    }
}
