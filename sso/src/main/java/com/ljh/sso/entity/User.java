package com.ljh.sso.entity;

import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * User
 *
 * @author Arsenal
 * created on 2021/4/19 1:31
 */
@Getter
@Setter
public class User {

    public static final String USERNAME = "user";
    public static final String PASSWORD = "123";

    private String username;
    private String password;

    public static boolean checkLogin(String username, String password) {
        return username.equals(USERNAME) && password.equals(PASSWORD);
    }

    // 一般放在拦截器
    public static boolean checkCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("ssoCookie") && cookie.getValue().equals("sso")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkCookie(String cookieName, String cookieValue) {
        return cookieName.equals("ssoCookie") && cookieValue.equals("sso");
    }
}
