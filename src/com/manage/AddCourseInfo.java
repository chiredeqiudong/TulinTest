package com.manage;

import com.io.Input;

import com.password.CheckCourseList;
import com.password.Checked;
import com.people.Course;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zy293
 * 学生课程信息添加
 * 课程号、课程名、学分、学时 (有多个课程)
 */
public class AddCourseInfo implements Serializable {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * 课程信息集合
     */
    public static List<Course> courseList = new ArrayList<>();

    /**
     * 课程信息添加
     */
    public static void addCourse(){
        while (true) {
            Course course = new Course();
            System.out.println("\n\t\t*****************添加课程信息****************");
            System.out.println("请输入课程名:");
            course.setName(SCANNER.next());
            //课程号
            getId(course);
            //学分
            getCredits(course);
            //学时
            getHours(course);
            //集合
            courseList.add(course);
            //序列化
            Input.courseList(courseList);
            //是否继续添加
            System.out.println("添加成功!");
            if (!Checked.judge()) {
                return;
            }
        }

    }

    /**
     * 修改课程信息--保存
     * 每一个课程有一个对应的课程号
     */
    public static void polishCourseInfo (){
        if (courseList.isEmpty()){
            System.out.println("还没有学任何学生信息,请先添加!");
            return;
        }
        System.out.println("\n\t\t*****************修改课程信息****************");
        //搜索列表的数目
        int count;
        while (true) {
            System.out.println("请输入你要修改课程的课程名:");
            String courseName = SCANNER.next();
            count = CheckCourseList.selectCourse(courseName);
            if (count >  0){
                break;
            }
            System.out.println("输入有误！");
        }
        System.out.println("总计：" + count + "条搜索结果");
        Course course;
        while (true) {
            System.out.println("请输入具体的课程号进行修改:");
            String courseId = SCANNER.next();
             course = CheckCourseList.polishCouse(courseId);
            if (course != null){
                break;
            }
            System.out.println("输入的课程号错误！");
        }
        Frame.polishFrame(course);
        //保存到io流
        Input.courseList(courseList);
        System.out.println("修改成功！");
    }

    /**
     * 查询课程信息--模糊
     */
    public static void searchCourse (){
        if (courseList.isEmpty()){
            System.out.println("还没有学任何学生信息,请先添加!");
            return;
        }
        while (true) {
            System.out.println("请输入查询的课程名(全称||简称):");
            String name = SCANNER.next();
            CheckCourseList.courseSearch(name);
            //是否继续搜索
            if (!Checked.judge()) {
                return;
            }
        }
    }

    public static void searchIf (){
        if (courseList.isEmpty()){
            System.out.println("还没有学任何学生信息,请先添加!");
            return;
        }
        while (true) {
            System.out.println("请输入查询的课程名(全称):");
            String name = SCANNER.next();
            CheckCourseList.courseIf(name);
            //是否继续搜索
            if (!Checked.judge()) {
                return;
            }
        }
    }


    /**
     * 删除课程信息
     */
    public static void deleteCourse (){
        if (courseList.isEmpty()){
            System.out.println("还没有学任何学生信息,请先添加!");
            return;
        }
        while (true) {
            System.out.println("\n\t\t*****************删除课程信息****************");
            System.out.println("请输入需要删除的课程号:");
            String id = SCANNER.next();
            if (!CheckCourseList.deleteCourse(id)) {
                System.out.println("删除失败！可能原因：课程号错误");
            }
            else {
                System.out.println("删除成功！");
            }
            if (!Checked.judge()) {
                //保存信息
                Input.courseList(courseList);
                return;
            }
        }
    }

    /**
     * * 课程号
     */
    public static void getId(Course course){
        while (true) {
            System.out.println("请输入课程号(4位数字):");
            String id = SCANNER.next();
            if (Checked.courseIdCheck(id) && CheckCourseList.repeatCourseId(id)) {
                course.setCourseId(id);
                return;
            }
            System.out.println("\033[31;3m输入有误(格式或者出现重复)！请重新输入！\033[0m");
        }

    }

    /**
     * 学分
     */
    public static void getCredits(Course course){
        while (true) {
            System.out.println("请输入该门课程的学分(保留一位小数)[1.0-5.0):");
            String credits = SCANNER.next();
            if (Checked.creditsCheck(credits)) {
                course.setCredits(credits);
                return;
            }
            System.out.println("\033[31;3m输入有误！请重新输入！\033[0m");
        }


    }

    /**
     * 学时
     */
    public static void getHours(Course course){
        while (true) {
            System.out.println("请输入该门课程的学时(<100):");
            String hours = SCANNER.next();
            if (Checked.hoursCheck(hours)) {
                course.setHours(hours);
                return;
            }
            System.out.println("\033[31;3m输入有误！请重新输入！\033[0m");
        }

    }


}
