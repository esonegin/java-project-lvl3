package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    @Override
    public final StringSchema required() {
        req(String.class);
        addCheck(x -> x.toString().length() > 0);
        return this;
    }

    public final StringSchema minLength(int len) {
        addCheck(x -> x.toString().length() >= len);
        return this;
    }

    public final StringSchema contains(String s) {
        addCheck(x -> x.toString().contains(s));
        return this;
    }
}
