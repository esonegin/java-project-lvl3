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
    public final Boolean isValid(Object v) {
        return v instanceof HashMap<?, ?>;
    }

    @Override
    public final Boolean required(Object v) {
        return isValid(v);
    }

    public final Boolean sizeof(Object v, int size) {
        if (!isValid(v)) {
            return false;
        }
        ObjectMapper oMapper = new ObjectMapper();
        return oMapper.convertValue(v, Map.class).size() == size;
    }
}
