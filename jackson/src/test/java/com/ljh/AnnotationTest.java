package com.ljh;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ljh.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * Annotation
 * Jackson Annotations：https://github.com/FasterXML/jackson-docs/wiki/JacksonAnnotations
 * Jackson 全面解析--注解全讲解一：https://www.jianshu.com/p/83b0a3f4d0bf
 *
 * @author ljh
 * created on 2021/7/19 23:44
 */
public class AnnotationTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * `@JsonInclude
     * `@JsonIgnoreProperties
     * `@JsonIgnore
     * `@JsonPropertyOrder
     * `@JsonProperty
     * `@JsonRootName
     * `@JsonNaming
     * `@JsonFormat
     * `@JsonRawValue
     * `@JsonAnyGetter
     *
     * @see com.ljh.entity.Person
     */
    @Test
    public void testGenerator() throws JsonProcessingException {
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        Person person = new Person()
                .setName("ljh")
                .setAge(31)
                .setBirth(new Date())
                .setSex(1)
                .setHairStyle("pony-tail")
                .setDog(new Person.Dog("旺财", Person.Color.BLACK))
                .setJson("{\"language\":\"chinese\"}");
        person.getOther().put("country", "cn");
        // {"P":{"name":"ljh","age":31,"gender":1,"birth":"2021-07-22 15:04:35 PM","hairstyle":"pony-tail","dog":{"name":"旺财","color":"BLACK"},"json":{"language":"chinese"},"country":"cn"}}
        System.out.println(objectMapper.writeValueAsString(person));
    }

    /**
     * `@JsonNaming
     * `@JsonProperty
     * `@JsonIgnoreProperties
     * `@JsonIgnore
     * `@JsonAlias
     * `@JsonAnySetter
     *
     * @see com.ljh.entity.Person
     */
    @Test
    public void testParser() throws JsonProcessingException {
        String json = "{\"name\":\"ljh\",\"birthday\":1626920254637,\"age\":31,\"gender\":1,\"password\":\"123\",\"country\":\"cn\"}";
        Person person = objectMapper.readValue(json, Person.class);
        // Person(birth=Thu Jul 22 10:17:34 CST 2021, age=31, sex=1, password=null, hairStyle=null, dog=null, hobbies=null, name=ljh, json=null, other={country=cn, password=123})
        System.out.println(person);
    }

    /**
     * `@JsonCreator：Jackson 反序列化时，会默认使用无参构造函数实例化对象；
     * -    当没有无参构造函数时可使用 @JsonCreator 来指定反序列化时候的构造函数，需要配合 @JsonProperty 使用
     */
    @Test
    public void testJsonCreator() throws JsonProcessingException {
        Person2 person = objectMapper.readValue("{\"name\":\"ljh\",\"age\":31}", Person2.class);
        System.out.println(person);
        // JsonCreator
        // AnnotationTest.Person2(name=ljh, age=31)
    }

    @Data
    static class Person2 {
        private String name;
        private Integer age;

        @JsonCreator
        public Person2(@JsonProperty("name") String name) {
            System.out.println("JsonCreator");
            this.name = name;
        }
    }

    /**
     * `@JsonValue：序列化只返回这个字段的值
     */
    @Test
    public void testJsonValue() throws JsonProcessingException {
        Person3 person = new Person3("ljh", 31);
        System.out.println(objectMapper.writeValueAsString(person)); // "ljh"
    }

    @Data
    @AllArgsConstructor
    static class Person3 {
        @JsonValue
        private String name;
        private Integer age;

    }

}
