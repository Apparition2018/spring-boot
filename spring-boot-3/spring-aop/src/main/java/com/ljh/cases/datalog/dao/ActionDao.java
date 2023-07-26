package com.ljh.cases.datalog.dao;

import com.ljh.cases.datalog.domain.Action;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * ActionDao
 *
 * @author Arsenal
 * created on 2020/1/3 0:22
 */
public interface ActionDao extends MongoRepository<Action, String> {
}
