package com.password;

import com.io.Output;

import com.manage.AddCourseInfo;
import com.people.Course;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zy293
 * 对课程信息处理
 */
public class CheckCourseList implements Serializable {

    /**
     * 课程号重复判断
     */
    public static boolean repeatCourseId (String id){
        //第一次输入
        if (AddCourseInfo.courseList.isEmpty()){
            return true;
        }
        for (Course course  : AddCourseInfo.courseList) {
            String courseId = course.getCourseId();
            if (courseId.equals(id)){
                return false;
            }
        }
        return true;
    }

    /**
     * 修改课程--查询--选择
     */
    public static int selectCourse(String name){
        int count = 0;
        for (Course course : AddCourseInfo.courseList) {
            String courseName = course.getName();
            if (courseName.equals(name)){
                count++;
                StringBuilder builder = new StringBuilder();
                builder.append("[课程名]:").append(course.getName()).append("-")
                        .append("[课程号]:").append(course.getCourseId()).append("-")
                        .append("[学分]:").append(course.getCredits()).append("-")
                        .append("[学时]:").append(course.getHours()).append("小时");
                System.out.println(builder);
                System.out.println("---------------------------------------");
            }
        }

        return count;
    }

    /**
     * 确认课程名--修改信息
     */
    public static Course polishCouse(String id) {
        for (Course course : AddCourseInfo.courseList) {
            String courseId = course.getCourseId();
            if (courseId.equals(id)){
                return course;
            }
        }
        return null;

    }

    /**
     * 查询课程--模糊--包含
     */
    public static void courseSearch(String name){
        int count = 0;
        for (Course course : AddCourseInfo.courseList) {
            String courseName = course.getName();
            if (courseName.contains(name)){
                count++;
                StringBuilder builder = new StringBuilder();
                builder.append("[课程名]:").append(course.getName()).append("-")
                        .append("[课程号]:").append(course.getCourseId()).append("-")
                        .append("[学分]:").append(course.getCredits()).append("-")
                        .append("[学时]:").append(course.getHours()).append("小时");

                System.out.println(builder);
                System.out.println("---------------------------------------");
            }
        }
        if (count == 0){

            System.out.println("无任何查询结果！");
            return;
        }
        System.out.println("总计："+count + "条搜索结果");
    }

    public static void courseIf(String name){
        //抑制警告
        @SuppressWarnings("all")
        int count = 0;
        for (Course course : AddCourseInfo.courseList) {
            String courseName = course.getName();
            if (courseName.equals(name)){
                count++;
                StringBuilder builder = new StringBuilder();
                builder.append("[课程名]:").append(course.getName()).append("-")
                        .append("[课程号]:").append(course.getCourseId()).append("-")
                        .append("[学分]:").append(course.getCredits()).append("-")
                        .append("[学时]:").append(course.getHours()).append("小时");
                System.out.println(builder);
                System.out.println("---------------------------------------");
            }
        }
        if (count == 0){
            System.out.println("无任何查询结果！");
        }
    }


    /**
     * 删除课程
     */
    public static boolean deleteCourse(String id){
        //迭代器对象
        Iterator<Course> iterator = AddCourseInfo.courseList.iterator();
        while (iterator.hasNext()){
            Course course = iterator.next();
            if (course.getCourseId().equals(id)){
                iterator.remove();
                //删除成绩信息---根据课程号
                if (CheckScoreList.delete(id)) {
                    System.out.println("该学生的成绩信息也已经删除！");
                }
                return true;
            }
        }
        return false;


    }

    /**
     * 根据课程id拿到课程对象
     */
    public static Course searchCourse(String id){
        for (Course course : AddCourseInfo.courseList) {
            String courseId = course.getCourseId();
            if (courseId.equals(id)){
                return course;
            }
        }
        return null;
    }

    /**
     * 根据课程名得到课程号集合
     */
    public static List<String> getCourseId(String name){
        AddCourseInfo.courseList = Output.courseList();
        //保留课程集合ID
        List<String> tempList = new ArrayList<>();
        for (Course course : AddCourseInfo.courseList) {
            String courseName = course.getName();
            if (courseName.equals(name)){
                tempList.add(course.getCourseId());
            }
        }
        //可能时是空集合
        return tempList;

    }
}
