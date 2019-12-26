package com.imooc.controller;

import com.imooc.pojo.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Arsenal
 * created on 2019/2/19 22:23
 */
@Controller
@RequestMapping("ftl")
public class FreemarkerController {

    private final Resource resource;

    @Autowired
    public FreemarkerController(Resource resource) {
        this.resource = resource;
    }

    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("resource", resource);
        return "freemarker/index";
    }

    @RequestMapping("center")
    public String center() {
        return "freemarker/center/center";
    }
}
