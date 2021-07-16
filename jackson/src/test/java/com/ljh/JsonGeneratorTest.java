package com.ljh;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.ljh.entity.Person;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

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

    @Test
    public void testFeature() throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        try (JsonGenerator jsonGenerator = jsonFactory.createGenerator(System.out, JsonEncoding.UTF8)) {
            // 自动关闭流，默认 true
            jsonGenerator.enable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
            // 自动补齐，默认 true
            jsonGenerator.enable(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT);
            // 自动 flush，默认 true
            jsonGenerator.enable(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM);
            // 使用 BigDecimal#toPlainString() 输出，默认 false
            jsonGenerator.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);

        }
    }

    /**
     * 自定义 ObjectCodec
     */
    @Test
    public void testMyObjectCodec() throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        try (JsonGenerator jsonGenerator = jsonFactory.createGenerator(System.out, JsonEncoding.UTF8)) {
            jsonGenerator.setCodec(new MyObjectCodec());
            jsonGenerator.writeObject(new Person("ljh", 31));
        }
    }

    public static class MyObjectCodec extends ObjectCodec {

        @Override
        public Version version() {
            return null;
        }

        @Override
        public <T> T readValue(JsonParser jsonParser, Class<T> aClass) throws IOException {
            return null;
        }

        @Override
        public <T> T readValue(JsonParser jsonParser, TypeReference<T> typeReference) throws IOException {
            return null;
        }

        @Override
        public <T> T readValue(JsonParser jsonParser, ResolvedType resolvedType) throws IOException {
            return null;
        }

        @Override
        public <T> Iterator<T> readValues(JsonParser jsonParser, Class<T> aClass) throws IOException {
            return null;
        }

        @Override
        public <T> Iterator<T> readValues(JsonParser jsonParser, TypeReference<T> typeReference) throws IOException {
            return null;
        }

        @Override
        public <T> Iterator<T> readValues(JsonParser jsonParser, ResolvedType resolvedType) throws IOException {
            return null;
        }

        @Override
        public void writeValue(JsonGenerator jsonGenerator, Object o) throws IOException {
            Person person = (Person) o;
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", person.getName());
            jsonGenerator.writeNumberField("age", person.getAge());
            jsonGenerator.writeEndObject();
        }

        @Override
        public <T extends TreeNode> T readTree(JsonParser jsonParser) throws IOException {
            return null;
        }

        @Override
        public void writeTree(JsonGenerator jsonGenerator, TreeNode treeNode) throws IOException {

        }

        @Override
        public TreeNode createObjectNode() {
            return null;
        }

        @Override
        public TreeNode createArrayNode() {
            return null;
        }

        @Override
        public JsonParser treeAsTokens(TreeNode treeNode) {
            return null;
        }

        @Override
        public <T> T treeToValue(TreeNode treeNode, Class<T> aClass) throws JsonProcessingException {
            return null;
        }
    }

}
