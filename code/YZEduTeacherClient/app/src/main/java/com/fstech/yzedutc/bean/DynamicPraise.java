package com.fstech.yzedutc.bean;

import java.io.Serializable;

/**
 * Created by shaoxin on 18-4-9.
 * 学友圈点赞的模型类
 */

public class DynamicPraise implements Serializable {
    private int dynamic_praise_id;          // 点赞的id
    private int dynamic_praise_user_name;   // 点赞用户的用户名(dynamic_praise.user=student.user_id)
    private int circle_dynamic_id;          // 所属学友圈id

    public DynamicPraise() {
    }

    public DynamicPraise(int dynamic_praise_id, int dynamic_praise_user_name, int circle_dynamic_id) {
        this.dynamic_praise_id = dynamic_praise_id;
        this.dynamic_praise_user_name = dynamic_praise_user_name;
        this.circle_dynamic_id = circle_dynamic_id;
    }

    public int getDynamic_praise_id() {
        return dynamic_praise_id;
    }

    public void setDynamic_praise_id(int dynamic_praise_id) {
        this.dynamic_praise_id = dynamic_praise_id;
    }

    public int getDynamic_praise_user_name() {
        return dynamic_praise_user_name;
    }

    public void setDynamic_praise_user_name(int dynamic_praise_user_name) {
        this.dynamic_praise_user_name = dynamic_praise_user_name;
    }

    public int getCircle_dynamic_id() {
        return circle_dynamic_id;
    }

    public void setCircle_dynamic_id(int circle_dynamic_id) {
        this.circle_dynamic_id = circle_dynamic_id;
    }

    @Override
    public String toString() {
        return "DynamicPraise{" +
                "dynamic_praise_id=" + dynamic_praise_id +
                ", dynamic_praise_user_name=" + dynamic_praise_user_name +
                ", circle_dynamic_id=" + circle_dynamic_id +
                '}';
    }
}
