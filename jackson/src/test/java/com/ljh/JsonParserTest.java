package com.ljh;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.ljh.entity.Person;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author ljh
 * created on 2021/7/16 1:52
 */
@Slf4j
public class JsonParserTest extends com.ljh.Test {

    @Test
    public void test() throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        try (JsonParser jsonParser = jsonFactory.createParser(new File(PERSON_JSON_FILE))) {
            JsonToken jsonToken;
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = jsonParser.getCurrentName();
                if ("name".equals(fieldName)) {
                    jsonToken = jsonParser.nextToken();
                    log.info("token 类型：{}", jsonToken);
                    log.info(jsonParser.getText());
                } else if ("age".equals(fieldName)) {
                    jsonToken = jsonParser.nextToken();
                    log.info("token 类型：{}", jsonToken);
                    log.info(jsonParser.getIntValue() + "");
                } else if ("dog".equals(fieldName)) {
                    jsonToken = jsonParser.nextToken();
                    log.info("token 类型：{}", jsonToken);
                    while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                        String dogFieldName = jsonParser.getCurrentName();
                        if ("name".equals(dogFieldName)) {
                            jsonToken = jsonParser.nextToken();
                            log.info("token 类型：{}", jsonToken);
                            log.info(jsonParser.getText());
                        } else if ("color".equals(dogFieldName)) {
                            jsonToken = jsonParser.nextToken();
                            log.info("token 类型：{}", jsonToken);
                            log.info(jsonParser.getText());
                        }
                    }
                } else if ("hobbies".equals(fieldName)) {
                    jsonToken = jsonParser.nextToken();
                    log.info("token 类型：{}", jsonToken);
                    while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                        log.info(jsonParser.getText());
                    }
                }
            }

        }
    }

    /**
     * 自定义 ObjectCodec
     */
    @Test
    public void testMyObjectCodec() throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        try (JsonParser jsonParser = jsonFactory.createParser(new File(PERSON_JSON_FILE))) {
            jsonParser.setCodec(new MyObjectCodec());
            log.info("person: {}", jsonParser.readValueAs(Person.class));
        }
    }

    public static class MyObjectCodec extends ObjectCodec {

        @Override
        public Version version() {
            return null;
        }

        @SneakyThrows
        @Override
        public <T> T readValue(JsonParser jsonParser, Class<T> clazz) {
            Person person = new Person();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = jsonParser.getCurrentName();
                if ("name".equals(fieldName)) {
                    person.setName(jsonParser.nextTextValue());
                } else if ("age".equals(fieldName)) {
                    person.setAge(jsonParser.nextIntValue(0));
                } else if ("dog".equals(fieldName)) {
                    jsonParser.nextToken();
                    Person.Dog dog = new Person.Dog();
                    person.setDog(dog);
                    while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                        String dogFieldName = jsonParser.getCurrentName();
                        if ("name".equals(dogFieldName)) {
                            dog.setName(jsonParser.nextTextValue());
                        } else if ("color".equals(dogFieldName)) {
                            dog.setColor(Person.Color.valueOf(jsonParser.nextTextValue()));
                        }
                    }
                } else if ("hobbies".equals(fieldName)) {
                    jsonParser.nextToken();
                    List<String> hobbies = new ArrayList<>();
                    person.setHobbies(hobbies);
                    while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                        hobbies.add(jsonParser.getText());
                    }
                }
            }
            return (T) person;
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
        public <T> Iterator<T> readValues(JsonParser jsonParser, Class<T> clazz) throws IOException {
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
        public <T> T treeToValue(TreeNode treeNode, Class<T> clazz) throws JsonProcessingException {
            return null;
        }
    }
}
