package com.ljh.repository;

import com.ljh.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * DemoRepository
 *
 * @author ljh
 * created on 2022/8/24 16:56
 */
public interface DemoRepository extends JpaRepository<Demo, Long> {

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "truncate table demo", nativeQuery = true)
    void truncateTable();
}
