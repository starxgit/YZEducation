package com.fstech.yzeduss.model;

import lombok.Data;

/**
 * Created By shaoxin On 12/8/18
 */
@Data
public class SchoolAdmin {
    private int school_admin_id;
    private int school_id;
    private String school_admin_number;
    private String school_admin_name;
    private String school_admin_password;
    private String registe_time;
}