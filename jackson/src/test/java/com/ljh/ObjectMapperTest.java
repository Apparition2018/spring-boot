package com.ljh;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.ljh.custom.CustomDeserializer;
import com.ljh.custom.CustomSerializer;
import com.ljh.entity.Person;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

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
        System.out.println("========== Obj → String ==========\n" + json);
        // T        readValue(String content, Class<T> valueType)
        // 读为指定 Class 类型的对象
        Person person = jsonMapper.readValue(json, Person.class);
        System.out.println("========== String → Obj ==========\n" + person + "\n");

        json = jsonMapper.writeValueAsString(Arrays.asList(1, 2, 3));
        System.out.println("========== List<Object> → String ==========\n" + json);
        // T        readValue(String content, TypeReference<T> valueTypeRef)
        // 读为指定 TypeReference 类型的对象，一般用于泛型集合/Map的反序列化
        List<Long> list = jsonMapper.readValue(json, new TypeReference<List<Long>>() {
        });
        System.out.println("========== String → List<Object> ==========\n" + list);
    }

    @Test
    public void testFeature() {
        // 自定义模块
        SimpleModule simpleModule = new SimpleModule();
        // 自定义序列
        simpleModule.addSerializer(new CustomSerializer());
        // 自定义反序列化
        simpleModule.addDeserializer(Person.class, new CustomDeserializer());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper
                /* === SerializationFeature === */
                // 格式化输出，默认 false
                .enable(SerializationFeature.INDENT_OUTPUT)
                // 没有找到属性的访问器或注解则报错，默认 true
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                // 日期转为时间戳，默认 true
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

                /* === DeserializationFeature === */
                // 遇到未知属性报错，默认 true
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

                // 设置空如何序列化
                .setDefaultPropertyInclusion(JsonInclude.Include.NON_EMPTY)
                // 同上，底层调用了 setDefaultPropertyInclusion(Include incl)
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                // 设置 DateFormat
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                // 设置 TimeZone
                .setTimeZone(SimpleTimeZone.getTimeZone("GMT+8"))
                // 设置可视化
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)

                // 注册自定义模块
                .registerModule(simpleModule)
                // 注册模块
                .registerModule(new JavaTimeModule())
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                // 自动注册所有模块
                .findAndRegisterModules();
    }
}
