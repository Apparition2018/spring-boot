package com.ljh.repository.primary;

import com.ljh.entity.primary.Person;
import org.springframework.data.ldap.repository.LdapRepository;

/**
 * PersonRepository
 *
 * @author ljh
 * created on 2021/9/3 0:55
 */
public interface PersonRepository extends LdapRepository<Person> {
}
