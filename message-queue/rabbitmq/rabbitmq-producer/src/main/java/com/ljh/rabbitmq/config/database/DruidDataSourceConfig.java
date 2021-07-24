package com.ljh.rabbitmq.config.database;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * DruidDataSourceConfig
 *
 * @author Arsenal
 * created on 2021/4/17 17:34
 */
@Slf4j
@Configuration
@EnableTransactionManagement
public class DruidDataSourceConfig {

    private final DruidDataSourceSettings druidDataSourceSettings;

    @Autowired
    public DruidDataSourceConfig(DruidDataSourceSettings druidDataSourceSettings) {
        this.druidDataSourceSettings = druidDataSourceSettings;
    }

    public static String DRIVER_CLASSNAME;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigure() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(druidDataSourceSettings.getDriverClassName());
        DRIVER_CLASSNAME = druidDataSourceSettings.getDriverClassName();
        ds.setUrl(druidDataSourceSettings.getUrl());
        ds.setUsername(druidDataSourceSettings.getUsername());
        ds.setPassword(druidDataSourceSettings.getPassword());
        ds.setInitialSize(druidDataSourceSettings.getInitialSize());
        ds.setMinIdle(druidDataSourceSettings.getMinIdle());
        ds.setMaxActive(druidDataSourceSettings.getMaxActive());
        ds.setMaxWait(druidDataSourceSettings.getMaxWait());
        ds.setTimeBetweenEvictionRunsMillis(druidDataSourceSettings.getTimeBetweenEvictionRunsMillis());
        ds.setMinEvictableIdleTimeMillis(druidDataSourceSettings.getMinEvictableIdleTimeMillis());
        ds.setValidationQuery(druidDataSourceSettings.getValidationQuery());
        ds.setTestWhileIdle(druidDataSourceSettings.isTestWhileIdle());
        ds.setTestOnBorrow(druidDataSourceSettings.isTestOnBorrow());
        ds.setTestOnReturn(druidDataSourceSettings.isTestOnBorrow());
        ds.setPoolPreparedStatements(druidDataSourceSettings.isPoolPreparedStatements());
        ds.setMaxPoolPreparedStatementPerConnectionSize(druidDataSourceSettings.getMaxPoolPreparedStatementPerConnectionSize());
        ds.setFilters(druidDataSourceSettings.getFilters());
        ds.setConnectProperties(druidDataSourceSettings.getConnectionProperties());
        ds.setUseGlobalDataSourceStat(druidDataSourceSettings.getUseGlobalDataSourceStat());
        log.info("druid datasource config : {}", ds);
        return ds;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource(dataSource());
        return txManager;
    }
}
