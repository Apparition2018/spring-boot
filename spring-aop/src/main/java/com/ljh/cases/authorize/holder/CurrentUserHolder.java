package com.ljh.cases.authorize.holder;

/**
 * CurrentUserHolder
 *
 * @author Arsenal
 * created on 2020/1/2 11:41
 */
public class CurrentUserHolder {
    
    private static final ThreadLocal<String> HOLDER = new ThreadLocal<>();
    
    public static String get() {
        return HOLDER.get() == null ? "unknown" : HOLDER.get();
    }
    
    public static void set(String user) {
        HOLDER.set(user);
    }
}
