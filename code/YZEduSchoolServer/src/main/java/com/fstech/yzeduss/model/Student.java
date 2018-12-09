package com.fstech.yzeduss.model;

import lombok.Data;

/**
 * Created By shaoxin On 12/9/18
 */
@Data
public class Student {
    private String student_name;
    private String student_number;
    private int school_id;
    private int faculty_id;
    private int class_id;
    private String registe_time;

    private String faculty_name;
    private String class_name;
}