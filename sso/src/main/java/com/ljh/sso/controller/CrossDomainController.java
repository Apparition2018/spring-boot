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
import java.util.HashMap;
import java.util.Map;

/**
 * 跨域 SSO
 * 
 * 本地修改 host
 * 127.0.0.1	demo.x.com
 * 127.0.0.1	demo.y.com
 * 127.0.0.1	demo.z.com
 *
 * @author Arsenal
 * created on 2021/4/19 2:57
 */
@Slf4j
@Controller
@RequestMapping("cross")
public class CrossDomainController {

    @RequestMapping("login")
    public String login() {
        log.info("login");
        return "thymeleaf/login-c";
    }
    
    /**
     * http://demo.x.com/cross/doLogin
     * http://demo.y.com/cross/doLogin
     */
    @RequestMapping("doLogin")
    public String doLogin(String username, String password, String gotoUrl) {
        String url = "http://demo.z.com/cross/doLogin";
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        if (doGet(url, map)) {
            return "redirect:" + gotoUrl;
        }
        return "thymeleaf/login-c";
    }

    /**
     * http://demo.z.com/cross/doLogin
     */
    @RequestMapping("doLogin")
    public boolean doLogin(Map<String, String> map) {
        return User.checkLogin(map.get("username"), map.get("password"));
    }

    /**
     * http://demo.z.com/cross/doLogin
     */
    @RequestMapping("checkCookie")
    @ResponseBody
    public boolean checkCookie(String cookieName, String cookieValue) {
        log.info("{}, {}", cookieName, cookieValue);
        return User.checkCookie(cookieName, cookieValue);
    }

    /**
     * http://demo.x.com/cross/success1
     */
    @RequestMapping("success1")
    public String success1(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("sso")) {
                String url = "http://demo.x.com/cross/checkCookie";
                if (doGet(cookie.getName(), cookie.getValue())) {
                    return "thymeleaf/success1";
                }
            }
        }
        return "thymeleaf/login-sp";
    }

    /**
     * http://demo.y.com/cross/success2
     */
    @RequestMapping("success2")
    public String success2(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("sso")) {
                String url = "http://demo.y.com/cross/checkCookie";
                if (doGet(cookie.getName(), cookie.getValue())) {
                    return "thymeleaf/success2";
                }
            }
        }
        return "thymeleaf/login-sp";
    }

    public boolean doGet(String url, Map<String, String> map) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder tmpUrl = new StringBuilder(url).append("?");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            tmpUrl.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        url = tmpUrl.substring(0, url.length() - 1);
        Boolean result = restTemplate.getForObject(url, Boolean.class);
        return result != null && result;
    }
}
