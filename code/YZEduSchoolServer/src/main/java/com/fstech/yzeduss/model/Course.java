package com.fstech.yzeduss.model;

import lombok.Data;

/**
 * Created By shaoxin On 12/9/18
 */
@Data
public class Course {
    private int course_id;
    private String course_name;
    private String course_introduce;
    private String teacher_name;
    private int school_id;
    private int faculty_id;
    private int class_id;
    private String classification_name;
    private int course_sum_student;
    private int course_learn_student;
    private String course_cover;
    private int course_sum;
}