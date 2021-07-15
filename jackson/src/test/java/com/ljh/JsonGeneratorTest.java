package com.ljh;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author ljh
 * created on 2021/7/15 17:58
 */
public class JsonGeneratorTest extends com.ljh.Test {

    @Test
    public void test() throws IOException {
        JsonFactory jsonFactory = new JsonFactory();

        try (JsonGenerator jsonGenerator = jsonFactory.createGenerator(new File(PERSON_JSON_FILE), JsonEncoding.UTF8)) {
            jsonGenerator.writeStartObject(); // {

            jsonGenerator.writeStringField("name", "ljh");
            jsonGenerator.writeNumberField("age", 31);

            // 写入 Dog 对象
            jsonGenerator.writeFieldName("dog");
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", "旺财");
            jsonGenerator.writeStringField("color", "WHITE");
            jsonGenerator.writeEndObject();

            // 写入一个数组
            jsonGenerator.writeFieldName("hobbies");
            jsonGenerator.writeStartArray();
            jsonGenerator.writeString("篮球");
            jsonGenerator.writeString("football");
            jsonGenerator.writeEndArray();

            jsonGenerator.writeEndObject(); // }
        }
    }
}
