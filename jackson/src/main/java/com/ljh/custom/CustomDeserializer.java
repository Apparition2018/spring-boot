package com.ljh.custom;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.ljh.entity.Person;

import java.io.IOException;

/**
 * CustomDeserializer
 *
 * @author ljh
 * created on 2021/7/22 11:15
 */
public class CustomDeserializer extends StdDeserializer<Person> {
    private static final long serialVersionUID = 8522054763648986402L;

    public CustomDeserializer() {
        super(Person.class);
    }

    @Override
    public Person deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return null;
    }
}
