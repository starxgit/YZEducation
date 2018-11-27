package com.fstech.yzeduds.model;

/**
 * Created By shaoxin On 2018-11-27 
 * 课程交流回答的模型类
 * */
public class CommunicationCommentBean {
    private int communication_comment_id;   // 回复id
    private int communication_id;   //课程交流id
    private int communication_comment_user; // 写评论的人id
    private int communication_reply;    // 想要回复的人id
    private String communication_comment_content;  // 评论内容
    private String communication_comment_time;  // 评论时间
    private String author_name; // 作者昵称
    private String author_avatar; // 作者头像
    private String reply_name; // 回复的人昵称
    private int isMy; // 是否为我发的
    public CommunicationCommentBean() {
        super();
    }
    public int getCommunication_comment_id() {
        return communication_comment_id;
    }
    public void setCommunication_comment_id(int communication_comment_id) {
        this.communication_comment_id = communication_comment_id;
    }
    public int getCommunication_id() {
        return communication_id;
    }
    public void setCommunication_id(int communication_id) {
        this.communication_id = communication_id;
    }
    public int getCommunication_comment_user() {
        return communication_comment_user;
    }
    public void setCommunication_comment_user(int communication_comment_user) {
        this.communication_comment_user = communication_comment_user;
    }
    public String getAuthor_name() {
        return author_name;
    }
    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }
    public String getAuthor_avatar() {
        return author_avatar;
    }
    public void setAuthor_avatar(String author_avatar) {
        this.author_avatar = author_avatar;
    }
    public int getCommunication_reply() {
        return communication_reply;
    }
    public void setCommunication_reply(int communication_reply) {
        this.communication_reply = communication_reply;
    }
    public String getReply_name() {
        return reply_name;
    }
    public void setReply_name(String reply_name) {
        this.reply_name = reply_name;
    }
    public String getCommunication_comment_content() {
        return communication_comment_content;
    }
    public void setCommunication_comment_content(
            String communication_comment_content) {
        this.communication_comment_content = communication_comment_content;
    }
    public String getCommunication_comment_time() {
        return communication_comment_time;
    }
    public void setCommunication_comment_time(String communication_comment_time) {
        this.communication_comment_time = communication_comment_time;
    }
    public int getIsMy() {
        return isMy;
    }
    public void setIsMy(int isMy) {
        this.isMy = isMy;
    }
    @Override
    public String toString() {
        return "CommunicationCommentBean [communication_comment_id="
                + communication_comment_id + ", communication_id="
                + communication_id + ", communication_comment_user="
                + communication_comment_user + ", author_name=" + author_name
                + ", author_avatar=" + author_avatar + ", communication_reply="
                + communication_reply + ", reply_name=" + reply_name
                + ", communication_comment_content="
                + communication_comment_content
                + ", communication_comment_time=" + communication_comment_time
                + ", isMy=" + isMy + "]";
    }
}
