package com.ljh.controller;

import com.ljh.pojo.LeeJSONResult;
import com.ljh.pojo.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Arsenal
 * created on 2019/2/18 23:52
 */
@RestController
public class HelloController {

    private final Resource resource;

    @Autowired
    public HelloController(Resource resource) {
        this.resource = resource;
    }

    @RequestMapping("/hello")
    public Object hello() {
        return "hello springboot~~";
    }

    @RequestMapping("/getResource")
    public LeeJSONResult getResource() {

        Resource bean = new Resource();
        BeanUtils.copyProperties(resource, bean);

        return LeeJSONResult.ok(bean);
    }
}