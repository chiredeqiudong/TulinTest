package com.manage;

import com.password.CheckedStudentList;
import com.people.Course;
import com.people.Student;


import java.util.Scanner;

/**
 * @author zy293
 * 运行菜单
 */
public class Frame  {
    private  static  final Scanner SCANNER = new Scanner(System.in);

    /**
     *修改信息框架
     */
    public static void polishFrame(Student student){
        while (true) {
            System.out.println("\n\t\t*******修改信息*********");
            System.out.println("\t\t***--> [1]:修改姓名  ***");
            System.out.println("\t\t***--> [2]:修改学号  ***");
            System.out.println("\t\t***--> [3]:修改邮箱  ***");
            System.out.println("\t\t***--> [4]:返回     ***");
            System.out.println("\t\t*********************");
            System.out.println("请输入操作命令(command):");
            String command = SCANNER.next();
            switch (command) {
                case "1" -> {
                    System.out.println("请输入学生姓名:");
                    String name = SCANNER.next();
                    student.setName(name);
                }
                case "2" -> {
                    //性别和班级一起修改
                    AddStudentInfo.getIdCard(student);
                    AddStudentInfo.getGradeGender(student, student.getIdCard());
                }
                case "3" -> AddStudentInfo.getEmail(student);
                case "4" -> {
                    return;
                }
                default -> System.out.println("\033[31;3m输入有误！请重新输入！\033[0m");
            }
        }

    }

    /**
     * 修改课程框架
     */
    public static void polishFrame (Course course){
        while (true) {
            System.out.println("*******修改信息***********");
            System.out.println("***--> [1]:修改课程名  ***");
            System.out.println("***--> [2]:修改课程号  ***");
            System.out.println("***--> [3]:修改学分    ***");
            System.out.println("***--> [4]:修改学时    ***");
            System.out.println("***--> [5]:返回       ***");
            System.out.println("*************************");
            System.out.println("\n请输入操作命令(command):");
            String command = SCANNER.next();
            switch (command) {
                case "1" -> {
                    System.out.println("请输入课程名:");
                    course.setName(SCANNER.next());
                }
                case "2" -> AddCourseInfo.getId(course);
                case "3" -> AddCourseInfo.getCredits(course);
                case "4" -> AddCourseInfo.getHours(course);
                case "5" -> {
                    return;
                }
                default -> System.out.println("\033[31;3m输入有误！请重新输入！\033[0m");
            }
        }

    }

    /**
     * 课程信息框架
     */
    public static void courseManage() {

        while (true) {
            System.out.println("\n\t\t*****************课程信息管理*******************");
            System.out.println("\t\t**                [1]:添加课程信息\t\t\t**");
            System.out.println("\t\t**                [2]:修改课程信息\t\t\t**");
            System.out.println("\t\t**                [3]:查询课程信息\t\t\t**");
            System.out.println("\t\t**                [4]:删除课程信息\t\t\t**");
            System.out.println("\t\t**                [5]:返回上一菜单\t\t\t**");
            System.out.println("\t\t**********************************************");
            System.out.println("\n请输入操作命令(command):");
            String command = SCANNER.next();
            switch (command) {
                case "1" -> AddCourseInfo.addCourse();
                case "2" -> AddCourseInfo.polishCourseInfo();
                case "3" -> searchCourseFrame();
                case "4" -> AddCourseInfo.deleteCourse();
                case "5" -> {
                    return;
                }
                default -> System.out.println("\033[31;3m输入有误！请重新输入！\033[0m");
            }
        }
    }

    /**
     * 查询学生成绩信息框架
     */
    public static void searchFrame(){
        while (true) {
            System.out.println("\n\t\t*****************查询学生成绩**************");
            System.out.println("\t\t**               [1]:条件查询\t\t   **");
            System.out.println("\t\t**               [2]:模糊查询\t\t   **");
            System.out.println("\t\t**               [3]:返回上一菜单\t\t   **");
            System.out.println("\t\t*****************************************");
            System.out.println("请输入操作指令:");
            String command = SCANNER.next();
            switch (command) {
                case "1" -> StudentScoreManage.searchF();
                case "2" -> StudentScoreManage.searchM();
                case "3" -> {
                    return;
                }
                default -> System.out.println("\033[31;3m输入有误！请重新输入！\033[0m");
            }
        }

    }
    /**
     * 查询课程信息
     */
    public static void searchCourseFrame(){
        while (true) {
            System.out.println("\n\t\t*****************查询课程信息**************");
            System.out.println("\t\t**               [1]:条件查询\t\t   **");
            System.out.println("\t\t**               [2]:模糊查询\t\t   **");
            System.out.println("\t\t**               [3]:返回上一菜单\t\t   **");
            System.out.println("\t\t*****************************************");
            System.out.println("请输入操作指令:");
            String command = SCANNER.next();
            switch (command) {
                case "1" -> AddCourseInfo.searchIf();
                case "2" -> AddCourseInfo.searchCourse();
                case "3" -> {
                    return;
                }
                default -> System.out.println("\033[31;3m输入有误！请重新输入！\033[0m");
            }
        }

    }

    /**
     * 查询学上信息
     */
    public static void searchStudentFrame(){
        while (true) {
            System.out.println("\n\t\t*****************查询学生信息**************");
            System.out.println("\t\t**               [1]:条件查询\t\t   **");
            System.out.println("\t\t**               [2]:模糊查询\t\t   **");
            System.out.println("\t\t**               [3]:返回上一菜单\t\t   **");
            System.out.println("\t\t*****************************************");
            String command = SCANNER.next();
            switch (command) {
                case "1" -> CheckedStudentList.selectIf();
                case "2" -> CheckedStudentList.selectInfo();
                case "3" -> {
                    return;
                }
                default -> System.out.println("\033[31;3m输入有误！请重新输入！\033[0m");
            }
        }

    }



}
