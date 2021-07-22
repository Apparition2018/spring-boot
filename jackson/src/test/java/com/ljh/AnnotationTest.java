package com.ljh;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ljh.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

/**
 * Annotation
 * Jackson Annotations：https://github.com/FasterXML/jackson-docs/wiki/JacksonAnnotations
 * Jackson 全面解析--注解全讲解一：https://www.jianshu.com/p/83b0a3f4d0bf
 * Jackson 全面解析--注解全讲解二：https://www.jianshu.com/p/32c21a390e1d
 * Jackson 全面解析--注解全讲解三：https://www.jianshu.com/p/e85c3dfba052
 * Jackson 全面解析--注解全讲解四：https://www.jianshu.com/p/38e6f1f642b3
 * Jackson 全面解析--注解全讲解五：https://www.jianshu.com/p/2982116ee0ff ?
 * Jackson 全面解析--注解全讲解六：https://www.jianshu.com/p/a4e24b253c4d
 * Jackson 全面解析--注解全讲解七：https://www.jianshu.com/p/54cf66ff6e3f
 * Jackson 全面解析--注解全讲解八：https://www.jianshu.com/p/2b9ee52bd6ab
 * Jackson 全面解析--注解全讲解九：https://www.jianshu.com/p/3d98fa64473b
 * Jackson 全面解析--注解全讲解十：https://www.jianshu.com/p/4003b9756952 ?
 * Jackson 全面解析--注解全讲解十一：https://www.jianshu.com/p/38202f12dd31
 * Jackson 全面解析--注解全讲解十二：https://www.jianshu.com/p/e7483a292605 ?
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
     * `@JsonIgnoreType
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
                .setDog(new Person.Dog().setName("旺财").setColor(Person.Color.BLACK))
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
     * `@JsonIgnoreType
     * `@JsonAlias
     * `@JacksonInject
     * `@JsonAnySetter
     * `@JsonEnumDefaultValue
     *
     * @see com.ljh.entity.Person
     */
    @Test
    public void testParser() throws JsonProcessingException {
        InjectableValues.Std std = new InjectableValues.Std();
        std.addValue("age", 0);
        objectMapper.setInjectableValues(std);
        objectMapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);
        String json = "{\"name\":\"ljh\",\"birthday\":1626920254637,\"gender\":1,\"dog\":{\"name\":\"旺财\",\"color\":\"BLUE\"},\"password\":\"123\",\"country\":\"cn\"}";
        Person person = objectMapper.readValue(json, Person.class);
        // Person(birth=Thu Jul 22 10:17:34 CST 2021, age=0, sex=1, password=null, hairStyle=null, dog=Person.Dog(name=旺财, color=WHITE), hobbies=null, name=ljh, json=null, other={country=cn, password=123})
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
        // AnnotationTest.Person2(name=super ljh, age=31)
    }

    @Data
    static class Person2 {
        private String name;
        private Integer age;

        @JsonCreator
        public Person2(@JsonProperty("name") String name) {
            if (name.equals("ljh")) {
                name = "super " + name;
            }
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

    /**
     * `@JsonManagedReference
     * `@JsonBackReference
     * 解决循环依赖序列化和反序列化问题（父/子）
     */
    @Test
    public void testJsonReference() throws JsonProcessingException {
        Employee e1 = new Employee().setName("mary");
        Employee e2 = new Employee().setName("lucy");

        Boss boss = new Boss().setName("ljh").setEmployeeList(Lists.list(e1, e2));

        e1.setBoss(boss);
        e2.setBoss(boss);

        System.out.println(objectMapper.writeValueAsString(boss));
        // {"name":"ljh","employeeList":[{"name":"mary"},{"name":"lucy"}]}
    }

    @Data
    @Accessors(chain = true)
    static class Boss {
        private String name;
        @JsonManagedReference
        private List<Employee> employeeList;
    }

    @Data
    @Accessors(chain = true)
    static class Employee {
        private String name;
        @JsonBackReference
        private Boss boss;
    }

    /**
     * `@JsonIdentityInfo：解决循环依赖序列化和反序列化问题
     */
    @Test
    public void testJsonIdentityInfo() throws JsonProcessingException {
        Employee2 e1 = new Employee2().setName("mary");
        Employee2 e2 = new Employee2().setName("lucy");

        Boss2 boss = new Boss2().setName("ljh").setEmployeeList(Lists.list(e1, e2));

        e1.setBoss(boss);
        e2.setBoss(boss);

        System.out.println(objectMapper.writeValueAsString(boss));
        // {"name":"ljh","employeeList":[{"name":"mary","boss":"ljh"},{"name":"lucy","boss":"ljh"}]}
    }

    @Data
    @Accessors(chain = true)
    @JsonIdentityInfo(property = "name", generator = ObjectIdGenerators.PropertyGenerator.class)
    static class Boss2 {
        private String name;
        private List<Employee2> employeeList;
    }

    @Data
    @Accessors(chain = true)
    @JsonIdentityInfo(property = "name", generator = ObjectIdGenerators.PropertyGenerator.class)
    static class Employee2 {
        private String name;
        private Boss2 boss;
    }

}
