package com.io;

import com.people.Course;
import com.people.Score;
import com.people.Student;

import java.io.*;
import java.util.List;

/**
 * @author zy293
 * 存储信息
 */
public class Input {

    /**
     * 学生序列化流
     */
    public static void studentList(List<Student> list){
        try (
                ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream("TuLin-app\\src\\Student_Information.txt"))
                ){
            oos.writeObject(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 课程序列化流
     */
    public static void courseList(List<Course> list){
        try (
                ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream("TuLin-app\\src\\Course_Information.txt"))
        ){
            oos.writeObject(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 成绩序列化流
     */
    public static void scoreList(List<Score> list){
        try (
                ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream("TuLin-app\\src\\Score_Information.txt"))
        ){
            oos.writeObject(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static void timeList(List<String> list){
        try (
                ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream("TuLin-app\\src\\Time_Information.txt"))
        ){
            oos.writeObject(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
