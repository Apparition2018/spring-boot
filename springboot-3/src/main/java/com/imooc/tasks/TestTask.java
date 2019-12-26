package com.imooc.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Arsenal
 * created on 2019/2/22 1:04
 */
@Component
public class TestTask {

    private static final SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm:ss");

    // 定义每过3秒执行任务
//    @Scheduled(fixedRate = 3000)
    // 在线Cron表达式生成器：http://cron.qqe2.com/
    // spring-boot 不支持年
    @Scheduled(cron = "4-40 * * * * ? ")
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dataFormat.format(new Date()));
    }

}
