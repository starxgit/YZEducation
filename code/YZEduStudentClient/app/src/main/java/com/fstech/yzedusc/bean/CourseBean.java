package com.fstech.yzedusc.bean;

import java.io.Serializable;

/**
 * Created by shaoxin on 18-3-29.
 */

public class CourseBean implements Serializable {
    private int course_id;              // 课程id
    private String course_code;         // 课程代码
    private String course_name;         // 课程名称
    private String course_introduce;    // 课程介绍
    private String course_teacher;      // 课程教师(通过teacher_id找到这个教师的teacher_name)
    private int course_sum_student;     // 课程容量人数
    private int course_learn_student;   // 课程学习人数
    private String course_cover;        // 课程封面途径
    private int course_sum;             // 总课时
    private int course_type;            // 课程类型
    private double course_price;        // 课程价格
    private int course_finish;          // 已完成课时

    public CourseBean() {
    }

    public CourseBean(int course_id, String course_code, String course_name, String course_introduce, String course_teacher,
                      int course_sum_student, int course_learn_student, String course_cover, int course_sum, int course_type, double course_price, int course_finish) {
        this.course_id = course_id;
        this.course_code = course_code;
        this.course_name = course_name;
        this.course_introduce = course_introduce;
        this.course_teacher = course_teacher;
        this.course_sum_student = course_sum_student;
        this.course_learn_student = course_learn_student;
        this.course_cover = course_cover;
        this.course_sum = course_sum;
        this.course_type = course_type;
        this.course_price = course_price;
        this.course_finish = course_finish;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_introduce() {
        return course_introduce;
    }

    public void setCourse_introduce(String course_introduce) {
        this.course_introduce = course_introduce;
    }

    public String getCourse_teacher() {
        return course_teacher;
    }

    public void setCourse_teacher(String course_teacher) {
        this.course_teacher = course_teacher;
    }

    public int getCourse_sum_student() {
        return course_sum_student;
    }

    public void setCourse_sum_student(int course_sum_student) {
        this.course_sum_student = course_sum_student;
    }

    public int getCourse_learn_student() {
        return course_learn_student;
    }

    public void setCourse_learn_student(int course_learn_student) {
        this.course_learn_student = course_learn_student;
    }

    public String getCourse_cover() {
        return course_cover;
    }

    public void setCourse_cover(String course_cover) {
        this.course_cover = course_cover;
    }

    public int getCourse_sum() {
        return course_sum;
    }

    public void setCourse_sum(int course_sum) {
        this.course_sum = course_sum;
    }

    public int getCourse_type() {
        return course_type;
    }

    public void setCourse_type(int course_type) {
        this.course_type = course_type;
    }

    public double getCourse_price() {
        return course_price;
    }

    public void setCourse_price(double course_price) {
        this.course_price = course_price;
    }

    public int getCourse_finish() {
        return course_finish;
    }

    public void setCourse_finish(int course_finish) {
        this.course_finish = course_finish;
    }

    @Override
    public String toString() {
        return "CourseBean{" +
                "course_id=" + course_id +
                ", course_code='" + course_code + '\'' +
                ", course_name='" + course_name + '\'' +
                ", course_introduce='" + course_introduce + '\'' +
                ", course_teacher='" + course_teacher + '\'' +
                ", course_sum_student=" + course_sum_student +
                ", course_learn_student=" + course_learn_student +
                ", course_cover='" + course_cover + '\'' +
                ", course_sum=" + course_sum +
                ", course_type=" + course_type +
                ", course_price=" + course_price +
                ", course_finish=" + course_finish +
                '}';
    }
}
