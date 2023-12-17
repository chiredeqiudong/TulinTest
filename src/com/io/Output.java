package com.io;

import com.people.Course;
import com.people.Score;
import com.people.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * io流读取信息
 * @author zy293
 */
public class Output {

    /**
     * 欢迎界面
     */
    public static void imageStart(){
        try (
                Reader os = new FileReader("TuLin-app\\src\\Welcome");
                BufferedReader buffer = new BufferedReader(os)
                ){
            String line = buffer.readLine();
            while (line!=null){
                //Java彩色字体格式："\033[你的字体颜色;字体效果m你的字符（输出的字符）\033[0m"
                System.out.println("\033[35m"+ line +"\033[0m");
                line = buffer.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 结束界面
     */
    public static void imageEnd(){
        try (
                Reader os = new FileReader("TuLin-app\\src\\Ending");
                BufferedReader buffer = new BufferedReader(os)
        ){
            String line = buffer.readLine();
            while (line!=null){
                System.out.println("\033[35m"+ line +"\033[0m");
                line = buffer.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 读取专业码
     */
    public static void specialty(){
        try (
                Reader os = new FileReader("TuLin-app\\src\\Specialty");
                BufferedReader buffer = new BufferedReader(os)
        ){
            String line = buffer.readLine();
            while (line!=null){
                System.out.println(line);
                line = buffer.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 学生反序列化
     */
    public static List<Student> studentList(){
        try (
                ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream("TuLin-app\\src\\Student_Information.txt") )
                ) {
            // 如果读取为null（第一次）---返回错误
            return (List<Student>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    /**
     * 课程反序列化
     */
    public static List<Course> courseList(){
        try (
                ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream("TuLin-app\\src\\Course_Information.txt") )
        ) {

            // 如果读取为null（第一次）---返回错误
            return (List<Course>) ois.readObject();
        }
        catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * 成绩反序列化
     */
    public static List<Score> scoreList(){
        try (
                ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream("TuLin-app\\src\\Score_Information.txt"))
        ) {
            // 如果读取为null（第一次）---返回错误
            return (List<Score>) ois.readObject();
        }
        catch (Exception e) {
            return new ArrayList<>();
        }


    }












}
