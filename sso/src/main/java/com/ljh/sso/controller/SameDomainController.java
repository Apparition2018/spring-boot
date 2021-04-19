package com.ljh.sso.controller;

import com.ljh.sso.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

    /**
     * http://localhost/same/login1
     */
    @RequestMapping("login1")
    public String login1(ModelMap map, HttpServletRequest request) {
        map.addAttribute("gotoUrl", "login1");
        if (User.checkCookie(request)) {
            return "thymeleaf/success1";
        } else {
            return "thymeleaf/login-s";
        }
    }

    /**
     * http://localhost/same/login2
     */
    @RequestMapping("login2")
    public String login2(ModelMap map, HttpServletRequest request) {
        map.addAttribute("gotoUrl", "login2");
        if (User.checkCookie(request)) {
            return "thymeleaf/success2";
        } else {
            return "thymeleaf/login-s";
        }
    }

    /**
     * http://localhost/same/doLogin
     */
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
}
