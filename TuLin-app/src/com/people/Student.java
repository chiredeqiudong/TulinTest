package com.people;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author zy293
 * 学生实体类
 * 姓名、学号、性别、班级、邮箱、信息建立时间、最后修改时间
 * 学号固定：2020[入学年] 0006[专业码] 4[班级] 1[男、友] 26[班级序号]
 */
public class Student implements Serializable {
    private String name;
    private String idCard;
    private String gender;
    private String grade;
    private String email;
    private LocalDateTime registerTime;
    private LocalDateTime polishTime;

    /**
     * 时间格式化对象
     */
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");


    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegisterTime() {
        return FORMATTER.format(registerTime);
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

    public String getPolishTime() {
        if (Objects.isNull(polishTime)){
            return "还未有过修改";
        }
        return FORMATTER.format(polishTime);

    }

    public void setPolishTime(LocalDateTime polishTime) {
        this.polishTime = polishTime;
    }
}
