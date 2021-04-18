package com.ljh.rabbitmq.util;

import com.google.gson.Gson;

/**
 * FastJsonConvertUtil
 *
 * @author Administrator
 * created on 2021/4/17 23:05
 */
public class GsonConverter {
    
    public static final  Gson GSON = new Gson();
    
    public static Object json2Obj(String message, Class<?> clazz) {
        return GSON.fromJson(message, clazz);
    }
    
    public static String obj2Json(Object obj) {
        return GSON.toJson(obj);
    }
}
