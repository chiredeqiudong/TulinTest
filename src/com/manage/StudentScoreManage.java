package com.manage;

import com.io.Input;

import com.io.Output;
import com.password.CheckScoreList;
import com.password.Checked;
import com.people.Score;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * @author zy293
 * 学生成绩管理
 */
public class StudentScoreManage implements Serializable {
    public static List<Score> scoreList = new ArrayList<>();
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void studentManage() {
        while (true) {
            System.out.println("\n\t\t*****************学生成绩管理*******************");
            System.out.println("\t\t**                [1]:添加学生成绩\t\t\t**");
            System.out.println("\t\t**                [2]:修改学生成绩\t\t\t**");
            System.out.println("\t\t**                [3]:查询学生成绩\t\t\t**");
            System.out.println("\t\t**                [4]:删除学生成绩\t\t\t**");
            System.out.println("\t\t**                [5]:学生成绩排名\t\t\t**");
            System.out.println("\t\t**                [6]:返回上一菜单\t\t\t**");
            System.out.println("\t\t**********************************************");
            System.out.println("\n请输入操作命令(command):");
            String command = SCANNER.next();
            switch (command) {
                case "1" -> addStudentScore();
                case "2" -> polishStudentScore();
                case "3" -> Frame.searchFrame();
                case "4" -> deleteStudentScore();
                case "5" -> scoreRanking();
                case "6" -> {
                    return;
                }
                default ->
                    //Java彩色字体格式："\033[你的字体颜色;字体效果m你的字符（输出的字符）\033[0m"
                        System.out.println("\033[31;3m输入有误！请重新输入！\033[0m");
            }
        }

    }

    /**
     * 添加学生成绩
     * 课程号--学号--成绩--成绩判定分段--排名
     */
    public static void addStudentScore() {
        // 初始化学生和课程信息---成绩
        AddStudentInfo.studentsList = Output.studentList();
        AddCourseInfo.courseList = Output.courseList();
        //先添加学生信息--和课程信息
        if (AddStudentInfo.studentsList.isEmpty() || AddCourseInfo.courseList.isEmpty()) {
            System.out.println("还没有任何学生和课程信息,请先添加!");
            return;
        }
        Score score = new Score();
        while (true) {
            System.out.println("\n\t\t*****************添加学生成绩****************");
            System.out.println("请输入课程号:");
            String courseId = SCANNER.next();
            System.out.println("请输入学生的学号:");
            String studentId = SCANNER.next();
            //判断---io流--保存
            if (CheckScoreList.checkStudentId(studentId) &&
                    CheckScoreList.checkCourseId(courseId)
                    && CheckScoreList.repeatCheck(studentId, courseId)) {
                score.setStudentId(studentId);
                score.setCourseId(courseId);
                //添加成绩
                addScore(score);
                //成绩分段
                rank(score);
                //集合保存对象
                scoreList.add(score);
                //io流保存--序列化
                Input.scoreList(scoreList);
                System.out.println("添加成功!");
                break;
            }
            System.out.println("\033[31;3m输入有误(可能重复输入)！请重新输入！\033[0m");

        }

    }

    /**
     * 修改学生成绩
     */
    public static void polishStudentScore() {
        //添加学生信息--和课程信息
        if (scoreList.isEmpty()) {
            System.out.println("\033[31;3m还没有任何成绩信息,请先添加!\033[0m");
            return;
        }
        while (true) {
            System.out.println("\n\t\t*****************修改学生成绩****************");
            System.out.println("请输入该学生的学号:");
            String id = SCANNER.next();
            // 显示信息---连接---姓名--课程名
            Score score = CheckScoreList.showInfo(id);
            if (score != null) {
                addScore(score);
                //成绩分段也要改变
                rank(score);
                //保存
                Input.scoreList(scoreList);
                break;
            }
            System.out.println("\033[31;3m输入有误！请重新输入！\033[0m");
        }

    }

    /**
     * 条件查询
     */
    public static void searchF() {
        //先添加学生信息--和课程信息
        if (scoreList.isEmpty()) {
            System.out.println("\033[31;3m还没有任何成绩信息,请先添加!\033[0m");
            return;
        }

        //文档--对象
        while (true) {
            System.out.println("请输入学生的学号:");
            String studentId = SCANNER.next();
            Score score = CheckScoreList.showInfo(studentId);
            if (score != null) {
                return;
            }
            System.out.println("\033[31;3m没有此学生的成绩信息。请重新输入！\033[0m");

        }


    }

    /**
     * 模糊查询
     * 学号--课程号--分数--成绩分段
     */
    public static void searchM() {
        //先添加学生信息--和课程信息
        if (scoreList.isEmpty()) {
            System.out.println("\033[31;3m还没有任何成绩信息,请先添加!\033[0m");
            return;
        }
        //文档--对象
        System.out.println("请输入需要查询的相关信息(学号或课程号或分数或成绩分段):");
        String passWord = SCANNER.next();
        int count = CheckScoreList.searchScore(passWord);
        System.out.println("总计:" + count + "条搜索结果！");


    }

    /**
     * 删除学生成绩
     */
    public static void deleteStudentScore() {
        if (scoreList.isEmpty()) {
            System.out.println("\033[31;3m还没有任何成绩信息,请先添加!\033[0m");
            return;
        }
        //文档--对象
        System.out.println("\n\t\t*****************删除学生成绩****************");
        System.out.println("请输入该学生的学号:");
        String id = SCANNER.next();
        if (CheckScoreList.deleteScore(id)) {
            System.out.println("删除成功！！！");
            //保存
            Input.scoreList(scoreList);
            return;
        }
        System.out.println("删除失败！！！");

    }

    /**
     * 添加分数
     */
    public static void addScore(Score scores) {
        try {
            while (true) {
                System.out.println("请输入分数[0-100]:(最多保留两位小数)");
                //double类型数据--输入异常
                double score = SCANNER.nextDouble();
                //如果输入不是字母--不执行
                if (Checked.scoreCheck(score)) {
                    scores.setScore(score);
                    return;
                }
                System.out.println("\033[31;3m输入的分数必须满足>=0!\033[0m");
            }
        } catch (Exception e) {
            System.out.println("\033[31;3m输入的数据格式有误，学生成绩为0\033[0m");
        }
    }

    /**
     * 成绩分段
     * [0-60) [60-70) [70-85) [85-100]
     */
    public static void rank(Score score) {
        //得到分数
        double grade = score.getScore();
        double rank1 = 60.0;
        double rank2 = 70.0;
        double rank3 = 85.0;
        if (grade < rank1) {
            score.setRank("不及格");
        } else if (grade >= rank1 && grade < rank2) {
            score.setRank("及格");
        } else if (grade >= rank2 && grade < rank3) {
            score.setRank("良好");
        } else {
            score.setRank("优秀");
        }
    }

    /**
     * 学生成绩科目排名--map排序
     */
    public static void scoreRanking() {
        if (scoreList.isEmpty()) {
            System.out.println("\033[31;3m还没有任何成绩信息,请先添加!\033[0m");
            return;
        }
        //文档--对象
        System.out.println("\n\t\t*****************学生成绩排名***************");
        System.out.println("请输入课程名:");
        String courseName = SCANNER.next();
        CheckScoreList.projectRanking(courseName);
        //输入正确性判断

    }


}
