package com.ljh.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

/**
 * MyLocalResolver
 *
 * @author ljh
 * created on 2022/4/7 22:44
 */
public class MyLocalResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String lang = request.getParameter("lang");
        Locale locale = request.getLocale();
        if (StringUtils.isNotEmpty(lang)) {
            String[] data = lang.split("_");
            locale = new Locale(data[0], data[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
