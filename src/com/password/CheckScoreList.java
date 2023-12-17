package com.password;

import com.io.Output;

import com.manage.StudentScoreManage;
import com.people.Course;
import com.people.Score;
import com.people.Student;

import java.io.Serializable;
import java.util.*;

/**
 * @author zy293
 * 成绩集合--学生集合--课程结合
 */
public class CheckScoreList implements Serializable {

    /**
     * 检查课程id
     */
    public static boolean checkCourseId(String id){
        //读取
        List<Course> courseList = Output.courseList();
        for (Course course : courseList) {
            String courseId = course.getCourseId();
            if (courseId.equals(id)){
                return true;
            }
        }
        return false;
    }

    /**
     * 检查学生id
     */
    public static boolean checkStudentId(String id){
        //读取
        List<Student> studentList = Output.studentList();
        for (Student student : studentList) {
            String idCard = student.getIdCard();
            if (idCard.equals(id)){
                return true;
            }
        }
        return false;

    }

    /**
     * 重复性检验--一个学生只能有该门课一次
     */
    public static boolean repeatCheck(String id1,String id2){
        if (StudentScoreManage.scoreList.isEmpty()){
            return true;
        }
        for (Score score : StudentScoreManage.scoreList) {
            String studentId = score.getStudentId();
            String courseId = score.getCourseId();
            if (studentId.equals(id1) && courseId.equals(id2)){
                return false;
            }
        }

        return true;
    }

    /**
     * 根据学号展示信息（具体条件）
     */
    public static Score showInfo(String id1){
        for (Score score : StudentScoreManage.scoreList) {
            String studentId = score.getStudentId();
            String courseId = score.getCourseId();
            if (studentId.equals(id1)){
                //拿到对象
                Course course = CheckCourseList.searchCourse(courseId);
                Student student = CheckedStudentList.searchStudent(id1);
                StringBuilder builder = new StringBuilder();
                //成绩添加成功--则必然有此id存在
                assert course != null;
                assert student != null;
                builder.append("[课程名]:").append(course.getName()).append("-")
                    .append("[课程号]:").append(courseId).append("-")
                        .append("[姓名]:").append(student.getName()).append("-")
                        .append("[学号]:").append(score.getStudentId()).append("-")
                        .append("[分数]:").append(score.getScore()).append("-")
                        .append("[成绩分段]:").append(score.getRank());
                System.out.println(builder);
                System.out.println("-----------------------------------------------");
                return score;
            }
        }
        return null;
    }

    /**
     * 查询学生成绩--模糊查询
     * 学生id -- 课程号 --分数 -- 排名
     */
    public static int searchScore(String password){
        int count = 0;
        for (Score score : StudentScoreManage.scoreList) {
            String studentId = score.getStudentId();
            String courseId = score.getCourseId();
            String studentScore = "" +  score.getScore();
            String rank = score.getRank();
            // 包含--模糊--不具体
            if (studentId.contains(password) || courseId.contains(password)
            || studentScore.contains(password) || rank.contains(password) ){
                count++;
                Course course = CheckCourseList.searchCourse(courseId);
                Student student = CheckedStudentList.searchStudent(studentId);
                StringBuilder builder = new StringBuilder();
                assert course != null;
                assert student != null;
                builder.append("[课程名]:").append(course.getName()).append("-")
                        .append("[课程号]:").append(courseId).append("-")
                        .append("[姓名]:").append(student.getName()).append("-")
                        .append("[学号]:").append(studentId).append("-")
                        .append("[分数]:").append(studentScore).append("-")
                        .append("[成绩分段]:").append(rank);
                System.out.println(builder);
                System.out.println("-----------------------------------------------");
            }
        }
        return count;
    }

    /**
     *成绩主动删除
     */
    public static boolean deleteScore(String id){
        for (Score score : StudentScoreManage.scoreList) {
            String studentId = score.getStudentId();
            if (studentId.equals(id)){
                return StudentScoreManage.scoreList.remove(score);
            }
        }
        return false;
    }

    /**
     * 数据同步--学生和课程信息删除--成绩被动删除
     */
    public static boolean delete(String id){
        for (Score score : StudentScoreManage.scoreList) {
            String studentId = score.getStudentId();
            String courseId = score.getCourseId();
            if (studentId.equals(id) || courseId.equals(id)){
                return StudentScoreManage.scoreList.remove(score);
            }
        }
        return false;
    }


    /**
     * 成绩排名--通过课程名拿到相关课程号信息！
     */
    public static void projectRanking(String name){
        //通过课程名拿到相关课程号信息！
        List<String> courseId = CheckCourseList.getCourseId(name);
        if (courseId.isEmpty()){
            System.out.println("\033[31;3m输入的课程名错误(也可能还未添加此课程信息)！\033[0m");
        }
        for (String id : courseId) {
            showRanking(id);
        }

    }

    /**
     * 成绩排名处理
     */
    public static void showRanking(String courseId){
        //键值对集合--比较--》排序
        Map<String,Double> mapScore = new LinkedHashMap<>();
        for (Score score : StudentScoreManage.scoreList) {
            //添加了成绩的课程号
            if (score.getCourseId().equals(courseId)){
                //学生id（唯一性）-学生分数
                String studentId = score.getStudentId();
                double scoreScore = score.getScore();
                mapScore.put(studentId,scoreScore);

            }
        }
        //比较分数大小-
        Map<String, Double> mapSort = compare(mapScore);
        //输出
        showScoreRanking(mapSort);

    }

    /**
     * 比较分数大小
     */
    public static Map<String,Double> compare(Map<String,Double> map){
        Map<String,Double> result = new LinkedHashMap<>();
        //降序排序
        map.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));

        return result;
    }

    /**
     * 排序后的map集合
     */
    public static void showScoreRanking(Map<String,Double> map ) {
        Set<String> student = map.keySet();
        int i = 1;
        for (String key : student) {
            System.out.println("第" + i + "名:---->" + "[学号]:" + key + "-"
                    + "[分数]:" + map.get(key));
            i++;
            System.out.println("------------------------------------");
        }
    }
}
