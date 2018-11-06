package com.fstech.yzedusc.bean;

import java.io.Serializable;

/**
 * Created by shaoxin on 18-5-31.
 * 记录错题的模型类
 */

public class MistakeBean implements Serializable{
    private int misktake_id;
    private String exam_item_question; // 题目问题
    private String exam_item_option1; // 如果是选择题，则为选项1，否则为null
    private String exam_item_option2; // 选项2
    private String exam_item_option3; // 选项3
    private String exam_item_option4; // 选项4
    private String exam_item_answer; // 客观题的答案

    private String student_answer; // 学生选择的答案
    private String mistake_note; // 错题笔记

    public MistakeBean() {
    }

    public MistakeBean(String exam_item_question, String exam_item_option1, String exam_item_option2,
                       String exam_item_option3, String exam_item_option4, String exam_item_answer, String student_answer) {
        this.exam_item_question = exam_item_question;
        this.exam_item_option1 = exam_item_option1;
        this.exam_item_option2 = exam_item_option2;
        this.exam_item_option3 = exam_item_option3;
        this.exam_item_option4 = exam_item_option4;
        this.exam_item_answer = exam_item_answer;
        this.student_answer = student_answer;
    }

    public int getMisktake_id() {
        return misktake_id;
    }

    public void setMisktake_id(int misktake_id) {
        this.misktake_id = misktake_id;
    }

    public String getExam_item_question() {
        return exam_item_question;
    }

    public void setExam_item_question(String exam_item_question) {
        this.exam_item_question = exam_item_question;
    }

    public String getExam_item_option1() {
        return exam_item_option1;
    }

    public void setExam_item_option1(String exam_item_option1) {
        this.exam_item_option1 = exam_item_option1;
    }

    public String getExam_item_option2() {
        return exam_item_option2;
    }

    public void setExam_item_option2(String exam_item_option2) {
        this.exam_item_option2 = exam_item_option2;
    }

    public String getExam_item_option3() {
        return exam_item_option3;
    }

    public void setExam_item_option3(String exam_item_option3) {
        this.exam_item_option3 = exam_item_option3;
    }

    public String getExam_item_option4() {
        return exam_item_option4;
    }

    public void setExam_item_option4(String exam_item_option4) {
        this.exam_item_option4 = exam_item_option4;
    }

    public String getExam_item_answer() {
        return exam_item_answer;
    }

    public void setExam_item_answer(String exam_item_answer) {
        this.exam_item_answer = exam_item_answer;
    }

    public String getStudent_answer() {
        return student_answer;
    }

    public void setStudent_answer(String student_answer) {
        this.student_answer = student_answer;
    }

    public String getMistake_note() {
        return mistake_note;
    }

    public void setMistake_note(String mistake_note) {
        this.mistake_note = mistake_note;
    }

    @Override
    public String toString() {
        return "MistakeBean{" +
                "misktake_id=" + misktake_id +
                ", exam_item_question='" + exam_item_question + '\'' +
                ", exam_item_option1='" + exam_item_option1 + '\'' +
                ", exam_item_option2='" + exam_item_option2 + '\'' +
                ", exam_item_option3='" + exam_item_option3 + '\'' +
                ", exam_item_option4='" + exam_item_option4 + '\'' +
                ", exam_item_answer='" + exam_item_answer + '\'' +
                ", student_answer='" + student_answer + '\'' +
                ", mistake_note='" + mistake_note + '\'' +
                '}';
    }
}
