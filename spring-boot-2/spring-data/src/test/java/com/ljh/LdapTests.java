package com.ljh;

import com.ljh.entity.ldap.Person;
import com.ljh.repository.ldap.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.core.LdapTemplate;

/**
 * <a href="https://blog.didispace.com/spring-boot-learning-24-6-2/">使用 LDAP 来管理用户与组织数据</a>
 * <p><a href="https://www.jianshu.com/p/1749a01443ce">SpringBoot + LDAP</a>
 *
 * @author ljh
 * created on 2021/9/3 0:57
 */
@SpringBootTest
public class LdapTests {

    @Autowired
    private LdapTemplate ldapTemplate;
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testLDAP() {
        ldapTemplate.findAll(Person.class).forEach(System.out::println);
        personRepository.findAll().forEach(System.out::println);
    }
}
