package com.ljh;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ljh.entity.Person;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
     * `@JsonPropertyOrder({"name", "age", "gender"})
     * `@JsonProperty
     * `@JsonFormat
     * `@JsonIgnore
     * `@JsonRootName
     * `@JsonAnyGetter
     *
     * @see com.ljh.entity.Person
     */
    @Test
    public void testGenerator() throws JsonProcessingException {
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        Person person = new Person("ljh", 31, new Date(), 1, new Person.Dog("旺财", Person.Color.BLACK));
        person.getOther().put("country", "cn");
        // {"P":{"name":"ljh","age":31,"gender":1,"birth":"2021-07-21 02:16:06 AM","dog":{"name":"旺财","color":"BLACK"},"hobbies":null,"country":"cn"}}
        System.out.println(objectMapper.writeValueAsString(person));
    }

    /**
     * `@JsonIgnore
     * `@JsonAnySetter
     *
     * @see com.ljh.entity.Person
     */
    @Test
    public void testParser() throws JsonProcessingException {
        String json = "{\"name\":\"ljh\",\"age\":31,\"gender\":1,\"password\":\"123\",\"country\":\"cn\"}";
        Person person = objectMapper.readValue(json, Person.class);
        // Person(birth=null, age=31, sex=1, password=null, dog=null, hobbies=null, name=ljh, other={country=cn, password=123})
        System.out.println(person);
    }

    /**
     * `@JsonCreator：Jackson 反序列化时，会默认使用无参构造函数实例化对象；
     * -    当没有无参构造函数时可使用 @JsonCreator 来指定反序列化时候的构造函数，需要配合 @JsonProperty 使用
     */
    @Test
    public void testJsonCreator() throws JsonProcessingException {
        Student student = objectMapper.readValue("{\"name\":\"ljh\",\"age\":31}", Student.class);
        System.out.println(student);
    }

    @Getter
    @Setter
    @ToString
    static class Student {
        private String name;
        private Integer age;

        @JsonCreator
        public Student(@JsonProperty("name") String name) {
            this.name = name;
        }
    }

    @Test
    public void testJsonAnySetter() {

    }

}
