package com.ljh.sso.entity;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * User
 *
 * @author Arsenal
 * created on 2021/4/19 1:31
 */
@Getter
@Setter
public class User {

    private String username;
    private String password;

    public static boolean checkLogin(String username, String password) {
        return username.equals("user") && password.equals("123");
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
