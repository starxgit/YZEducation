package com.fstech.yzeduds.model;

/**
 * 题目的模型类
 * */
public class ExamBean {
    private int exam_id;    // 题目id
    private int lesson_id;  // 所属一节课id
    private int exam_type;  // 题目类型（0表示选择题，1表示填空题，2表示主观题）
    private String question;// 问题题目
    private String option1; // 选项1答案
    private String option2; // 选项2答案
    private String option3; // 选项3答案
    private String option4; // 选项4答案
    private String answer;  // 答案
    public ExamBean() {
        super();
    }
    public ExamBean(int exam_id, int lesson_id, int exam_type, String question,
            String option1, String option2, String option3, String option4,
            String answer) {
        super();
        this.exam_id = exam_id;
        this.lesson_id = lesson_id;
        this.exam_type = exam_type;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
    }
    public int getExam_id() {
        return exam_id;
    }
    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }
    public int getLesson_id() {
        return lesson_id;
    }
    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }
    public int getExam_type() {
        return exam_type;
    }
    public void setExam_type(int exam_type) {
        this.exam_type = exam_type;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getOption1() {
        return option1;
    }
    public void setOption1(String option1) {
        this.option1 = option1;
    }
    public String getOption2() {
        return option2;
    }
    public void setOption2(String option2) {
        this.option2 = option2;
    }
    public String getOption3() {
        return option3;
    }
    public void setOption3(String option3) {
        this.option3 = option3;
    }
    public String getOption4() {
        return option4;
    }
    public void setOption4(String option4) {
        this.option4 = option4;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    @Override
    public String toString() {
        return "ExamBean [exam_id=" + exam_id + ", lesson_id=" + lesson_id
                + ", exam_type=" + exam_type + ", question=" + question
                + ", option1=" + option1 + ", option2=" + option2
                + ", option3=" + option3 + ", option4=" + option4 + ", answer="
                + answer + "]";
    }
    
}
