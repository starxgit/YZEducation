package com.fstech.yzedusc.bean;

import java.io.Serializable;

/**
 * Created by shaoxin on 18-4-12.
 * 任务的模型类
 */

public class TaskBean implements Serializable {
    private int task_id;                // 任务id
    private String task_name;           // 任务名
    private int task_type;              // 任务类型
    private int course_id;              // 所属课程id
    private String task_start_time;     // 任务开始时间
    private String task_end_time;       // 任务结束时间
    private int task_state;             // 任务状态
    private String task_publish;        // 任务发布者（通过id找到对应的教师名）
    private String task_create_time;    // 任务创建时间（gmt_create）

    public TaskBean(int task_id, String task_name, int task_type, int course_id, String task_start_time,
                    String task_end_time, int task_state, String task_publish, String task_create_time) {
        this.task_id = task_id;
        this.task_name = task_name;
        this.task_type = task_type;
        this.course_id = course_id;
        this.task_start_time = task_start_time;
        this.task_end_time = task_end_time;
        this.task_state = task_state;
        this.task_publish = task_publish;
        this.task_create_time = task_create_time;
    }

    public TaskBean() {
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public int getTask_type() {
        return task_type;
    }

    public void setTask_type(int task_type) {
        this.task_type = task_type;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getTask_start_time() {
        return task_start_time;
    }

    public void setTask_start_time(String task_start_time) {
        this.task_start_time = task_start_time;
    }

    public String getTask_end_time() {
        return task_end_time;
    }

    public void setTask_end_time(String task_end_time) {
        this.task_end_time = task_end_time;
    }

    public int getTask_state() {
        return task_state;
    }

    public void setTask_state(int task_state) {
        this.task_state = task_state;
    }

    public String getTask_publish() {
        return task_publish;
    }

    public void setTask_publish(String task_publish) {
        this.task_publish = task_publish;
    }

    public String getTask_create_time() {
        return task_create_time;
    }

    public void setTask_create_time(String task_create_time) {
        this.task_create_time = task_create_time;
    }

    @Override
    public String toString() {
        return "TaskBean{" +
                "task_id=" + task_id +
                ", task_name='" + task_name + '\'' +
                ", task_type=" + task_type +
                ", course_id=" + course_id +
                ", task_start_time='" + task_start_time + '\'' +
                ", task_end_time='" + task_end_time + '\'' +
                ", task_state=" + task_state +
                ", task_publish='" + task_publish + '\'' +
                ", task_create_time='" + task_create_time + '\'' +
                '}';
    }
}
