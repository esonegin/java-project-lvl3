package hexlet.code.schemas;


public class NumberSchema extends BaseSchema {

    public NumberSchema() {
        setRequired(false);
    }

    @Override
    public final NumberSchema required() {
        setPredicate(x -> x instanceof Integer);
        setRequired(true);
        return this;
    }

    public final NumberSchema positive() {
        setPredicate(x -> x instanceof Integer && (int) x > 0);
        return this;
    }

    public final NumberSchema range(int min, int max) {
        setPredicate(x -> (Integer) x >= min && (Integer) x <= max);
        return this;
    }
}
