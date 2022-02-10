package hexlet.code.schemas;


public abstract class BaseSchema {
    //private static Object value;

    public abstract Boolean isValid(Object o);

    public abstract Boolean required(Object o);
}
