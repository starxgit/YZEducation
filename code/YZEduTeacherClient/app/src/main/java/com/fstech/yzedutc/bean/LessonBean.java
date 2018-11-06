package com.fstech.yzedutc.bean;

import java.io.Serializable;

/**
 * Created by shaoxin on 18-3-29.
 */

public class LessonBean implements Serializable{
    private int lesson_id;              // 一节课id
    private int course_id;              // 所属课程id
    private String lesson_title;        // 一节课标题
    private String lesson_video_url;    // 视频路径

    public LessonBean() {
    }

    public LessonBean(int lesson_id, int course_id, String lesson_title, String lesson_video_url) {
        this.lesson_id = lesson_id;
        this.course_id = course_id;
        this.lesson_title = lesson_title;
        this.lesson_video_url = lesson_video_url;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getLesson_title() {
        return lesson_title;
    }

    public void setLesson_title(String lesson_title) {
        this.lesson_title = lesson_title;
    }

    public String getLesson_video_url() {
        return lesson_video_url;
    }

    public void setLesson_video_url(String lesson_video_url) {
        this.lesson_video_url = lesson_video_url;
    }

    @Override
    public String toString() {
        return "LessonBean{" +
                "lesson_id=" + lesson_id +
                ", course_id=" + course_id +
                ", lesson_title='" + lesson_title + '\'' +
                ", lesson_video_url='" + lesson_video_url + '\'' +
                '}';
    }
}
