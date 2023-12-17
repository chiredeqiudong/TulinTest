package com.password;

import java.util.Objects;
import java.util.Scanner;

/**
 * @author zy293
 * 数据校验
 */
public class Checked {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String COMMAND= "continue";

    /**
     * @param email
     * 邮箱校验
     */
    public static boolean emailCheck(String email){
        //非空判断
        if (Objects.isNull(email) || email.isEmpty()){
            return false;
        }
        // \\w-->\\d
        String regex = "1[3-9]\\d{9}@stu\\.gdou\\.edu\\.cn";
        return email.matches(regex);
    }

    /**
     * 学号校验（格式）
     */
    public static boolean studentIdCheck(String id){
        if (Objects.isNull(id) || id.isEmpty() ){
            return false;
        }
        // \\w-->\\d
        String regex = "\\d{4}000[0-6][1-4][01][1-9]\\d{1,2}";
        return id.matches(regex);
    }


    /**
     * 确认是否继续添加
     */
    public static boolean judge(){
        System.out.println("\n如果继续\t---->>\tcontinue");
        System.out.println("返回上一级\t---->>\t任意输入");
        System.out.println("请输入:");
        String message = SCANNER.next();
        return COMMAND.equals(message);
    }

    /**
     * 课程号检查
     */
    public static boolean courseIdCheck(String id){
        if (Objects.isNull(id) || id.isEmpty()){
            return false;
        }
        String regex = "\\d{4}";
        return id.matches(regex);

    }

    /**
     *学分
     */
    public static boolean creditsCheck(String credits){
        String regex = "[1-4]\\.[0-9]";
        return credits.matches(regex);

    }

    /**
     * 学时
     */
    public static boolean hoursCheck(String hours){
        String regex = "[1-9]\\d?";
        return hours.matches(regex);
    }

    /**
     * 分数判别0-100保留一位小数
     */
    public static boolean scoreCheck(double grade){
        if (grade < 0){
            return false;
        }
        String score = "" + grade;
        String regex = "(([1-9]?\\d?(\\.\\d{1,2})?)|100|100\\.(0){1,2})";
        return score.matches(regex);

    }


}
