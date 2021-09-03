package com.ljh.config;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * AtomikosJtaPlatform
 *
 * @author ljh
 * created on 2021/9/3 14:39
 */
public class AtomikosJtaPlatform extends AbstractJtaPlatform {
    private static final long serialVersionUID = -3518905713510018766L;
    public static TransactionManager transactionManager;
    public static UserTransaction userTransaction;

    @Override
    protected TransactionManager locateTransactionManager() {
        return transactionManager;
    }

    @Override
    protected UserTransaction locateUserTransaction() {
        return userTransaction;
    }
}
