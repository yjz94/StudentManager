package cn.lessann.studentmanager;

import cn.lessann.studentmanager.bean.Student;
import cn.lessann.studentmanager.function.FileDb;
import cn.lessann.studentmanager.util.Util;

/**
 * 程序启动类
 * <p>
 * jdk最低版本1.8
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/6 7:59 下午
 */
public class Application {

    public static void main(String[] args) {
        Student student = new Student();
        student.setName("张三");
        student.setSex("男");
        student.setAge(18);
        student.setGrade("一年级二班");
        student.setRank(1);

        FileDb<Student> fileDb = new FileDb<>(new Student());

        fileDb.add(student);

    }

}
