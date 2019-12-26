package com.imooc.controller;

import com.imooc.pojo.LeeJSONResult;
import com.imooc.pojo.SysUser;
import com.imooc.pojo.User;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Arsenal
 * created on 2019/2/22 0:03
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    private final StringRedisTemplate strRedis;
    private final RedisOperator redis;

    @Autowired
    public RedisController(StringRedisTemplate strRedis, RedisOperator redis) {
        this.strRedis = strRedis;
        this.redis = redis;
    }

    @RequestMapping("/test")
    public LeeJSONResult test() {

        strRedis.opsForValue().set("imooc-cache", "hello 慕课网~~~~~~");

        SysUser user = new SysUser();
        user.setId("100111");
        user.setUsername("imooc");
        user.setPassword("abc123");
        user.setIsDelete(0);
        user.setRegistTime(new Date());
        strRedis.opsForValue().set("json:user", JsonUtils.objectToJson(user));

        SysUser jsonUser = JsonUtils.jsonToPojo(strRedis.opsForValue().get("json:user"), SysUser.class);

        return LeeJSONResult.ok(jsonUser);
    }

    @RequestMapping("/getJsonList")
    public LeeJSONResult getJsonList() {


        User user = new User();
        user.setAge(18);
        user.setName("慕课网");
        user.setPassword("123456");
        user.setBirthday(new Date());

        User u1 = new User();
        u1.setAge(19);
        u1.setName("imooc");
        u1.setPassword("123456");
        u1.setBirthday(new Date());

        User u2 = new User();
        u2.setAge(17);
        u2.setName("hello imooc");
        u2.setPassword("123456");
        u2.setBirthday(new Date());

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(u1);
        userList.add(u2);

        redis.set("json:info:userlist", JsonUtils.objectToJson(userList), 2000);

        String userListJson = redis.get("json:info:userlist");
        List<User> userListBorn = JsonUtils.jsonToList(userListJson, User.class);

        return LeeJSONResult.ok(userListBorn);
    }
}
