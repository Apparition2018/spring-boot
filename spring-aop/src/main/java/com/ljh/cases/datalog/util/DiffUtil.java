package com.ljh.cases.datalog.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.ljh.cases.datalog.anno.DataLog;
import com.ljh.cases.datalog.domain.ChangeItem;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.util.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * DiffUtil
 *
 * @author Arsenal
 * created on 2020/1/3 0:23
 */
@Slf4j
public class DiffUtil {

    public static Object getObjectById(Object target, Object id) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = target.getClass().getDeclaredMethod("findById2", Long.class);
        return method.invoke(target, id);
    }

    /**
     * 获取新操作的 change item
     */
    public static List<ChangeItem> getInsertChangeItems(Object obj) {
        Map<String, String> valueMap = getBeanSimpleFieldValueMap(obj, true);
        Map<String, String> fieldCnNameMap = getFieldNameMap(obj.getClass());
        List<ChangeItem> items = new ArrayList<>();
        for (Map.Entry<String, String> entry : valueMap.entrySet()) {
            String fieldName = entry.getKey();
            String value = entry.getValue();
            ChangeItem changeItem = new ChangeItem();
            // set old value empty
            changeItem.setOldValue("");
            changeItem.setNewValue(value);
            changeItem.setField(fieldName);
            String cnName = fieldCnNameMap.get(fieldName);
            changeItem.setFieldShowName(StringUtils.hasLength(cnName) ? cnName : fieldName);
            items.add(changeItem);
        }
        return items;
    }

    /**
     * 获取更新操作的 change item
     */
    public static List<ChangeItem> getChangeItems(Object oldObj, Object newObj) {
        List<ChangeItem> changeItems = new ArrayList<>();
        Map<String, String> fieldCnNameMap = getFieldNameMap(oldObj.getClass());
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(oldObj.getClass(), Object.class);
            for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
                String fieldName = propertyDescriptor.getName();
                String oldProp = getValue(PropertyUtils.getProperty(oldObj, fieldName));
                String newProp = getValue(PropertyUtils.getProperty(newObj, fieldName));
                if (!oldProp.equals(newProp)) {
                    ChangeItem changeItem = new ChangeItem();
                    changeItem.setField(fieldName);
                    String cnName = fieldCnNameMap.get(fieldName);
                    changeItem.setFieldShowName(StringUtils.hasLength(cnName) ? cnName : fieldName);
                    changeItem.setNewValue(newProp);
                    changeItem.setOldValue(oldProp);
                    changeItems.add(changeItem);
                }
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            log.error("There is error when convert change item", e);
        }
        return changeItems;
    }

    /**
     * 获取删除操作的 change item
     */
    public static ChangeItem getDeleteChangeItem(Object obj) throws JsonProcessingException {
        ChangeItem changeItem = new ChangeItem();
        changeItem.setOldValue(JsonMapper.builder().build().writeValueAsString(obj));
        changeItem.setNewValue("");
        return changeItem;
    }

    /**
     * 获取 bean 的 fileName 和 value
     * 只获取简单类型，不获取复杂类型，包括集合
     */
    public static Map<String, String> getBeanSimpleFieldValueMap(Object bean, boolean filterNull) {
        Map<String, String> map = new HashMap<>(16);
        if (bean == null) {
            return map;
        }
        Class<?> clazz = bean.getClass();
        // 不获取父类的字段
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                Class<?> fieldType = field.getType();
                String name = field.getName();
                Method method = clazz.getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
                Object value = method.invoke(bean);
                if (filterNull && value == null) {
                    continue;
                }
                if (isBaseDataType(fieldType)) {
                    String strValue = getFieldStringValue(fieldType, value);
                    map.put(name, strValue);
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 判断一个类是否为基本数据类型或包装类，或日期
     *
     * @param clazz 要判断的类
     * @return true 表示为基本数据类型
     */
    public static boolean isBaseDataType(Class<?> clazz) {
        return (clazz.equals(String.class) ||
                clazz.equals(Integer.class) ||
                clazz.equals(Byte.class) ||
                clazz.equals(Long.class) ||
                clazz.equals(Double.class) ||
                clazz.equals(Float.class) ||
                clazz.equals(Character.class) ||
                clazz.equals(Short.class) ||
                clazz.equals(BigDecimal.class) ||
                clazz.equals(BigInteger.class) ||
                clazz.equals(Boolean.class) ||
                clazz.equals(Date.class) ||
                clazz.isPrimitive());
    }

    /**
     * 自定义不同类型的 String 值
     */
    public static String getFieldStringValue(Class<?> type, Object value) {
        if (type.equals(Date.class)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format((Date) value);
        }
        return value.toString();
    }

    /**
     * 从注解读取中文名
     */
    public static Map<String, String> getFieldNameMap(Class<?> clazz) {
        Map<String, String> map = new HashMap<>(16);
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(DataLog.class)) {
                DataLog dataLog = field.getAnnotation(DataLog.class);
                map.put(field.getName(), dataLog.name());
            }
        }
        return map;
    }

    /**
     * 不同类型转字符串的处理
     */
    public static String getValue(Object obj) {
        if (obj != null) {
            if (obj instanceof Date) {
                return formatDateW3C((Date) obj);
            } else {
                return obj.toString();
            }
        } else {
            return "";
        }
    }

    /**
     * 将 Date 类型转为字符串
     */
    public static String formatDateW3C(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String text = df.format(date);
        return text.substring(0, 22) + ":" + text.substring(22);
    }
}
