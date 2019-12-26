package com.imooc.controller;

import com.imooc.pojo.LeeJSONResult;
import com.imooc.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Arsenal
 * created on 2019/2/18 23:57
 */
//@controller
@RestController     // @RestController = @controller + @ResponseBody
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getUser")
//    @ResponseBody
    public User getUser() {

        User u = new User();
        u.setName("imooc2");
        u.setAge(18);
        u.setBirthday(new Date());
        u.setPassword("imooc2");
        u.setDesc("hello imooc2~~");

        return u;
    }

    @RequestMapping("/getUserJson")
//    @ResponseBody
    public LeeJSONResult getUserJson() {

        User u = new User();
        u.setName("imooc");
        u.setAge(18);
        u.setBirthday(new Date());
        u.setPassword("imooc");
        u.setDesc("hello imooc~~");

        return LeeJSONResult.ok(u);
    }
}
