package com.fstech.yzedusc.bean;

import java.io.Serializable;

/**
 * Created by shaoxin on 18-3-26.
 * 存放直播间对象的类
 */

public class LiveRoomBean implements Serializable {
    private int live_room_id;           // 直播间id
    private String teacher_name;        // 教师姓名(通过teacher_id找到)
    private String live_room_name;      // 直播间名
    private String live_room_number;    // 直播间号码
    private String live_room_image;     // 直播间封面图片路径
    private int live_room_state;        // 直播间状态

    public LiveRoomBean() {
    }

    public LiveRoomBean(int live_room_id, String teacher_name, String live_room_name,
                        String live_room_number, String live_room_image, int live_room_state) {
        this.live_room_id = live_room_id;
        this.teacher_name = teacher_name;
        this.live_room_name = live_room_name;
        this.live_room_number = live_room_number;
        this.live_room_image = live_room_image;
        this.live_room_state = live_room_state;
    }

    public int getLive_room_id() {
        return live_room_id;
    }

    public void setLive_room_id(int live_room_id) {
        this.live_room_id = live_room_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getLive_room_name() {
        return live_room_name;
    }

    public void setLive_room_name(String live_room_name) {
        this.live_room_name = live_room_name;
    }

    public String getLive_room_number() {
        return live_room_number;
    }

    public void setLive_room_number(String live_room_number) {
        this.live_room_number = live_room_number;
    }

    public String getLive_room_image() {
        return live_room_image;
    }

    public void setLive_room_image(String live_room_image) {
        this.live_room_image = live_room_image;
    }

    public int getLive_room_state() {
        return live_room_state;
    }

    public void setLive_room_state(int live_room_state) {
        this.live_room_state = live_room_state;
    }

    @Override
    public String toString() {
        return "LiveRoomBean{" +
                "live_room_id=" + live_room_id +
                ", teacher_name='" + teacher_name + '\'' +
                ", live_room_name='" + live_room_name + '\'' +
                ", live_room_number='" + live_room_number + '\'' +
                ", live_room_image='" + live_room_image + '\'' +
                ", live_room_state=" + live_room_state +
                '}';
    }
}
