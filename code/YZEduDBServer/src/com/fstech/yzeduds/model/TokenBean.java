package com.fstech.yzeduds.model;

public class TokenBean {

    // 基本参数信息
    private int user_id;    // 用户id
    private int role_id;    // 角色id(学生|教师)
    private int role_type;  // 角色id（1学生，2教师）
    private int class_id;   // 班级id
    private int faculty_id; // 院系id
    private int school_id;  // 院校id
    
    public TokenBean() {
        super();
    }

    public TokenBean(int user_id, int role_id, int role_type, int class_id,
            int faculty_id, int school_id) {
        super();
        this.user_id = user_id;
        this.role_id = role_id;
        this.role_type = role_type;
        this.class_id = class_id;
        this.faculty_id = faculty_id;
        this.school_id = school_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getRole_type() {
        return role_type;
    }

    public void setRole_type(int role_type) {
        this.role_type = role_type;
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

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    @Override
    public String toString() {
        return "TokenBean [user_id=" + user_id + ", role_id=" + role_id
                + ", role_type=" + role_type + ", class_id=" + class_id
                + ", faculty_id=" + faculty_id + ", school_id=" + school_id
                + "]";
    }
    
}
