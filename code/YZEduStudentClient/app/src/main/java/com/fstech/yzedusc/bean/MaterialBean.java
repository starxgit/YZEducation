package com.fstech.yzedusc.bean;
/**
 * 课程资料相关的模型类
 * @author shaoxin
 */
public class MaterialBean {
    private int material_id;
    private int course_id;
    private String material_name;
    private String material_url;
    private String material_size;
    private String material_time;
    
    
    public MaterialBean() {
        super();
    }

    public MaterialBean(int material_id, int course_id, String material_name,
            String material_url, String material_size, String material_time) {
        super();
        this.material_id = material_id;
        this.course_id = course_id;
        this.material_name = material_name;
        this.material_url = material_url;
        this.material_size = material_size;
        this.material_time = material_time;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getMaterial_url() {
        return material_url;
    }

    public void setMaterial_url(String material_url) {
        this.material_url = material_url;
    }

    public String getMaterial_size() {
        return material_size;
    }

    public void setMaterial_size(String material_size) {
        this.material_size = material_size;
    }

    public String getMaterial_time() {
        return material_time;
    }

    public void setMaterial_time(String material_time) {
        this.material_time = material_time;
    }
    
}
