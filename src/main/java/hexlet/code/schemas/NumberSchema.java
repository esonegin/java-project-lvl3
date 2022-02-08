package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    Object value;

    public NumberSchema(Object o) {
        this.value = o;
    }

    public Boolean required() {
        return isValid(value, "Integer");
    }

    public Boolean positive() {
        return required() && (Integer)value > 0;
    }

    public Boolean range(int min, int max) {
        return (Integer) value >= min && (Integer) value <= max;
    }
}
