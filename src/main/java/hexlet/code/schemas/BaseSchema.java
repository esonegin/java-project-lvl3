package hexlet.code.schemas;


public abstract class BaseSchema {
    //private static Object value;

    public abstract Boolean isValid();

    public abstract Boolean required();
}
