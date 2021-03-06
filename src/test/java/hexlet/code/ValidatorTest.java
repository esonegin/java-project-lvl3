package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorTest {

    static final int POSITIVE = 100;
    static final int NEGATIVE = -13;
    static final int RANGESTART = 5;
    static final int RANGEFINISH = 10;
    static final int RANGEOUTPLUS = 11;
    static final int RANGEOUTMINUS = 4;

    @BeforeAll
    public static void globalSetUp() {

    }

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void defaultStringTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertEquals(schema.isValid(""), true);
        assertEquals(schema.isValid(null), true);
        schema.required();
        assertEquals(schema.isValid("what does the fox say"), true); // true
        assertEquals(schema.isValid("hexlet"), true); // true
        assertEquals(schema.isValid(null), false); // false
        assertEquals(schema.isValid(""), false);
        assertEquals(schema.contains("what").isValid("what does the fox say"), true); // true
        assertEquals(schema.contains("whatthe").isValid("what does the fox say"), false); // false
        assertEquals(schema.isValid("what does the fox say"), false); // false
    }

    @Test
    public void defaultIntTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertEquals(schema.isValid(null), true);
        assertEquals(schema.isValid(POSITIVE), true);
        assertEquals(schema.isValid("5"), true);
        assertEquals(schema.isValid(NEGATIVE), true);
        assertEquals(schema.positive().isValid(POSITIVE), true);
        schema.required();
        assertEquals(schema.isValid(null), false); // false
        assertEquals(schema.isValid(POSITIVE), true); // true
        assertEquals(schema.isValid("5"), false); // false
        assertEquals(schema.positive().isValid(POSITIVE), true); // true
        assertEquals(schema.isValid(NEGATIVE), false); // false
    }

    @Test
    public void defaultIntRangeTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.required();
        schema.range(RANGESTART, RANGEFINISH);
        assertEquals(schema.isValid(RANGESTART), true); // false
        assertEquals(schema.isValid(RANGEFINISH), true); // true
        assertEquals(schema.isValid(RANGEOUTMINUS), false); // false
        assertEquals(schema.isValid(RANGEOUTPLUS), false); // true
    }

    @Test
    public void defaultMinLengthTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertEquals(schema.minLength(1).isValid(" "), true);
    }

    @Test
    public void trueContainsTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertEquals(schema.contains("??????").isValid("""
                  ?????? ???????? ?????????? ?????????????? ????????????,
                  ?????????? ???? ?? ?????????? ??????????????,
                  ???? ?????????????? ???????? ????????????????
                \s"""), true);
    }

    @Test
    public void falseContainsTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertEquals(schema.contains("ewfwe").isValid("""
                  ?????? ???????? ?????????? ?????????????? ????????????,
                  ?????????? ???? ?? ?????????? ??????????????,
                  ???? ?????????????? ???????? ????????????????
                \s"""), false);
    }

    @Test
    public void nullMapTest() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        assertEquals(schema.sizeof(0).isValid(null), true);
    }

    @Test
    public void emptyMapTest() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        assertEquals(schema.required().isValid(new HashMap()), true);
    }

    @Test
    public void notEmptyMapTest() {
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        Validator v = new Validator();
        MapSchema schema = v.map();
        assertEquals(schema.required().isValid(data), true);
    }

    @Test
    public void sizeOfTrueMapTest() {
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        Validator v = new Validator();
        MapSchema schema = v.map();
        assertEquals(schema.required().isValid(data), true);
        assertEquals(schema.sizeof(1).isValid(data), true);

    }

    @Test
    public void sizeOfNotTrueMapTest() {
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        Validator v = new Validator();
        MapSchema schema = v.map();
        assertEquals(schema.required().isValid(data), true);
        assertEquals(schema.sizeof(2).isValid(data), false);
    }

    @Test
    public void nestedValidationTest() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", POSITIVE);
        assertEquals(schema.isValid(human1), true); // true
        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertEquals(schema.isValid(human2), true);
        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertEquals(schema.isValid(human3), false); // false
        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", NEGATIVE);
        assertEquals(schema.isValid(human4), false); // false
    }
}
