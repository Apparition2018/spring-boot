package com.ljh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TemplateController
 *
 * @author ljh
 * created on 2022/4/10 15:19
 */
@Controller
@RequestMapping("template")
public class TemplateController {

    @RequestMapping("index")
    public String index(Model model) {
        model.addAttribute("templateName", "menu");
        return "template/index";
    }

    @RequestMapping("index2")
    public String index2(Model model) {
        return "template/index2";
    }

    /**
     * th:remove
     */
    @RequestMapping("remove")
    public String remove(Model model) {
        return "template/remove";
    }

    /**
     * 动态布局
     */
    @RequestMapping("flexible-layouts")
    public String flexibleLayouts(Model model) {
        return "template/flexible-layouts";
    }
}
