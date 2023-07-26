package com.ljh.repository.mongo;

import com.ljh.entity.mongo.ZipMO;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * ZipRepository
 *
 * @author ljh
 * created on 2022/12/2 17:03
 */
public interface ZipRepository extends MongoRepository<ZipMO, String> {
}
