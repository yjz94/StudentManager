package cn.lessann.studentmanager.bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * 教师类
 *
 * @author LessAnn
 * @version 1.0
 */
public class Teacher extends User implements Serializable {

    private static final long serialVersionUID = 6946937469972231006L;

    /** 职位 */
    private String post;

    public Teacher() {
    }

    public Teacher(String post) {
        this.post = post;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(post, teacher.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), post);
    }

    @Override
    public String toString() {
        return super.toString() + " post='" + post + '\'';
    }
}
