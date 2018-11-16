package com.fstech.yzeduds.model;

public class UserBean {
    private int user_id;
    private String user_account;
    private String user_password;
    private String user_phone;
    private String user_sex;
    private int user_age;
    private String user_avatar;
    private int student_id;
    private String student_name;
    private String student_number;
    private int class_id;
    private int faculty_id;
    private int teacher_id;
    private String teacher_name;
    private String teacher_number;
    private int school_id;

    public UserBean() {
        super();
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public int getUser_age() {
        return user_age;
    }

    public void setUser_age(int user_age) {
        this.user_age = user_age;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_number() {
        return teacher_number;
    }

    public void setTeacher_number(String teacher_number) {
        this.teacher_number = teacher_number;
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getFaculty_id() {
        return faculty_id;
    }

    public void setFaculty_id(int faculty_id) {
        this.faculty_id = faculty_id;
    }

    @Override
    public String toString() {
        return "UserBean [user_id=" + user_id + ", user_account="
                + user_account + ", user_password=" + user_password
                + ", user_phone=" + user_phone + ", user_sex=" + user_sex
                + ", user_age=" + user_age + ", user_avatar=" + user_avatar
                + ", student_id=" + student_id + ", student_name="
                + student_name + ", student_number=" + student_number
                + ", class_id=" + class_id + ", faculty_id=" + faculty_id
                + ", teacher_id=" + teacher_id + ", teacher_name="
                + teacher_name + ", teacher_number=" + teacher_number
                + ", school_id=" + school_id + "]";
    }
    

}
