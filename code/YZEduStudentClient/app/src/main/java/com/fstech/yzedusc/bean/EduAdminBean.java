package com.fstech.yzedusc.bean;

/**
 * Created by shaoxin on 18-4-9.
 * 教务管理员的模型类
 */

public class EduAdminBean {
    private int edu_admin_id;
    private int school_id;
    private String edu_admin_number;
    private String admin_name;

    public EduAdminBean() {
    }

    public int getEdu_admin_id() {
        return edu_admin_id;
    }

    public void setEdu_admin_id(int edu_admin_id) {
        this.edu_admin_id = edu_admin_id;
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public String getEdu_admin_number() {
        return edu_admin_number;
    }

    public void setEdu_admin_number(String edu_admin_number) {
        this.edu_admin_number = edu_admin_number;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    @Override
    public String toString() {
        return "EduAdminBean{" +
                "edu_admin_id=" + edu_admin_id +
                ", school_id=" + school_id +
                ", edu_admin_number='" + edu_admin_number + '\'' +
                ", admin_name='" + admin_name + '\'' +
                '}';
    }
}
