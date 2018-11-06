package com.fstech.yzedutc.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shaoxin on 18-4-9.
 * 学友圈动态的模型类
 */

public class CircleDynamic implements Serializable {
    private int circle_dynamic_id;                  // 学友圈的id
    private int school_id;                          // 所属院校id
    private String circle_dynamic_author_name;      // 发言者学生名(通过author找到student表对应user_id的student_name)
    private String circle_dynamic_author_avatar;    // 发言者头像(通过author找到user表对应user_id的user_avatar)
    private String circle_dynamic_content;          // 发言内容
    private List<DynamicImage> list_images;         // 属于这条学友圈的图片列表 可以是空列表{},但不能为null
    private List<DynamicComment> list_comments;     // 属于这条学友圈的评论列表 可以是空列表{},但不能为null
    private List<DynamicPraise> list_praises;       // 属于这条学友圈的点赞列表 可以是空列表{},但不能为null
    private String dynamic_date;                    // 发布时间,(对应gmt_create)

    public CircleDynamic() {
    }

    public CircleDynamic(int circle_dynamic_id, int school_id, String circle_dynamic_author_name,
                         String circle_dynamic_author_avatar, String circle_dynamic_content,
                         List<DynamicImage> list_images, List<DynamicComment> list_comments,
                         List<DynamicPraise> list_praises, String dynamic_date) {
        this.circle_dynamic_id = circle_dynamic_id;
        this.school_id = school_id;
        this.circle_dynamic_author_name = circle_dynamic_author_name;
        this.circle_dynamic_author_avatar = circle_dynamic_author_avatar;
        this.circle_dynamic_content = circle_dynamic_content;
        this.list_images = list_images;
        this.list_comments = list_comments;
        this.list_praises = list_praises;
        this.dynamic_date = dynamic_date;
    }

    public int getCircle_dynamic_id() {
        return circle_dynamic_id;
    }

    public void setCircle_dynamic_id(int circle_dynamic_id) {
        this.circle_dynamic_id = circle_dynamic_id;
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public String getCircle_dynamic_author_name() {
        return circle_dynamic_author_name;
    }

    public void setCircle_dynamic_author_name(String circle_dynamic_author_name) {
        this.circle_dynamic_author_name = circle_dynamic_author_name;
    }

    public String getCircle_dynamic_author_avatar() {
        return circle_dynamic_author_avatar;
    }

    public void setCircle_dynamic_author_avatar(String circle_dynamic_author_avatar) {
        this.circle_dynamic_author_avatar = circle_dynamic_author_avatar;
    }

    public String getCircle_dynamic_content() {
        return circle_dynamic_content;
    }

    public void setCircle_dynamic_content(String circle_dynamic_content) {
        this.circle_dynamic_content = circle_dynamic_content;
    }

    public List<DynamicImage> getList_images() {
        return list_images;
    }

    public void setList_images(List<DynamicImage> list_images) {
        this.list_images = list_images;
    }

    public List<DynamicComment> getList_comments() {
        return list_comments;
    }

    public void setList_comments(List<DynamicComment> list_comments) {
        this.list_comments = list_comments;
    }

    public List<DynamicPraise> getList_praises() {
        return list_praises;
    }

    public void setList_praises(List<DynamicPraise> list_praises) {
        this.list_praises = list_praises;
    }

    public String getDynamic_date() {
        return dynamic_date;
    }

    public void setDynamic_date(String dynamic_date) {
        this.dynamic_date = dynamic_date;
    }

    @Override
    public String toString() {
        return "CircleDynamic{" +
                "circle_dynamic_id=" + circle_dynamic_id +
                ", school_id=" + school_id +
                ", circle_dynamic_author_name='" + circle_dynamic_author_name + '\'' +
                ", circle_dynamic_author_avatar='" + circle_dynamic_author_avatar + '\'' +
                ", circle_dynamic_content='" + circle_dynamic_content + '\'' +
                ", list_images=" + list_images +
                ", list_comments=" + list_comments +
                ", list_praises=" + list_praises +
                ", dynamic_date='" + dynamic_date + '\'' +
                '}';
    }
}
