package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    @Override
    public final MapSchema required() {
        req(Map.class);
        return this;
    }

    public final MapSchema sizeof(int size) {
        addCheck(x -> ((Map) x).size() == size);
        return this;
    }

    public final void shape(Map<String, BaseSchema> schemas) {
        addCheck(toCheck -> schemas.entrySet().stream()
                .allMatch(node -> node.getValue().isValid(((Map<String, Object>) toCheck).get(node.getKey()))));
    }
}
