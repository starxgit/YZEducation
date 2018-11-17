package com.fstech.yzeduds.model;

/**
 * Created by shaoxin on 18-3-28.
 * 公告数据的模型类
 */
public class AnnouncementBean {
    private int announcement_id;            // 公告id
    private String announcement_title;      // 公告标题
    private String announcement_content;    // 公告内容
    private int announcement_stick;         // 是否置顶
    private String announcement_date;       // 公告日期
    
    public AnnouncementBean() {
    }

    public AnnouncementBean(int announcement_id, String announcement_title, String announcement_content,
                            int announcement_stick, String announcement_date) {
        this.announcement_id = announcement_id;
        this.announcement_title = announcement_title;
        this.announcement_content = announcement_content;
        this.announcement_stick = announcement_stick;
        this.announcement_date = announcement_date;
    }

    public int getAnnouncement_id() {
        return announcement_id;
    }

    public void setAnnouncement_id(int announcement_id) {
        this.announcement_id = announcement_id;
    }

    public String getAnnouncement_title() {
        return announcement_title;
    }

    public void setAnnouncement_title(String announcement_title) {
        this.announcement_title = announcement_title;
    }

    public String getAnnouncement_content() {
        return announcement_content;
    }

    public void setAnnouncement_content(String announcement_content) {
        this.announcement_content = announcement_content;
    }

    public int getAnnouncement_stick() {
        return announcement_stick;
    }

    public void setAnnouncement_stick(int announcement_stick) {
        this.announcement_stick = announcement_stick;
    }

    public String getAnnouncement_date() {
        return announcement_date;
    }

    public void setAnnouncement_date(String announcement_date) {
        this.announcement_date = announcement_date;
    }

    @Override
    public String toString() {
        return "AnnouncementBean{" +
                "announcement_id=" + announcement_id +
                ", announcement_title='" + announcement_title + '\'' +
                ", announcement_content='" + announcement_content + '\'' +
                ", announcement_stick='" + announcement_stick + '\'' +
                ", announcement_date='" + announcement_date + '\'' +
                '}';
    }
    
    
}
