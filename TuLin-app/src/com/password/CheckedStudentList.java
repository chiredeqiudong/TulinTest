package com.password;

import com.io.Input;
import com.manage.AddStudentInfo;
import com.people.Student;
import com.restore.Restore;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Iterator;

import java.util.Scanner;

/**
 * @author zy293
 * 对集体信息判别
 */
public class CheckedStudentList implements Serializable {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * 修改学生信息
     * 姓名、学号、性别、班级、邮箱
     */
    public static int studentSearch(String name){
        int count = 0;
        //比对/展示
        for (Student student : AddStudentInfo.studentsList) {
            String studentName = student.getName();
            if (studentName.contains(name)){
                count++;
                StringBuffer appendInfo = new StringBuffer();
                 appendInfo.append("[姓名]:").append(student.getName()).append("-")
                         .append("[学号]:").append(student.getIdCard()).append("-")
                         .append("[性别]:").append(student.getGender()).append("-")
                         .append("[班级]:").append(student.getGrade()).append("-")
                         .append("[邮箱]:").append(student.getEmail());
                System.out.println(appendInfo);
                System.out.println("-----------------------------------------------");
            }
        }
        System.out.println("总共：" + count + "条搜索结果");
        return count;

    }

    /**
     * 查询学生信息/学号/姓名/邮箱/注册和修改时间
     * 显示全部信息
     */
    public static void selectInfo(){
        //特判
        if (AddStudentInfo.studentsList.isEmpty()) {
            System.out.println("还没有学任何学生信息,请先添加!");
            return;
        }
        while (true) {
            System.out.println("请输入需要查询的学生的姓名或学号或邮箱:");
            String password = SCANNER.next();
            showStudentInfo(password);
            //判断确认
            if (!Checked.judge()) {
                return;
            }
        }
    }

    /**
     * 条件查询
     */
    public static void selectIf(){
        //特判
        if (AddStudentInfo.studentsList.isEmpty()) {
            System.out.println("还没有学任何学生信息,请先添加!");
            return;
        }
        int sum = 0;
        System.out.println("请输入需要查询的学生的姓名或学号或邮箱:");
        String password = SCANNER.next();
        for (Student student : AddStudentInfo.studentsList) {
            String name = student.getName();
            String idCard = student.getIdCard();
            String studentEmail = student.getEmail();
            if (name.equals(password) || idCard.equals(password) || studentEmail.equals(password)){
                sum += 1;
                StringBuffer appendInfo = new StringBuffer();
                appendInfo.append("[姓名]:").append(student.getName()).append("-")
                        .append("[学号]:").append(student.getIdCard()).append("-")
                        .append("[性别]:").append(student.getGender()).append("-")
                        .append("[班级]:").append(student.getGrade()).append("-")
                        .append("[邮箱]:").append(student.getEmail()).append("-")
                        .append("[注册时间]:").append(student.getRegisterTime()).append("-")
                        .append("[最后修改时间]:").append(student.getPolishTime());
                System.out.println(appendInfo);
                System.out.println("-----------------------------------------------");
            }
        }
        if (sum == 0){
            System.out.println("无任何信息！");
        }
    }

    /**
     * 展示信息
     */
    public static void showStudentInfo(String password){
        int count = 0;
        for (Student student : AddStudentInfo.studentsList) {
            String name = student.getName();
            String idCard = student.getIdCard();
            String email = student.getEmail();
            //模糊查询---包含就行
            if (name.contains(password) || idCard.contains(password) || email.contains(password)){
                count++;
                StringBuffer appendInfo = new StringBuffer();
                appendInfo.append("[姓名]:").append(student.getName()).append("-")
                        .append("[学号]:").append(student.getIdCard()).append("-")
                        .append("[性别]:").append(student.getGender()).append("-")
                        .append("[班级]:").append(student.getGrade()).append("-")
                        .append("[邮箱]:").append(student.getEmail()).append("-")
                        .append("[注册时间]:").append(student.getRegisterTime()).append("-")
                        .append("[最后修改时间]:").append(student.getPolishTime());
                System.out.println(appendInfo);
                System.out.println("-----------------------------------------------");
            }
        }
        System.out.println("总计:"+ count + "条记录！");

    }

    /**
     * 学号重复性检查
     */
    public static boolean repeatIdCheck(String studentId){
        if (AddStudentInfo.studentsList.isEmpty()){
            return true;
        }
        for (Student student : AddStudentInfo.studentsList) {
            String idCard = student.getIdCard();
            if (idCard.equals(studentId)){
                return false;
            }
        }
        return true;
    }

    /**
     * 邮箱重复性检验
     */
    public static boolean repeatEmailCheck(String email){
        //第一次
        if (AddStudentInfo.studentsList.isEmpty()){
            return true;
        }
        for (Student student : AddStudentInfo.studentsList) {
            String studentEmail = student.getEmail();
            if (studentEmail.equals(email)){
                return false;
            }
        }
        return true;
    }

    /**
     * 删除学生信息（迭代器）--保存信息
     * 具体的姓名和学号(唯一性)
     */
    public static void deleteStudentInfo(){
        //先添加学生信息
        if (AddStudentInfo.studentsList.isEmpty()) {
            System.out.println("还没有学任何学生信息,请先添加!");
            return;
        }

        //锁
        AddStudentInfo.INFO_LOCK.lock();
        try {
            while (true) {
                System.out.println("\n\t\t*****************删除学生信息****************");
                System.out.println("请输入姓名:");
                String name = SCANNER.next();
                System.out.println("请输入学号:");
                String id = SCANNER.next();
                if (!deleteInfo(name, id)) {
                    System.out.println("删除失败！可能原因：无此学生，输入的姓名和学号错误");
                }
                else {
                    System.out.println("删除成功");
                    //保存信息
                    Input.studentList(AddStudentInfo.studentsList);
                    //操作记录
                    Restore.deleteInfo(LocalDateTime.now(),name + "学生的");
                }
                //是否继续
                if (!Checked.judge()) {
                    return;
                }
            }
        } finally {
            AddStudentInfo.INFO_LOCK.unlock();

        }
    }

    /**
     * 根据姓名和学号删除信息
     * 删除后--成绩信息--也删除
     */
    public static boolean deleteInfo(String name, String id){
        //迭代器对象
        Iterator<Student> iterator = AddStudentInfo.studentsList.iterator();
        while (iterator.hasNext()){

            Student student = iterator.next();
            if (student.getName().equals(name) && student.getIdCard().equals(id)){
                iterator.remove();
                //删除成绩信息->数据同步
                if (CheckScoreList.delete(id)) {
                    System.out.println("该学生的成绩信息也已删除！");
                }
                return true;
            }
        }
        return false;
    }
    /**
     * 根据学生id拿到学生对象
     */
    public static Student searchStudent(String id){
        //读取信息
        for (Student student : AddStudentInfo.studentsList) {
            String idCard = student.getIdCard();
            if (idCard.equals(id)){
                return student;
            }
        }
        return null;
    }





}
