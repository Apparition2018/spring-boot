package com.ljh.sso.controller;

import com.ljh.sso.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

    /**
     * http://demo1.x.com/same-parent/login1
     */
    @RequestMapping("login1")
    public String login1(ModelMap modelMap, HttpServletRequest request) {
        modelMap.addAttribute("gotoUrl", "http://demo1.x.com/same-parent/login1");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("ssoCookie")) {
                    String url = "http://check.x.com/same-parent/checkCookie";
                    if (doGet(url, cookie.getName(), cookie.getValue())) {
                        return "thymeleaf/success1";
                    }
                }
            }
        }
        return "thymeleaf/login-sp";
    }

    /**
     * http://demo2.x.com/same-parent/login2
     */
    @RequestMapping("login2")
    public String login2(ModelMap modelMap, HttpServletRequest request) {
        modelMap.addAttribute("gotoUrl", "http://demo1.x.com/same-parent/login2");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("ssoCookie")) {
                    String url = "http://check.x.com/same-parent/checkCookie";
                    if (doGet(url, cookie.getName(), cookie.getValue())) {
                        return "thymeleaf/success2";
                    }
                }
            }
        }
        return "thymeleaf/login-sp";
    }

    /**
     * http://check.x.com/same-parent/doLogin
     */
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

    /**
     * http://check.x.com/same-parent/checkCookie
     */
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
