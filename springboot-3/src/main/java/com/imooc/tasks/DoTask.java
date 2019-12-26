package com.imooc.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

/**
 * @author Arsenal
 * created on 2019/2/22 1:14
 */
@RestController
@RequestMapping("tasks")
public class DoTask {

    private final AsyncTask asyncTask;

    @Autowired
    public DoTask(AsyncTask asyncTask) {
        this.asyncTask = asyncTask;
    }

    @RequestMapping("test1")
    public String test1() throws InterruptedException {
        long start = System.currentTimeMillis();

        Future<Boolean> a = asyncTask.doTask11();
        Future<Boolean> b = asyncTask.doTask22();
        Future<Boolean> c = asyncTask.doTask33();

        while (!a.isDone() || !b.isDone() || !c.isDone()) {
            if (a.isDone() && b.isDone() && c.isDone()) {
                break;
            }
        }

        long end = System.currentTimeMillis();

        String times = "任务全部完成，总耗时：" + (end - start) + "耗时";
        System.out.println(times);

        return times;
    }


}
