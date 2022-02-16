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

   /* @Test
    public void defaultIntTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertEquals(schema.isValid(null), true); // true
        schema.required();
        assertEquals(schema.isValid(null), false); // false
        assertEquals(schema.isValid(POSITIVE), true); // true
        assertEquals(schema.isValid("5"), false); // false
        assertEquals(schema.positive().isValid(POSITIVE), true); // true
        assertEquals(schema.isValid(NEGATIVE), false); // false
    }*/

    @Test
    public void serverTest() {
        Validator v = new Validator();
        StringSchema s = v.string();
        assertEquals(s.isValid(""), true);
    }

    @Test
    public void notValidalidNullTest() {
        Validator v = new Validator();
        NumberSchema i = v.number();
        i.required();
        assertEquals(i.isValid(null), false);
        StringSchema s = v.string();
        s.required();
        assertEquals(s.isValid(null), false);
        MapSchema m = v.map();
        m.required();
        assertEquals(m.isValid(null), false);
    }


    @Test
    public void emptyStringTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertEquals(schema.required().isValid(""), false);
    }


    @Test
    public void nullStringTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertEquals(schema.required().isValid(null), false);
    }

    @Test
    public void defaultRequiredStringTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertEquals(schema.required().isValid("v"), true);
    }

    @Test
    public void spaceStringTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertEquals(schema.required().isValid(" "), true);
    }

    @Test
    public void defaultMinLengthTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertEquals(schema.minLength(1).isValid(" "), true);
    }

    @Test
    public void outofMinLengthTest1() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertEquals(schema.minLength(1).isValid(""), false);
    }

    @Test
    public void outofMinLengthTest2() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertEquals(schema.minLength(1).isValid("22"), true);
    }

    @Test
    public void trueContainsTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertEquals(schema.contains("утк").isValid("""
                  Мой дядя самых честных правил,
                  Когда не в шутку занемог,
                  Он уважать себя заставил
                \s"""), true);
    }

    @Test
    public void falseContainsTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertEquals(schema.contains("ewfwe").isValid("""
                  Мой дядя самых честных правил,
                  Когда не в шутку занемог,
                  Он уважать себя заставил
                \s"""), false);
    }

    @Test
    public void positiveIntPositiveTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertEquals(schema.positive().isValid(POSITIVE), true);
    }

    @Test
    public void zeroIntPositiveTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertEquals(schema.positive().isValid(0), false);
    }


    @Test
    public void nullIntPositiveTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertEquals(schema.positive().isValid(null), true);
    }

    @Test
    public void nullIntRequiredPositiveTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertEquals(schema.required().positive().isValid(null), false);
    }


    @Test
    public void negativeIntPositiveTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertEquals(schema.positive().isValid(NEGATIVE), false);
    }

    @Test
    public void nullRequiredTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertEquals(schema.positive().isValid(null), true);
    }

    @Test
    public void positiveRangeIntTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertEquals(schema.range(0, POSITIVE).isValid(POSITIVE), true);
        assertEquals(schema.range(NEGATIVE, 0).isValid(POSITIVE), false);
        assertEquals(schema.range(0, POSITIVE).isValid(POSITIVE), false);
    }

    @Test
    public void negativeRangeIntTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertEquals(schema.range(NEGATIVE, POSITIVE).isValid(NEGATIVE), true);
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
