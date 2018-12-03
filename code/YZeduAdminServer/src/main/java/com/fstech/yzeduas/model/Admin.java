package com.fstech.yzeduas.model;

import lombok.Data;

@Data
public class Admin {
    private int admin_id;
    private String admin_name;
    private String admin_account;
    private String admin_password;
    private String register_time;
}
