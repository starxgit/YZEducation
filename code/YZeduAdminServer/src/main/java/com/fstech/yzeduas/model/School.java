package com.fstech.yzeduas.model;

import lombok.Data;

/**
 * Created By shaoxin On 12/5/18
 */
@Data
public class School {
    public static final String SCHOOL_TYPE_LIST[] = {"", "中小学", "高校", "培训机构"};
    private int school_id;
    private String school_name;
    private String school_badge;
    private String school_introduce;
    private String school_city;
    private String school_province;
    private int school_type;    // 1中小学，2高校，3培训机构
}