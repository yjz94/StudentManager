package cn.lessann.studentmanager.function;

import cn.lessann.studentmanager.bean.Student;
import cn.lessann.studentmanager.util.Util;

import java.util.List;
import java.util.Scanner;

/**
 * 学生文件储存操作类
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/7 1:28 下午
 */
public class StudentFileDb extends FileDb<Student> {

    public StudentFileDb(Student student) {
        super(student);
    }

    public void addStudent() {
        Student student = Util.getWriterStudent();
        Student add = add(student);
        if (add == null) {
            System.out.println("新增失败！");
            return;
        }
        System.out.println("新增成功新增内容：" + add);
    }

    public void deleteStudent() {
        Student student = showStudentSelect();
        if (student == null) {
            System.out.println("选择为空，无法删除！");
            return;
        }
        Student delete = delete(student);
        if (delete == null) {
            System.out.println("删除出错！");
            return;
        }

        System.out.println("删除完成：" + delete);
    }

    public void updateStudent() {
        Student student = showStudentSelect();

        Student writerStudent = Util.getWriterStudent();

        Student update = update(student, writerStudent);

        System.out.println("修改完成：" + update);
    }

    public void showAllStudent() {
        List<Student> list = getAll();
        for (Student student : list) {
            System.out.println(student);
        }
    }

    /**
     * 显示所有已存内容，通过编号选择
     *
     * @return 返回已选择内容
     */
    private Student showStudentSelect() {
        List<Student> studentList = getAll();
        if (studentList == null || studentList.size() <= 0) {
            System.out.println("已存内容为空，无需选择。");
            return null;
        }

        System.out.println("请按照最前排编号选择：");
        for (int i = 0; i < studentList.size(); i++) {
            System.out.println(i + " => " + studentList.get(i));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("请选择：");
        int index = scanner.nextInt();

        return studentList.get(index);
    }

}
