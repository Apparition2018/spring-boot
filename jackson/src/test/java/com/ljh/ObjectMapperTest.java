package com.ljh;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.ljh.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * ObjectMapper
 * ObjectMapper 是 jackson-databind 模块最为重要的一个类，它完成了 coder 对数据绑定的几乎所有功能。
 * ObjectMapper 是面向用户的高层 API，底层依赖于 Streaming API 来实现读/写。
 * 1 读取和写入JSON的功能
 * -  1.1 普通 POJO 的序列化/反序列化
 * -  1.2 JSON 树模型的读/写
 * 2 可以高度定制，以使用不同风格的 JSON
 * -  2.1 Feature
 * -  2.2 com.fasterxml.jackson.databind.Module
 * 3 支持更高级的对象概念：多态泛型、对象标识
 * 4 充当其它更高级的 API：ObjectReader 和 ObjectWriter 的工厂
 *
 * @author ljh
 * created on 2021/7/18 12:59
 */
@Slf4j
public class ObjectMapperTest {

    /**
     * JsonMapper 为 ObjectMapper 的子类
     */
    @Test
    public void testJsonMapper() throws JsonProcessingException {
        JsonMapper jsonMapper = JsonMapper.builder().build();

        // String   writeValueAsString(Object value)
        // 写成字符串形式（最常用）
        String json = jsonMapper.writeValueAsString(new Person("ljh", 31, new Date()));
        log.info("\n========== Obj → String ==========\n" + json);
        // T        readValue(String content, Class<T> valueType)
        // 读为指定 Class 类型的对象
        Person person = jsonMapper.readValue(json, Person.class);
        log.info("\n========== String → Obj ==========\n" + person + "\n");

        json = jsonMapper.writeValueAsString(Arrays.asList(1, 2, 3));
        log.info("\n========== List<Object> → String ==========\n" + json);
        // T        readValue(String content, TypeReference<T> valueTypeRef)
        // 读为指定 TypeReference 类型的对象，一般用于泛型集合/Map的反序列化
        List<Long> list = jsonMapper.readValue(json, new TypeReference<List<Long>>() {
        });
        log.info("\n========== String → List<Object> ==========\n" + list);
    }
}
