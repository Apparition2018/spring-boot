package com.ljh;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.ServiceLoader;

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
}
