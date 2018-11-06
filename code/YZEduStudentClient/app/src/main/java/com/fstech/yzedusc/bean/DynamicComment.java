package com.fstech.yzedusc.bean;

import java.io.Serializable;

/**
 * Created by shaoxin on 18-4-9.
 * 学友圈动态评论的模型类
 */

public class DynamicComment implements Serializable {
    private int dynamic_comment_id;             // 学友圈评论id
    private String dynamic_comment_name;        // 学友圈评论者的对应学生名(dynamic_comment.author=student.user_id,再找学生名)
    private String dynamic_comment_avatar;      // 学友圈评论者的对应学生的头像(dynamic_comment.author=student.user_id,在找用户头像)
    private int circle_dynamic_id;              // 对应的学友圈动态id
    private int dynamic_reply_user;             // 要回复的用户对应的学生名(dynamic_comment.author=student.user_id,再找学生名),如果没有为null
    private String dynamic_comment_content;     // 评论的内容
    private String dynamic_comment_date;        // 发表时间(对应gmt_create)

    public DynamicComment() {
    }

    public DynamicComment(int dynamic_comment_id, String dynamic_comment_name, String dynamic_comment_avatar, int circle_dynamic_id, int dynamic_reply_user,
                          String dynamic_comment_content, String dynamic_comment_date) {
        this.dynamic_comment_id = dynamic_comment_id;
        this.dynamic_comment_name = dynamic_comment_name;
        this.dynamic_comment_avatar = dynamic_comment_avatar;
        this.circle_dynamic_id = circle_dynamic_id;
        this.dynamic_reply_user = dynamic_reply_user;
        this.dynamic_comment_content = dynamic_comment_content;
        this.dynamic_comment_date = dynamic_comment_date;
    }

    public int getDynamic_comment_id() {
        return dynamic_comment_id;
    }

    public void setDynamic_comment_id(int dynamic_comment_id) {
        this.dynamic_comment_id = dynamic_comment_id;
    }

    public String getDynamic_comment_name() {
        return dynamic_comment_name;
    }

    public void setDynamic_comment_name(String dynamic_comment_name) {
        this.dynamic_comment_name = dynamic_comment_name;
    }

    public String getDynamic_comment_avatar() {
        return dynamic_comment_avatar;
    }

    public void setDynamic_comment_avatar(String dynamic_comment_avatar) {
        this.dynamic_comment_avatar = dynamic_comment_avatar;
    }

    public int getCircle_dynamic_id() {
        return circle_dynamic_id;
    }

    public void setCircle_dynamic_id(int circle_dynamic_id) {
        this.circle_dynamic_id = circle_dynamic_id;
    }

    public int getDynamic_reply_user() {
        return dynamic_reply_user;
    }

    public void setDynamic_reply_user(int dynamic_reply_user) {
        this.dynamic_reply_user = dynamic_reply_user;
    }

    public String getDynamic_comment_content() {
        return dynamic_comment_content;
    }

    public void setDynamic_comment_content(String dynamic_comment_content) {
        this.dynamic_comment_content = dynamic_comment_content;
    }

    public String getDynamic_comment_date() {
        return dynamic_comment_date;
    }

    public void setDynamic_comment_date(String dynamic_comment_date) {
        this.dynamic_comment_date = dynamic_comment_date;
    }

    @Override
    public String toString() {
        return "DynamicComment{" +
                "dynamic_comment_id=" + dynamic_comment_id +
                ", dynamic_comment_name='" + dynamic_comment_name + '\'' +
                ", dynamic_comment_avatar='" + dynamic_comment_avatar + '\'' +
                ", circle_dynamic_id=" + circle_dynamic_id +
                ", dynamic_reply_user=" + dynamic_reply_user +
                ", dynamic_comment_content='" + dynamic_comment_content + '\'' +
                ", dynamic_comment_date='" + dynamic_comment_date + '\'' +
                '}';
    }
}
