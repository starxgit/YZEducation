package com.fstech.yzeduds.model;

public class MistakeBean {
    private int mistake_id; // 错题id
    private int my_exam_id; // 习题id
    private int exam_type; // 习题类型
    private String note; // 错题笔记
    private String question;// 问题
    private String mistake_time;// 添加时间

    public MistakeBean() {
        super();
    }

    public MistakeBean(int mistake_id, int my_exam_id, int exam_type,
            String note, String question, String mistake_time) {
        super();
        this.mistake_id = mistake_id;
        this.my_exam_id = my_exam_id;
        this.exam_type = exam_type;
        this.note = note;
        this.question = question;
        this.mistake_time = mistake_time;
    }

    public int getMistake_id() {
        return mistake_id;
    }

    public void setMistake_id(int mistake_id) {
        this.mistake_id = mistake_id;
    }

    public int getMy_exam_id() {
        return my_exam_id;
    }

    public void setMy_exam_id(int my_exam_id) {
        this.my_exam_id = my_exam_id;
    }

    public int getExam_type() {
        return exam_type;
    }

    public void setExam_type(int exam_type) {
        this.exam_type = exam_type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getMistake_time() {
        return mistake_time;
    }

    public void setMistake_time(String mistake_time) {
        this.mistake_time = mistake_time;
    }

    @Override
    public String toString() {
        return "MistakeBean [mistake_id=" + mistake_id + ", my_exam_id="
                + my_exam_id + ", exam_type=" + exam_type + ", note=" + note
                + ", question=" + question + ", mistake_time=" + mistake_time
                + "]";
    }

}
