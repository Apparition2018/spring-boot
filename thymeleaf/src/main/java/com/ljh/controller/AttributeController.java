package com.ljh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * AttributeController
 *
 * @author ljh
 * created on 2022/4/9 1:25
 */
@Controller
@RequestMapping("attribute")
public class AttributeController {

    /**
     * th:attr  设置任意属性的值
     * th:*     为特定属性设置值
     * th:*-*   一次设置多个属性值
     * 布尔属性
     */
    @RequestMapping("attr")
    public String attr(Model model) {
        model.addAttribute("action", "/user/login");
        model.addAttribute("text", "请登录");
        model.addAttribute("selected", true);
        model.addAttribute("unselected", false);
        return "attribute/attr";
    }

}
