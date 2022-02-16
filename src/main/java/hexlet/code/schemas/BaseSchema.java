package hexlet.code.schemas;


public abstract class BaseSchema {
    public abstract Boolean isValid(Object o);
    public abstract BaseSchema required();
}
