package cn.lessann.studentmanager.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 工具类
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/6 8:13 下午
 */
public class Util {

    public static void execObjectFunction(String functionItem) {
        try {
            String[] split = functionItem.split(":");
            String className = split[0];
            String functionName = split[1];

            // 反射获得类
            Class clazz = Class.forName(className);
            Object o = clazz.newInstance();
            Method method = clazz.getMethod(functionName);
            method.setAccessible(true);
            method.invoke(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得类属性值，无视访问权限<br/>
     * 此方法为递归方法，使用需谨慎
     *
     * @param clazz     类对象
     * @param t         类实体类
     * @param fieldName 类成员变量名
     * @param <T>       泛型
     * @return 属性值
     */
    public static <T> Object getFieldValue(Class clazz, T t, String fieldName) {
        try {
            // 获得指定字段
            Field[] fields = clazz.getDeclaredFields();
            boolean flag = false;
            // 判断是否有指定成员变量
            for (Field field : fields) {
                if (fieldName.equals(field.getName())) {
                    // 开启权限
                    field.setAccessible(true);
                    return field.get(t);
                }
            }

            // 获得父类成员变量
            return getFieldValue(clazz.getSuperclass(), t, fieldName);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
