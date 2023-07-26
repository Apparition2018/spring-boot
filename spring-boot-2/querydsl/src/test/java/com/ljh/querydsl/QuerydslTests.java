package com.ljh.querydsl;

import com.ljh.querydsl.entity.QUser;
import com.ljh.querydsl.entity.User;
import com.ljh.querydsl.vo.UserVO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * QuerydslTests
 *
 * @author Arsenal
 * created on 2021/3/16 23:09
 */
@Slf4j
@SpringBootTest
@Rollback(false)
@Transactional
public class QuerydslTests {

    private final EntityManager entityManager;
    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public QuerydslTests(EntityManager entityManager, JPAQueryFactory jpaQueryFactory) {
        this.entityManager = entityManager;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @BeforeEach
    void before() {
    }

    /**
     * 创建用户
     */
    @Test
    void create() {
        for (int i = 0; i < 10; i++) {
            User user = new User(i + "", "admin" + i, "123", "测试用户" + i, 1, "155071812" + i);
            entityManager.persist(user);
        }
    }

    /**
     * 修改
     */
    @Test
    void update() {
        QUser qUser = QUser.user;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qUser.username.like("%admin%")).and(qUser.sex.eq(2));
        long update = jpaQueryFactory.update(qUser).set(qUser.password, "123456").where(booleanBuilder).execute();
        log.info("update: {}", update);
    }

    /**
     * 删除
     */
    @Test
    void delete() {
        QUser qUser = QUser.user;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qUser.username.like("%admin%")).and(qUser.sex.eq(2));
        long delete = jpaQueryFactory.delete(qUser).where(booleanBuilder).execute();
        log.info("delete: {}", delete);
    }

    /**
     * 查询
     */
    @Test
    void retrieve() {
        QUser qUser = QUser.user;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qUser.sex.eq(1));
        List<User> userList = jpaQueryFactory.selectFrom(qUser).where(booleanBuilder).orderBy(qUser.id.asc()).fetch();
        for (User user : userList) {
            log.info(user.getId() + " " + user.getRealName());
        }
    }

    /**
     * 查询字段
     */
    @Test
    void retrieve2() {
        QUser qUser = QUser.user;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qUser.sex.eq(1));
        List<String> nameList = jpaQueryFactory.select(qUser.username).from(qUser).where(booleanBuilder).orderBy(qUser.id.asc()).fetch();
        for (String name : nameList) {
            log.info(name);
        }
    }

    /**
     * 查询 VO
     */
    @Test
    void retrieve3() {
        QUser qUser = QUser.user;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qUser.sex.eq(1));
        List<UserVO> userVOList = jpaQueryFactory.select(Projections.bean(UserVO.class, qUser.id, qUser.username.as("name"))).from(qUser).where(booleanBuilder).orderBy(qUser.id.asc()).fetch();
        for (UserVO userVO : userVOList) {
            log.info(userVO.getName());
        }
    }

    @Test
    void count() {
        QUser qUser = QUser.user;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qUser.sex.eq(1));
        long count = jpaQueryFactory.selectFrom(qUser).where(booleanBuilder).fetchCount();
        log.info("count: {}", count);
    }

    @Test
    void distinct() {
        QUser qUser = QUser.user;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qUser.sex.eq(1));
        List<String> realNameList = jpaQueryFactory.selectDistinct(qUser.realName).from(qUser).where(booleanBuilder).fetch();
        for (String realName : realNameList) {
            log.info("realName: {}", realName);
        }
    }

    @Test
    void page() {
        QUser qUser = QUser.user;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qUser.sex.eq(1));
        QueryResults<User> queryResults = jpaQueryFactory.selectFrom(qUser).where(booleanBuilder).orderBy(qUser.id.asc()).offset(0).limit(5).fetchResults();
        log.info("total: {}", queryResults.getTotal());
        log.info("size: {}", queryResults.getResults().size());
    }

    @Test
    void contextLoads() {
    }
}
