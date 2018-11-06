package com.fstech.yzedusc.bean;

import java.io.Serializable;

/**
 * Created by shaoxin on 18-4-13.
 * 聊天的列表item
 */

public class ChatBean implements Serializable {
    private int chat_id;
    private String user_in;
    private String user_out;
    private String content_in;
    private String content_out;
    private String datetime;

    public ChatBean() {
    }

    public ChatBean(int chat_id, String user_in, String content_in, String user_out, String content_out, String datetime) {
        this.chat_id = chat_id;
        this.user_in = user_in;
        this.user_out = user_out;
        this.content_in = content_in;
        this.content_out = content_out;
        this.datetime = datetime;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public String getUser_in() {
        return user_in;
    }

    public void setUser_in(String user_in) {
        this.user_in = user_in;
    }

    public String getUser_out() {
        return user_out;
    }

    public void setUser_out(String user_out) {
        this.user_out = user_out;
    }

    public String getContent_in() {
        return content_in;
    }

    public void setContent_in(String content_in) {
        this.content_in = content_in;
    }

    public String getContent_out() {
        return content_out;
    }

    public void setContent_out(String content_out) {
        this.content_out = content_out;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "ChatBean{" +
                "chat_id=" + chat_id +
                ", user_in='" + user_in + '\'' +
                ", user_out='" + user_out + '\'' +
                ", content_in='" + content_in + '\'' +
                ", content_out='" + content_out + '\'' +
                ", datetime='" + datetime + '\'' +
                '}';
    }
}
