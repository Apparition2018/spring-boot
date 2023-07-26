package com.ljh;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * JdbcTests
 *
 * @author ljh
 * created on 2022/8/25 15:19
 */
@SpringBootTest
public class JdbcTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test() {
        this.batchUpdate(10, 1000);
        System.err.printf("有数据%s条%n", this.count());
        System.out.println(this.page(1, 100));
    }

    /**
     * 批量插入
     *
     * @param times 分批插入多少次
     * @param batch 每次插入多少条
     */
    private void batchUpdate(int times, int batch) {
        Connection connection = null;
        try {
            // 获取数据库连接
            connection = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();
            // 设置为不自动提交
            connection.setAutoCommit(false);
            List<Object[]> objList;
            for (int i = 0; i < times; i++) {
                objList = new ArrayList<>(1000);
                Object[] obj = new Object[1];
                String randomStr = RandomStringUtils.random(9, true, false);
                for (int j = 0; j < batch; j++) {
                    obj[0] = randomStr + j;
                    objList.add(obj);
                }
                // 批量更新
                jdbcTemplate.batchUpdate("INSERT INTO DEMO (ID, NAME) VALUES (DEMO_SEQ.NEXTVAL, ?)", objList);
            }
            // 主动提交
            connection.commit();
            // 设置为自动提交
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            // 回滚
            this.rollback(connection);
        } finally {
            // 关闭连接
            this.close(connection);
        }
    }

    /**
     * 查询条数
     */
    private Integer count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEMO", Integer.class);
    }

    /**
     * 分页查询
     */
    private List<Map<String, Object>> page(int pageNum, int pageSize) {
        String pageSql = "SELECT * FROM (SELECT *, ROWNUM RN FROM DEMO WHERE ROWNUM <= %s) WHERE RN > %s";
        return jdbcTemplate.queryForList(String.format(pageSql, pageNum * pageSize, (pageNum - 1) * pageSize));
    }

    private void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
