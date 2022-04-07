package com.ljh;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * ThymeleafTests
 *
 * @author ljh
 * created on 2022/4/7 1:01
 */
public class ThymeleafTests {

    TemplateEngine engine;
    Context context;

    @BeforeEach
    public void init() {
        engine = new TemplateEngine();
        context = new Context();
    }

    /**
     * 模板字符串
     */
    @Test
    public void testTemplateString() {
        String template = "<template type='text' th:value='hello_thymeleaf'/>";
        String html = engine.process(template, context);
        System.out.println("结果：" + html);
    }

    /**
     * 变量
     */
    @Test
    public void testVariable() {
        String template = "<template type='text' th:value='${name}'/>";
        context.setVariable("name", "张三");
        String html = engine.process(template, context);
        System.out.println("结果：" + html);
    }

    /**
     * 模板文件
     */
    @Test
    public void testTemplateFile() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");
        engine.setTemplateResolver(resolver);
        context.setVariable("name", "张三");
        String html = engine.process("hello", context);
        System.out.println("结果：" + html);
    }
}
