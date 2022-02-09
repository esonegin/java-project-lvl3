package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MapSchema extends BaseSchema {
    private static Object value;

    public MapSchema(Object o) {
        value = o;
    }

    @Override
    public final Boolean isValid() {
        return value instanceof HashMap<?, ?>;
    }

    @Override
    public final Boolean required() {
        return isValid();
    }

    public final Boolean sizeof(int size) {
        if (!isValid()) {
            return false;
        }
        ObjectMapper oMapper = new ObjectMapper();
        return oMapper.convertValue(value, Map.class).size() == size;
    }
}
