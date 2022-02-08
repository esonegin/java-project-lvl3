package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {

    private static Object value;

    public Validator(Object o) {
        value = o;
    }


    public static StringSchema string() {
        return new StringSchema(value);
    }

    public static NumberSchema number() {
        return new NumberSchema(value);
    }

    public static MapSchema map() {
        return new MapSchema(value);
    }
}
