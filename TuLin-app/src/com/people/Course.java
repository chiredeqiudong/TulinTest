package com.people;

import java.io.Serializable;

/**
 * @author zy293
 * 课程类
 * 课程号、课程名、学分、学时 (有多个课程)
 */
public class Course implements Serializable {
    private String courseId;
    private String name;
    private String credits;
    private String hours;

    public Course() {
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
