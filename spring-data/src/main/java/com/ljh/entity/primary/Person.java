package com.ljh.entity.primary;

import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

/**
 * Person
 *
 * @author ljh
 * created on 2021/9/2 23:25
 */
@Data
@Entry(base = "ou=resident,dc=zs,dc=com", objectClasses = "inetOrgPerson")
public class Person {
    @Id
    private Name id;
    @DnAttribute(value = "uid", index = 3)
    private String uid;
    @Attribute(name = "cn")
    private String commonName;
    @Attribute(name = "sn")
    private String surname;
    private String userPassword;
}
