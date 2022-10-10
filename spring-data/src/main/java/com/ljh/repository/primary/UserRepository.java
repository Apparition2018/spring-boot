package com.ljh.repository.primary;

import com.ljh.entity.primary.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * UserRepository
 *
 * @author ljh
 * created on 2021/8/30 11:41
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    @Query("from User u where u.name = :name")
    User queryByName(@Param("name") String name);

    User findByNameAndAge(String name, Integer age);
}
