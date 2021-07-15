package com.ljh;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author ljh
 * created on 2021/7/15 17:58
 */
public class JsonGeneratorTest {

    @Test
    public void test() throws IOException {
        JsonFactory jsonFactory = new JsonFactory();

        try(JsonGenerator jsonGenerator = jsonFactory.createGenerator(System.out, JsonEncoding.UTF8)) {
            jsonGenerator.writeStartObject(); // {
            jsonGenerator.writeStringField("name", "ljh");
            jsonGenerator.writeNumberField("age", 31);
            jsonGenerator.writeEndObject(); // }
        }
    }
}
