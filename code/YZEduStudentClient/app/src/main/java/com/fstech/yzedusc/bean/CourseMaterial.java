package com.fstech.yzedusc.bean;

import java.io.Serializable;

/**
 * Created by shaoxin on 18-4-9.
 * 课程资料的模型类
 */

public class CourseMaterial implements Serializable {
    private int course_material_id;         // 资料Id
    private int course_id;                  // 所属课程id
    private String course_material_name;    // 资料名称
    private String course_material_url;     // 资料下载路径
    private String course_material_size;    // 资料大小

    public CourseMaterial() {
    }

    public CourseMaterial(int course_material_id, int course_id, String course_material_name,
                          String course_material_url, String course_material_size) {
        this.course_material_id = course_material_id;
        this.course_id = course_id;
        this.course_material_name = course_material_name;
        this.course_material_url = course_material_url;
        this.course_material_size = course_material_size;
    }

    public int getCourse_material_id() {
        return course_material_id;
    }

    public void setCourse_material_id(int course_material_id) {
        this.course_material_id = course_material_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_material_name() {
        return course_material_name;
    }

    public void setCourse_material_name(String course_material_name) {
        this.course_material_name = course_material_name;
    }

    public String getCourse_material_url() {
        return course_material_url;
    }

    public void setCourse_material_url(String course_material_url) {
        this.course_material_url = course_material_url;
    }

    public String getCourse_material_size() {
        return course_material_size;
    }

    public void setCourse_material_size(String course_material_size) {
        this.course_material_size = course_material_size;
    }

    @Override
    public String toString() {
        return "CourseMaterial{" +
                "course_material_id=" + course_material_id +
                ", course_id=" + course_id +
                ", course_material_name='" + course_material_name + '\'' +
                ", course_material_url='" + course_material_url + '\'' +
                ", course_material_size='" + course_material_size + '\'' +
                '}';
    }
}
