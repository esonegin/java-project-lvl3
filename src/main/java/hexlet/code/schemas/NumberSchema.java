package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    @Override
    public final NumberSchema required() {
        req(Integer.class);
        return this;
    }

    public final NumberSchema positive() {
        addCheck(x -> x instanceof Integer && (int) x > 0);
        return this;
    }

    public final NumberSchema range(int min, int max) {
        addCheck(x -> (Integer) x >= min && (Integer) x <= max);
        return this;
    }
}
