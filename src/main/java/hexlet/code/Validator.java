package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {

    Object value;

    public Validator(Object o) {
        this.value = o;
    }


    public StringSchema string() {
        return new StringSchema(value);
    }

    public NumberSchema number() {
        return new NumberSchema(value);
    }

    public MapSchema map() {
        return new MapSchema(value);
    }
}
