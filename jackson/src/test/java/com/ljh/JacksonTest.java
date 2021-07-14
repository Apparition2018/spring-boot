package com.ljh;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljh.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * JacksonTest
 *
 * @author ljh
 * created on 2021/7/15 0:56
 */
@SpringBootTest
public class JacksonTest {

    @Test
    public void test() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = new Person();
        person.setName("Tom");
        person.setAge(40);
        person.setDate(new Date());
        // 序列化
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
        System.out.println(json);
        // 反序列化
        System.out.println(objectMapper.readValue(json, Person.class));
    }
}
