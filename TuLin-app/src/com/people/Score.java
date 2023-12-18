package com.people;

import java.io.Serializable;

/**
 * @author zy293
 * 成绩类
 * 课程号、学号、成绩、成绩分段（优秀、良好、及格、不及格）
 *
 */
public class Score implements Serializable {
    private String courseId;
    private String studentId;
    private double score;
    private String rank;

    public Score() {
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
