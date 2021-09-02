package com.ljh.repository.primary;

import com.ljh.entity.primary.User2;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * UserMongoRepository
 *
 * @author ljh
 * created on 2021/9/2 17:32
 */
public interface UserMongoRepository extends MongoRepository<User2, Long> {

    User2 findByName(String name);
}
