package hexlet.code.schemas;

import java.util.HashMap;

public class BaseSchema {

    public Boolean isValid(Object value, String type) {
        return switch (type) {
            case "String" -> value instanceof String && value != null;
            case "Integer" -> value instanceof Integer && value != null;
            case "Map" -> value instanceof HashMap<?,?> && value != null;
            default -> false;
        };
    }
}
