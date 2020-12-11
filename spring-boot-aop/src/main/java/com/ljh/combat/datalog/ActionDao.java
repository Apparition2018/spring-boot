package com.ljh.combat.datalog;

import com.ljh.combat.domain.Action;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * ActionDao
 *
 * @author Arsenal
 * created on 2020/1/3 0:22
 */
public interface ActionDao extends MongoRepository<Action, String> {
}
