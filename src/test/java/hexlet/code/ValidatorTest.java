package hexlet.code;

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
    public void serverTest() {
        Validator v = new Validator();
        StringSchema s = v.string();
        assertEquals(s.isValid(""), true);
    }

   @Test
    public void notValidalidNullTest() {
        Validator v = new Validator();
        NumberSchema i = v.number();
        assertEquals(i.isValid(null), false);
        StringSchema s = v.string();
        assertEquals(s.isValid(null), false);
        MapSchema m = v.map();
        assertEquals(m.isValid(null), false);
    }

    @Test
    public void validNumberTest() {
        Validator v = new Validator();
        NumberSchema i = v.number();
        assertEquals(i.isValid(POSITIVE), true);
        StringSchema s = v.string();
        assertEquals(s.isValid(POSITIVE), false);
        MapSchema m = v.map();
        assertEquals(m.isValid(POSITIVE), false);
    }

    @Test
    public void validStringTest() {
        Validator v = new Validator();
        NumberSchema i = v.number();
        assertEquals(i.isValid("cwecew"), false);
        StringSchema s = v.string();
        assertEquals(s.isValid("cwecew"), true);
        MapSchema m = v.map();
        assertEquals(m.isValid("cwecew"), false);
    }

    @Test
    public void validMapTest() {
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        Validator v = new Validator();
        NumberSchema i = v.number();
        assertEquals(i.isValid(data), false);
        StringSchema s = v.string();
        assertEquals(s.isValid(data), false);
        MapSchema m = v.map();
        assertEquals(m.isValid(data), true);
    }

    @Test
    public void emptyStringTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertEquals(schema.required(""), false);
    }

    @Test
    public void nullStringTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertEquals(schema.required(null), false);
    }

    @Test
    public void defaultRequiredStringTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertEquals(schema.required("v"), true);
    }

    @Test
    public void spaceStringTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertEquals(schema.required(" "), true);
    }

    @Test
    public void defaultMinLengthTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertEquals(schema.minLength(" ",1), true);
    }

    @Test
    public void outofMinLengthTest1() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertEquals(schema.minLength("",1), false);
    }

    @Test
    public void outofMinLengthTest2() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertEquals(schema.minLength("22",1), true);
    }

    @Test
    public void trueContainsTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertEquals(schema.contains("""
                «Мой дядя самых честных правил,
                Когда не в шутку занемог,
                Он уважать себя заставил
                \s""", "утк"), true);
    }

    @Test
    public void falseContainsTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertEquals(schema.contains("""
                «Мой дядя самых честных правил,
                Когда не в шутку занемог,
                Он уважать себя заставил
                \s""", "fwevwe"), false);
    }

    @Test
    public void nullIntTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertEquals(schema.required(null), false);
    }

    @Test
    public void defaultIntTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertEquals(schema.required(POSITIVE), true);
    }

    @Test
    public void stringIntTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertEquals(schema.required("5"), false);
    }

    @Test
    public void positiveIntPositiveTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertEquals(schema.positive(POSITIVE), true);
    }

    @Test
    public void zeroIntPositiveTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertEquals(schema.positive(0), false);
    }

    @Test
    public void negativeIntPositiveTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertEquals(schema.positive(NEGATIVE), false);
    }

    @Test
    public void positiveRangeIntTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertEquals(schema.range(POSITIVE, 0, POSITIVE), true);
        assertEquals(schema.range(POSITIVE, NEGATIVE, 0), false);
        assertEquals(schema.range(POSITIVE, 0, POSITIVE), true);
    }

    @Test
    public void negativeRangeIntTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertEquals(schema.range(NEGATIVE, NEGATIVE, POSITIVE), true);
    }

    @Test
    public void nullMapTest() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        assertEquals(schema.required(null), false);
        assertEquals(schema.sizeof(null,0), false);
    }

    @Test
    public void emptyMapTest() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        assertEquals(schema.required(new HashMap()), true);
    }

    @Test
    public void notEmptyMapTest() {
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        Validator v = new Validator();
        MapSchema schema = v.map();
        assertEquals(schema.required(data), true);
    }

    @Test
    public void sizeOfTrueMapTest() {
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        Validator v = new Validator();
        MapSchema schema = v.map();
        assertEquals(schema.required(data), true);
        assertEquals(schema.sizeof(data,1), true);

    }

    @Test
    public void sizeOfNotTrueMapTest() {
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        Validator v = new Validator();
        MapSchema schema = v.map();
        assertEquals(schema.required(data), true);
        assertEquals(schema.sizeof(data,2), false);
    }

    /*@Test
    public void nestedValidationTest() {

        //MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        Validator v = new Validator(schemas);
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());



        MapSchema schema = v.map();
        schema.shape(schemas);
        assertEquals(schema.required(), true);
        assertEquals(schema.sizeof(2), false);
    }*/
}
