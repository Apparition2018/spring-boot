package com.ljh.sso.controller;

import com.ljh.sso.entity.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

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

    /** <a href="http://demo1.x.com/same-parent/login1">login1</a> */
    @RequestMapping("login1")
    public String login1(ModelMap modelMap, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("ssoCookie")) {
                    String url = "http://check.x.com/same-parent/checkCookie";
                    if (doGet(url, cookie.getName(), cookie.getValue())) {
                        return "success1";
                    }
                }
            }
        }
        modelMap.addAttribute("gotoUrl", "http://demo1.x.com/same-parent/login1");
        return "login-sp";
    }

    /** <a href="http://demo2.x.com/same-parent/login2">login2</a> */
    @RequestMapping("login2")
    public String login2(ModelMap modelMap, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("ssoCookie")) {
                    String url = "http://check.x.com/same-parent/checkCookie";
                    if (doGet(url, cookie.getName(), cookie.getValue())) {
                        return "success2";
                    }
                }
            }
        }
        modelMap.addAttribute("gotoUrl", "http://demo1.x.com/same-parent/login2");
        return "login-sp";
    }

    /** <a href="http://check.x.com/same-parent/doLogin">doLogin</a> */
    @RequestMapping("doLogin")
    public String doLogin(String username, String password, String gotoUrl, HttpServletResponse response) {
        boolean ok = User.checkLogin(username, password);
        if (ok) {
            Cookie cookie = new Cookie("ssoCookie", "sso");
            // 关键代码，设置到同父域
            cookie.setDomain(".x.com");
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:" + gotoUrl;
        }
        return null;
    }

    /** <a href="http://check.x.com/same-parent/checkCookie">checkCookie</a> */
    @RequestMapping("checkCookie")
    @ResponseBody
    public boolean checkCookie(String cookieName, String cookieValue) {
        return User.checkCookie(cookieName, cookieValue);
    }

    public boolean doGet(String url, String cookieName, String cookieValue) {
        RestTemplate restTemplate = new RestTemplate();
        url += "?cookieName=" + cookieName + "&cookieValue=" + cookieValue;
        Boolean result = restTemplate.getForObject(url, Boolean.class);
        return result != null && result;
    }
}
