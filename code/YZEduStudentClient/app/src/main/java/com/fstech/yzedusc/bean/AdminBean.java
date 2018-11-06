package com.fstech.yzedusc.bean;

/**
 * Created by shaoxin on 18-4-9.
 */

public class AdminBean {
    private int admin_id;
    private String admin_name;
    private String admin_account;

    public AdminBean() {
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_account() {
        return admin_account;
    }

    public void setAdmin_account(String admin_account) {
        this.admin_account = admin_account;
    }

    @Override
    public String toString() {
        return "AdminBean{" +
                "admin_id=" + admin_id +
                ", admin_name='" + admin_name + '\'' +
                ", admin_account='" + admin_account + '\'' +
                '}';
    }
}
