package cn.lessann.studentmanager;

import cn.lessann.studentmanager.bean.Student;
import cn.lessann.studentmanager.function.StudentFileDb;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
        Application application = new Application();

        StudentFileDb studentFileDb = new StudentFileDb(new Student());

        while (true) {
            int i = application.showFunction();
            switch (i) {
                case 1:
                    studentFileDb.addStudent();
                    break;
                case 2:
                    studentFileDb.deleteStudent();
                    break;
                case 3:
                    studentFileDb.updateStudent();
                    break;
                case 4:
                    studentFileDb.showAllStudent();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("选择错误，程序即将停止");
                    return;
            }
        }
    }

    public int showFunction() {
        System.out.println("请选择操作内容：");
        List<String> list = Arrays.asList("1.新增学生", "2.删除学生", "3.修改学生", "4.显示学生", "5.退出");
        for (String s : list) {
            System.out.println(s);
        }
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

}
