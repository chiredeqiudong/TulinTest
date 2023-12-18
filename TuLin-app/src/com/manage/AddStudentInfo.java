package com.manage;

import com.password.CheckScoreList;
import com.password.Checked;
import com.io.Input;
import com.io.Output;
import com.password.CheckedStudentList;
import com.people.Student;
import com.restore.Restore;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zy293
 * 添加学生信息
 */
public class AddStudentInfo implements Serializable {

    /**
     * 男
     */
    private static final char GENDER = '1';

    public static final Scanner SCANNER = new Scanner(System.in);

    public final static Lock INFO_LOCK = new ReentrantLock();

    /**
     * 学生基本信息集合
     */
    public static List<Student> studentsList = new ArrayList<>();

    /**
     * 学生信息管理页面
     */
    public static void studentInfo(){
        while (true) {
            System.out.println("\n\t\t*****************学生信息管理*******************");
            System.out.println("\t\t**                [1]:添加学生信息\t\t\t**");
            System.out.println("\t\t**                [2]:修改学生信息\t\t\t**");
            System.out.println("\t\t**                [3]:查询学生信息\t\t\t**");
            System.out.println("\t\t**                [4]:删除学生信息\t\t\t**");
            System.out.println("\t\t**                [5]:返回上一菜单\t\t\t**");
            System.out.println("\t\t**********************************************");
            System.out.println("\n请输入操作命令(command):");
            String command = SCANNER.next();
            switch (command) {
                case "1" -> start();
                case "2" -> polish();
                case "3" -> Frame.searchStudentFrame();
                case "4" -> CheckedStudentList.deleteStudentInfo();
                case "5" -> {
                    return;
                }
                default ->
                    //Java彩色字体格式："\033[你的字体颜色;字体效果m你的字符（输出的字符）\033[0m"
                        System.out.println("\033[31;3m输入有误！请重新输入！\033[0m");
            }
        }
    }


    /**
     * 添加学生信息--姓名--邮箱--学号
     * 保存信息
     * 锁对象--是同一资源
     */
    public static void start(){
        while (true) {
            Student student = new Student();
            System.out.println("\n\t\t*****************添加学生信息****************");
            System.out.println("请输入学生姓名:");
            String name = SCANNER.next();
            student.setName(name);
            //输入邮箱
            getEmail(student);
            //输入学号
            getIdCard(student);
            //根据学号--存储班级和性别
            getGradeGender(student,student.getIdCard());
            //信息建立时间
            LocalDateTime time = LocalDateTime.now();
            student.setRegisterTime(time);
            //放入对象集合
            studentsList.add(student);
            //数据保留--io
            Input.studentList(studentsList);
            System.out.println("学生信息添加成功");
            //操作记录
            Restore.addInfo(time,student.getName()+"学生的");
            //继续确认
            if (!Checked.judge()) {
                return;
            }
        }
    }

    /**
     * 修改学生信息
     */
    public static void polish(){
        //先添加学生信息
        if (studentsList.isEmpty()) {
            System.out.println("还没有学任何学生信息,请先添加!");
            return;
        }
        while (true) {
            System.out.println("\n\t\t*****************修改学生信息****************");
            System.out.println("请输入要该学生的姓名(支持模糊查询):");
            String name = SCANNER.next();
            int count = CheckedStudentList.studentSearch(name);
            if (count > 0){
                break;
            }
            System.out.println("\033[31;3m请重新输入！\033[0m");
        }
        //临时学生对象
        Student student;
        while (true) {
            System.out.println("请输入你要具体修改的学生的学号:");
            String id = SCANNER.next();
            student = idSearch(id);
            if (student != null){
                break;
            }
            System.out.println("\033[31;3m输入有误！请重新输入！\033[0m");
        }
        //修改框架
        Frame.polishFrame(student);
        //修改成功--最后修改时间--保存
        LocalDateTime endPolishTime = LocalDateTime.now();
        student.setPolishTime(endPolishTime);
        //io流实时保存
        Input.studentList(studentsList);
        System.out.println("操作成功!");
        //数据记录
        Restore.polishInfo(endPolishTime,student.getName()+"学生的");
    }

    /**
     * 根据id找对象
     */
    public static Student idSearch(String id){
        //根据id找对象
        for (Student student : studentsList) {
            String idCard = student.getIdCard();
            if (idCard.equals(id)){
                return student;
            }
        }
        //输入错误
        return null;
    }

    /**
     * 邮箱填写
     */
    public static void getEmail(Student student){
        //邮箱验证-->上锁
        INFO_LOCK.lock();
        try {
            while (true) {
                System.out.println("请输入邮箱(电话号码+@stu.gdou.edu.cn):");
                String email = SCANNER.next();
                if (Checked.emailCheck(email) && CheckedStudentList.repeatEmailCheck(email)) {
                    student.setEmail(email);
                    System.out.println("设置成功");
                    break;
                }
                System.out.println("\033[31;3m输入格式有误(不能有重复)，请重新输入:\033[0m");
            }
        } finally {
            INFO_LOCK.unlock();
        }
    }

    /**
     * 得到学号
     */
    public static void getIdCard(Student student){
        INFO_LOCK.lock();
        try {
            while (true) {
                System.out.println("\n填写学号信息");
                System.out.println("示例：2020(入学年) 0006[专业编号] 4[班级] 1[1-男 0-女] 26[班级次序]");
                System.out.println("-----------------------------------------------------");
                //读取io-专业代码
                Output.specialty();
                System.out.println("-----------------------------------------------------");
                System.out.println("请输入学号信息:");
                String studentId = SCANNER.next();
                if (Checked.studentIdCheck(studentId) && CheckedStudentList.repeatIdCheck(studentId)) {
                    //学生成绩id变
                    CheckScoreList.polish(student.getIdCard(),studentId);
                    student.setIdCard(studentId);
                    System.out.println("设置成功");
                    //成绩的学生id也要变
                    break;
                }
                System.out.println("\033[31;3m输入格式有误！注意:不可重复输入一个学号\033[0m");
            }
        } finally {
            INFO_LOCK.unlock();

        }

    }

    /**
     * 根据学号获取班级和姓名
     */
    public static void getGradeGender(Student student,String id) {
        //班级--添加
        String grade = id.charAt(8) + "";
        student.setGrade(grade);
        //性别
        char gender = id.charAt(9);
        if (GENDER == gender){
            student.setGender("男");
            return;
        }
        student.setGender("女");

    }








}
