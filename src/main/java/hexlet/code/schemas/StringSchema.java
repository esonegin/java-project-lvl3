package hexlet.code.schemas;

import java.util.ArrayList;

public class StringSchema extends BaseSchema {

    public StringSchema() {
        //setChecks(new ArrayList<>());
        setRequired(false);
    }

    @Override
    public final StringSchema required() {
        setPredicate(x -> x instanceof String);
        setPredicate(x -> x.toString().length() > 0);
        setRequired(true);
        return this;
    }

    public final StringSchema minLength(int len) {
        setPredicate(x -> x.toString().length() >= len);
        return this;
    }

    public final StringSchema contains(String s) {
        setPredicate(x -> x.toString().contains(s));
        return this;
    }
}
