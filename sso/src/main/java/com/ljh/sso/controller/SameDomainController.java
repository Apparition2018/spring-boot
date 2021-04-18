package com.ljh.sso.controller;

import com.ljh.sso.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 同域 SSO
 *
 * @author Arsenal
 * created on 2021/4/18 19:31
 */
@Slf4j
@Controller
@RequestMapping("same")
public class SameDomainController {

    @RequestMapping("login")
    public String login() {
        log.info("login");
        return "thymeleaf/login-s";
    }

    @RequestMapping("doLogin")
    public String doLogin(String username, String password, String gotoUrl, HttpServletResponse response) {
        boolean ok = User.checkLogin(username, password);
        if (ok) {
            Cookie cookie = new Cookie("sso", "sso");
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:" + gotoUrl;
        }
        return null;
    }

    @RequestMapping("success1")
    public String success1(HttpServletRequest request) {
        if (User.checkCookie(request)) {
            return "thymeleaf/success1";
        } else {
            return "thymeleaf/login-s";
        }
    }

    @RequestMapping("success2")
    public String success2(HttpServletRequest request) {
        if (User.checkCookie(request)) {
            return "thymeleaf/success2";
        } else {
            return "thymeleaf/login-s";
        }
    }
}
