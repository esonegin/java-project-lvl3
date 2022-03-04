package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MapSchema extends BaseSchema {

    public MapSchema() {
        setChecks(new ArrayList<>());
        setRequired(false);
    }

    @Override
    public final MapSchema required() {
        setPredicate(x -> x instanceof HashMap<?, ?>);
        setRequired(true);
        return this;
    }

    public final MapSchema sizeof(int size) {
        ObjectMapper oMapper = new ObjectMapper();
        setPredicate(x -> oMapper.convertValue(x, Map.class).size() == size);
        return this;
    }

    public final void shape(Map<String, BaseSchema> schemas) {
        setPredicate((Map<String, Object> toCheck) -> schemas.entrySet().stream()
                .allMatch(node -> node.getValue().isValid(toCheck.get(node.getKey()))));
    }
}
