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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 跨域 SSO
 * <p>
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
    private static final List<String> ADD_COOKIE_URL_LIST = new ArrayList<>();

    static {
        ADD_COOKIE_URL_LIST.add("http://demo.x.com/cross/addCookie1");
        ADD_COOKIE_URL_LIST.add("http://demo.y.com/cross/addCookie2");
    }

    /** <a href="http://demo.x.com/cross/login1">login1</a> */
    @RequestMapping("login1")
    public String login1(ModelMap modelMap, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("ssoCookie")) {
                    String url = "http://demo.z.com/cross/checkCookie";
                    Map<String, String> map = new HashMap<>();
                    map.put("cookieName", cookie.getName());
                    map.put("cookieValue", cookie.getValue());
                    if (doGet(url, map)) {
                        return "thymeleaf/success1";
                    }
                }
            }
        }
        modelMap.addAttribute("doLogin", "/cross/doLogin1");
        modelMap.addAttribute("gotoUrl", "success1");
        return "login-c";
    }

    /** <a href="http://demo.y.com/cross/login2">login2</a> */
    @RequestMapping("login2")
    public String login2(ModelMap modelMap, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("ssoCookie")) {
                    String url = "http://demo.z.com/cross/checkCookie";
                    Map<String, String> map = new HashMap<>();
                    map.put("cookieName", cookie.getName());
                    map.put("cookieValue", cookie.getValue());
                    if (doGet(url, map)) {
                        return "success2";
                    }
                }
            }
        }
        modelMap.addAttribute("doLogin", "/cross/doLogin2");
        modelMap.addAttribute("gotoUrl", "success2");
        return "login-c";
    }

    /** <a href="http://demo.x.com/cross/doLogin1">doLogin1</a> */
    @RequestMapping("doLogin1")
    public String doLogin1(String username, String password, String gotoUrl, ModelMap modelMap) {
        String url = "http://demo.z.com/cross/doLogin";
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        if (doGet(url, map)) {
            modelMap.addAttribute("addCookieUrlList", ADD_COOKIE_URL_LIST);
            return gotoUrl;
        }
        return "login-c";
    }

    /** <a href="http://demo.y.com/cross/doLogin2">doLogin2</a> */
    @RequestMapping("doLogin2")
    public String doLogin2(String username, String password, String gotoUrl, ModelMap modelMap) {
        String url = "http://demo.z.com/cross/doLogin";
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        if (doGet(url, map)) {
            modelMap.addAttribute("addCookieUrlList", ADD_COOKIE_URL_LIST);
            return gotoUrl;
        }
        return "login-c";
    }

    /** <a href="http://demo.x.com/cross/addCookie1">addCookie1</a> */
    @RequestMapping("addCookie1")
    public void addCookie1(HttpServletResponse response) {
        Cookie cookie = new Cookie("ssoCookie", "sso");
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /** <a href="http://demo.y.com/cross/addCookie2">addCookie2</a> */
    @RequestMapping("addCookie2")
    public void addCookie2(HttpServletResponse response) {
        Cookie cookie = new Cookie("ssoCookie", "sso");
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /** <a href="http://demo.z.com/cross/doLogin">doLogin</a> */
    @RequestMapping("doLogin")
    @ResponseBody
    public boolean doLogin(String username, String password) {
        return User.checkLogin(username, password);
    }

    /** <a href="http://demo.z.com/cross/checkCookie">checkCookie</a> */
    @RequestMapping("checkCookie")
    @ResponseBody
    public boolean checkCookie(String cookieName, String cookieValue) {
        return User.checkCookie(cookieName, cookieValue);
    }

    public boolean doGet(String url, Map<String, String> map) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder tmpUrl = new StringBuilder(url).append("?");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            tmpUrl.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        url = tmpUrl.substring(0, tmpUrl.length() - 1);
        Boolean result = restTemplate.getForObject(url, Boolean.class);
        return result != null && result;
    }
}
