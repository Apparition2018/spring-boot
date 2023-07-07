package com.ljh;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.swing.filechooser.FileSystemView;
import java.io.FileWriter;
import java.io.IOException;

/**
 * ThymeleafTests
 *
 * @author ljh
 * created on 2022/4/7 1:01
 */
public class ThymeleafTests {

    private TemplateEngine engine;
    private Context context;

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

    /**
     * 模板文件静态化
     */
    @Test
    public void testTemplateFile2() throws IOException {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");
        engine.setTemplateResolver(resolver);
        context.setVariable("name", "张三");
        String desktopPath = FileSystemView.getFileSystemView().getHomeDirectory().getPath();
        try (FileWriter writer = new FileWriter(desktopPath + "/hello_copy.html")) {
            engine.process("hello", context, writer);
        }
    }
}
