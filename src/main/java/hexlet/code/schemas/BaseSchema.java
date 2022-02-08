package hexlet.code.schemas;

import java.util.HashMap;

public class BaseSchema {

    public static Boolean isValid(Object value, String type) {
        return switch (type) {
            case "String" -> value instanceof String;
            case "Integer" -> value instanceof Integer;
            case "Map" -> value instanceof HashMap<?, ?>;
            default -> false;
        };
    }
}
