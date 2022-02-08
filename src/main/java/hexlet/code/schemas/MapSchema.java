package hexlet.code.schemas;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MapSchema extends BaseSchema {
    private static Object value;

    public MapSchema(Object o) {
        value = o;
    }

    public static Boolean required() {
        return isValid(value, "Map");
    }

    public static Boolean sizeof(int size) {
        if (!isValid(value, "Map")) {
            return false;
        }
        ObjectMapper oMapper = new ObjectMapper();
        return oMapper.convertValue(value, Map.class).size() == size;
    }
}
