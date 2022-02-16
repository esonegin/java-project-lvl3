package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MapSchema extends BaseSchema {

    private Boolean required = false;
    private Boolean shape = false;
    private static Predicate<Map<String, BaseSchema>> shapecheck;
    private static List<Predicate<Object>> checks;

    public MapSchema() {
        checks = new ArrayList<>();
        //shapecheck = new Predicate<Map<String, BaseSchema>>();
    }

    @Override
    public final Boolean isValid(Object v) {
        if (shape) {
            if (!shapecheck.test((Map<String, BaseSchema>) v)) {
                return false;

            }
        }
        if (!required && v == null) {
            return true;
        }
        if (checks.size() != 0 && v == null) {
            return false;
        }


        for (Predicate<Object> check : checks) {
            if (!check.test(v)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final MapSchema required() {
        checks.add(x -> x instanceof HashMap<?, ?>);
        required = true;
        return this;
    }

    public final MapSchema sizeof(int size) {
        ObjectMapper oMapper = new ObjectMapper();
        checks.add(x -> oMapper.convertValue(x, Map.class).size() == size);
        return this;
    }

    public final void shape(Map<String, BaseSchema> schemas) {
        /*Predicate<Map<String, BaseSchema>> containsCheck = (Map<String, BaseSchema> toCheck) -> {
            return schemas.keySet().stream().allMatch(key -> toCheck.containsKey(key));
        };*/


        Predicate<Map<String, BaseSchema>> valuesCheck = (Map<String, BaseSchema> toCheck) -> schemas.entrySet()
                .stream().allMatch(node -> node.getValue().isValid(toCheck.get(node.getKey())));

        shape = true;
        shapecheck = valuesCheck;
    }
}
