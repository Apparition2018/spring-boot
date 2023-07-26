package com.ljh.transaction.dao;

import com.ljh.transaction.domain.OperationLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * OperationLogDao
 *
 * @author Arsenal
 * created on 2020/1/2 17:53
 */
public interface OperationLogDao extends JpaRepository<OperationLog, Long> {
}
