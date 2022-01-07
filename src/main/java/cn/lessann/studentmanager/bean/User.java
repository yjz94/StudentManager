package cn.lessann.studentmanager.bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * 系统同用户类
 *
 * @author LessAnn
 * @version 1.0
 */
public class User implements Serializable {

    private static final long serialVersionUID = 3394776324366625068L;

    /** 用户名 */
    private String name;
    /** 性别 */
    private String sex;
    /** 年龄 */
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(sex, user.sex) && Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sex, age);
    }

    @Override
    public String toString() {
        return " name='" + name + '\'' + ", sex='" + sex + '\'' + ", age='" + age + '\'';
    }

}
