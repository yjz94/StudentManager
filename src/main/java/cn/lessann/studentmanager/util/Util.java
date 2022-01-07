package cn.lessann.studentmanager.util;

import cn.lessann.studentmanager.bean.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * @param t         类实体类
     * @param fieldName 类成员变量名
     * @param <T>       泛型
     * @return 属性值
     */
    public static <T> Object getFieldValue(Class clazz, T t, String fieldName) {
        try {
            // 获得所有字段
            Field[] fields = clazz.getDeclaredFields();
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

    public static <T> void setFieldValue(Class clazz, T t, String fieldName, String val) {
        try {
            // 获得所有字段
            Field[] fields = clazz.getDeclaredFields();
            // 判断是否有指定成员变量
            for (Field field : fields) {
                if (fieldName.equals(field.getName())) {
                    // 开启权限
                    field.setAccessible(true);
                    // 设置值
                    setFieldValueByType(field, t, val);
                    return;
                }
            }
            // 设置父类成员变量
            setFieldValue(clazz.getSuperclass(), t, fieldName, val);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> void setFieldValueByType(Field field, T t, String val) throws Exception {
        // 获得当前属性值类型
        Class type = field.getType();

        // 如果类型为String直接设置
        if (String.class.getName().equals(type.getName())) {
            field.set(t, val);
            return;
        }

        // 通过构造函数获得对应类型，构造函数传入值都为String自动类型转换
        Constructor constructor = type.getConstructor(String.class);
        field.set(t, constructor.newInstance(val));
    }

    public static Student getWriterStudent() {
        List<String> list = Arrays.asList("请输入学生信息" + System.lineSeparator() + "学名（name）",
                "性别（sex）", "年龄（age）", "年级（grade）", "排名（rank）");
        List<String> writers = getWriter(list);
        Student student = new Student();

        // 循环设置内容
        for (int i = 0; i < list.size(); i++) {
            String fieldName = match(list.get(i), "\\w+");
            setFieldValue(student.getClass(), student, fieldName, writers.get(i));
        }

        return student;
    }

    public static String match(String str, String pattern) {
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(str);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }


    /**
     * 根据提示获得输入内容
     *
     * @param list 提示列表
     * @return 输入内容
     */
    public static List<String> getWriter(List<String> list) {
        Scanner scanner = new Scanner(System.in);
        List<String> arrayList = new ArrayList<>();
        for (String hint : list) {
            System.out.println(hint + ":");
            arrayList.add(scanner.next());
        }
        return arrayList;
    }
}
