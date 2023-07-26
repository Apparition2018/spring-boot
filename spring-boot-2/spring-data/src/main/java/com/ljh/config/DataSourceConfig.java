package com.ljh.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * DataSourceConfig
 *
 * @author ljh
 * created on 2021/9/3 14:42
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws SystemException {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        // 最大值 300 秒
        userTransactionImp.setTransactionTimeout(30);
        AtomikosJtaPlatform.userTransaction = userTransactionImp;
        return userTransactionImp;
    }

    @Bean(name = "transactionManager")
    public TransactionManager transactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        AtomikosJtaPlatform.transactionManager = userTransactionManager;
        return userTransactionManager;
    }

    @Bean(name = "platformTransactionManager")
    @DependsOn({"userTransaction", "transactionManager"})
    public PlatformTransactionManager platformTransactionManager() throws SystemException {
        UserTransaction userTransaction = userTransaction();
        TransactionManager transactionManager = transactionManager();
        return new JtaTransactionManager(userTransaction, transactionManager);
    }
}
