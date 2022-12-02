package com.ljh.repository.primary;

import com.ljh.entity.primary.Zip;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * ZipRepository
 *
 * @author ljh
 * created on 2022/12/2 17:03
 */
public interface ZipRepository extends MongoRepository<Zip, String> {
}
