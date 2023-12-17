package com.manage;

import com.io.Output;
import com.thread.ThreadTest;

import java.util.Scanner;

/**
 * @author zy293
 * 测试类--学生成绩管理系统
 * 外围登录框架
 */
public class Test {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {
        //start（）界面
        Output.imageStart();
        //反序列化--学生--课程-成绩
        AddStudentInfo.studentsList = Output.studentList();
        AddCourseInfo.courseList = Output.courseList();
        StudentScoreManage.scoreList = Output.scoreList();
        while (true) {
            //主菜单
            System.out.println("\n\t\t*****************学生成绩管理系统******************");
            System.out.println("\t\t**               [1]:学生信息管理\t\t\t\t  **");
            System.out.println("\t\t**               [2]:课程信息管理\t\t\t\t  **");
            System.out.println("\t\t**               [3]:学生成绩管理\t\t\t\t  **");
            System.out.println("\t\t**               [4]:退出系统   \t\t\t\t  **");
            System.out.println("\t\t************************************************");
            System.out.println("\n请输入操作命令(command):");
            String command = SCANNER.next();
            switch (command) {
                case "1" -> AddStudentInfo.studentInfo();
                case "2" -> Frame.courseManage();
                case "3" -> StudentScoreManage.studentManage();
                case "4" -> {
                    Output.imageEnd();
                    return;
                }
                default ->
                    //Java彩色字体格式："\033[你的字体颜色;字体效果m你的字符（输出的字符）\033[0m"
                        System.out.println("\033[31;3m输入有误！请重新输入！\033[0m");
            }
        }
    }
}
