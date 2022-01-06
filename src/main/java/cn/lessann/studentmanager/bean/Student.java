package cn.lessann.studentmanager.bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * 学生类
 *
 * @author LessAnn
 * @version 1.0
 */
public class Student extends User implements Serializable {

    private static final long serialVersionUID = 329191593452109892L;

    /** 年级 */
    private String grade;
    /** 排名 */
    private Integer rank;

    public Student() {
    }

    public Student(String grade, Integer rank) {
        this.grade = grade;
        this.rank = rank;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(grade, student.grade) && Objects.equals(rank, student.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), grade, rank);
    }

    @Override
    public String toString() {
        return "Student{" +
                "grade='" + grade + '\'' +
                ", rank=" + rank +
                "} " + super.toString();
    }
}
