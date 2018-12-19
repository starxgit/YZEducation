package com.fstech.yzedutc.bean;

import java.io.Serializable;

public class CommunicationBean implements Serializable{
    private int communication_id; // 课程交流Id
    private int lesson_id; // 一节课Id
    private int communication_author;// 作者Id
    private String communication_content; // 评论内容
    private String communication_time; // 发表时间
    private int comment_num; // 评论数量
    private String avatar; // 作者头像
    private String nick_name; // 用户名称
    private int isMy; // 是否为我发的

    public CommunicationBean() {
        super();
    }

    public int getCommunication_id() {
        return communication_id;
    }

    public void setCommunication_id(int communication_id) {
        this.communication_id = communication_id;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public int getCommunication_author() {
        return communication_author;
    }

    public void setCommunication_author(int communication_author) {
        this.communication_author = communication_author;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getCommunication_content() {
        return communication_content;
    }

    public void setCommunication_content(String communication_content) {
        this.communication_content = communication_content;
    }

    public String getCommunication_time() {
        return communication_time;
    }

    public void setCommunication_time(String communication_time) {
        this.communication_time = communication_time;
    }

    public int getComment_num() {
        return comment_num;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }

    public int getIsMy() {
        return isMy;
    }

    public void setIsMy(int isMy) {
        this.isMy = isMy;
    }

    @Override
    public String toString() {
        return "CommunicationBean [communication_id=" + communication_id
                + ", lesson_id=" + lesson_id + ", communication_author="
                + communication_author + ", avatar=" + avatar + ", nick_name="
                + nick_name + ", communication_content="
                + communication_content + ", communication_time="
                + communication_time + ", comment_num=" + comment_num
                + ", isMy=" + isMy + "]";
    }

}
