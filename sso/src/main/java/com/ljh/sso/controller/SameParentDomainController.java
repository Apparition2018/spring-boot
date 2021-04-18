package com.ljh.sso.controller;

import com.ljh.sso.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 同父域 SSO
 * <p>
 * 本地修改 host
 * 127.0.0.1	demo1.x.com
 * 127.0.0.1	demo2.x.com
 * 127.0.0.1	check.x.com
 *
 * @author Arsenal
 * created on 2021/4/19 2:04
 */
@Slf4j
@Controller
@RequestMapping("same-parent")
public class SameParentDomainController {

    @RequestMapping("login")
    public String login() {
        log.info("login");
        return "thymeleaf/login-sp";
    }

    /**
     * http://check.x.com/same-parent/doLogin
     */
    @RequestMapping("doLogin")
    public String doLogin(String username, String password, String gotoUrl, HttpServletResponse response) {
        boolean ok = User.checkLogin(username, password);
        if (ok) {
            Cookie cookie = new Cookie("sso", "sso");
            // 关键代码
            cookie.setDomain(".x.com");
            cookie.setPath("/");
            response.addCookie(cookie);
            log.info("redirect:" + gotoUrl);
            return "redirect:" + gotoUrl;
        }
        return null;
    }

    /**
     * http://demo1.x.com/same-parent/doLogin
     * http://demo2.x.com/same-parent/doLogin
     * http://check.x.com/same-parent/doLogin
     */
    @RequestMapping("checkCookie")
    @ResponseBody
    public boolean checkCookie(String cookieName, String cookieValue) {
        log.info("{}, {}", cookieName, cookieValue);
        return User.checkCookie(cookieName, cookieValue);
    }

    /**
     * http://demo1.x.com/same-parent/success1
     */
    @RequestMapping("success1")
    public String success1(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("sso")) {
                String url = "http://check.x.com/same-parent/checkCookie";
                if (doGet(url, cookie.getName(), cookie.getValue())) {
                    return "thymeleaf/success1";
                }
            }
        }
        return "thymeleaf/login-sp";
    }

    /**
     * http://demo2.x.com/same-parent/success2
     */
    @RequestMapping("success2")
    public String success2(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("sso")) {
                String url = "http://check.x.com/same-parent/checkCookie";
                if (doGet(url, cookie.getName(), cookie.getValue())) {
                    return "thymeleaf/success2";
                }
            }
        }
        return "thymeleaf/login-sp";
    }

    public boolean doGet(String url, String cookieName, String cookieValue) {
        RestTemplate restTemplate = new RestTemplate();
        url += "?cookieName=" + cookieName + "&cookieValue=" + cookieValue;
        Boolean result = restTemplate.getForObject(url, Boolean.class);
        return result != null && result;
    }
}
