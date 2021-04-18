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

    @RequestMapping("checkCookie")
    @ResponseBody
    public boolean checkCookie(String cookieName, String cookieValue) {
        log.info("{}, {}", cookieName, cookieValue);
        return User.checkCookie(cookieName, cookieValue);
    }

    @RequestMapping("success1")
    public String success1(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("sso")) {
                String url = "http://demo1.x.com/same-parent/checkCookie";
                if (doGet(cookie.getName(), cookie.getValue())) {
                    return "thymeleaf/success1";
                }
            }
        }
        return "thymeleaf/login-sp";
    }

    @RequestMapping("success2")
    public String success2(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("sso")) {
                String url = "http://demo2.x.com/same-parent/checkCookie";
                if (doGet(cookie.getName(), cookie.getValue())) {
                    return "thymeleaf/success2";
                }
            }
        }
        return "thymeleaf/login-sp";
    }

    public boolean doGet(String url, Map<String, String> map) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuffer tmpUrl = new StringBuffer(url).append("?");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            tmpUrl.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        url = tmpUrl.toString().substring(0, url.length() - 1);
        Boolean result = restTemplate.getForObject(url, Boolean.class);
        return result != null && result;
    }
}
