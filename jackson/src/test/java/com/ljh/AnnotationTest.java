package com.ljh;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.log.Log;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Annotation
 * Jackson Annotations：https://github.com/FasterXML/jackson-docs/wiki/JacksonAnnotations
 * Jackson 全面解析--注解全讲解一：https://www.jianshu.com/p/83b0a3f4d0bf
 *
 *
 * @author ljh
 * created on 2021/7/19 23:44
 */
@Slf4j
public class AnnotationTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     *
     * `@JsonCreator：Jackson 反序列化时，会默认使用无参构造函数实例化对象；
     * -    当没有无参构造函数时可使用 @JsonCreator 来指定反序列化时候的构造函数，需要配合 @JsonProperty 使用
     */
    @Test
    public void testJsonCreator() throws JsonProcessingException {
        Student student = objectMapper.readValue("{\"name\" : \"ljh\"}", Student.class);
        log.info(student.toString());
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    static class Student {

        private String name;
        private Integer age;

        @JsonCreator
        public Student(@JsonProperty("name") String name) {
            this.name = name;
        }
    }

}
