package com.ljh;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.*;
import com.ljh.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * JsonNode
 * 当不想创建一个 JavaBean 与 JSON 属性相对应时，可以使用 JsonNode
 * 1 ValueNode
 * -  1.1 NumericNode
 * -  1.2 NullNode
 * -  1.3 BooleanNode
 * -  1.4 TestNode
 * -  1.5 POJONode
 * -  1.6 BinaryNode
 * 2 ContainerNode
 * -  2.1 ObjectNode: 类比 Map，采用 K-V 结构存储
 * -  2.2 ArrayNode: 类比 Collection、数组
 *
 * @author ljh
 * created on 2021/7/18 16:55
 */
@Slf4j
public class JsonNodeTest extends com.ljh.Test {

    @Test
    public void testValueNode() {
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;

        NumericNode numericNode = jsonNodeFactory.numberNode(1);
        // Numeric true : 1
        log.info("Numeric {} : {}", numericNode.isNumber(), numericNode.intValue());

        NullNode nullNode = jsonNodeFactory.nullNode();
        // Null true : null
        log.info("Null {} : {}", nullNode.isNull(), nullNode.asText());

        JsonNode missingNode = jsonNodeFactory.missingNode();
        // Missing true :
        log.info("Missing {} : {}", missingNode.isMissingNode(), missingNode.asText());

        ValueNode pojoNode = jsonNodeFactory.pojoNode(new Person("ljh", 31));
        // Pojo true : Person(name=ljh, age=31, dog=null, hobbies=null)
        log.info("Pojo {} : {}", pojoNode.isPojo(), pojoNode.asText());
    }

    @Test
    public void testContainerNode() {
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;

        ArrayNode arrayNode = jsonNodeFactory.arrayNode();
        arrayNode.add("java").add("javascript").add("nodejs");

        ObjectNode dogNode = jsonNodeFactory.objectNode();
        dogNode.put("name", "旺财");
        dogNode.put("age", 3);

        ObjectNode rootNode = jsonNodeFactory.objectNode();
        rootNode.put("name", "ljh");
        rootNode.put("age", 31);
        rootNode.set("languages", arrayNode);
        rootNode.set("dog", dogNode);

        log.info(rootNode.toPrettyString());
        log.info(rootNode.get("dog").get("name").toPrettyString());
    }

    @Test
    public void testObjectMapperTree() throws IOException {

        Person person = new Person("ljh", 31);
        person.setDog(new Person.Dog("旺财", Person.Color.WHITE));

        ObjectMapper objectMapper = new ObjectMapper();
        // <T extends JsonNode> T   valueToTree(Object fromValue)
        JsonNode jsonNode = objectMapper.valueToTree(person);
        log.info(jsonNode.toPrettyString());

        // JsonNode                 readTree(File file)
        jsonNode = objectMapper.readTree(new File(PERSON_JSON_FILE));
        log.info(jsonNode.toPrettyString());

        for (JsonNode nextJsonNode : jsonNode) {
            if (nextJsonNode.isContainerNode()) {
                if (nextJsonNode.isObject()) {
                    log.info("{} : {}", nextJsonNode.get("name"), nextJsonNode.get("color"));
                }
            } else {
                log.info(nextJsonNode.asText());
            }
        }

        JsonFactory jsonFactory = new JsonFactory();
        try (JsonGenerator jsonGenerator = jsonFactory.createGenerator(System.out, JsonEncoding.UTF8)) {
            // void                 writeTree(JsonGenerator g, JsonNode rootNode)
            objectMapper.writeTree(jsonGenerator, jsonNode);
        }
    }

    @Test
    public void testObjectMapperFeature() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper
                /* === SerializationFeature === */
                // 格式化输出，默认 false
                .enable(SerializationFeature.INDENT_OUTPUT)
                // 遇到无法转换的属性则报错，默认 true
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                // 日期转为时间戳，默认 true
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

                /* === DeserializationFeature === */
                // 遇到未知属性报错，默认 true
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)


                .setDefaultPropertyInclusion(JsonInclude.Include.NON_EMPTY)
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                // 设置 DateFormat
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
}
