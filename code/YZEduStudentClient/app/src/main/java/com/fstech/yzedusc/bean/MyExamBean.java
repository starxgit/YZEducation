package com.fstech.yzedusc.bean;

/**
 * Created by shaoxin on 11/23/18.
 * 学生完成习题的模型类
 */

public class MyExamBean {
    private int my_exam_id; // 学生习题id
    private int user_id;    // 用户id
    private int exam_id;    // 对应题目id
    private String student_ans;   // 学生答案
    private int my_exam_state;  // 学生习题状态（0待审核，1正确，2错位，3不全对）
    private int lesson_id;  // 所属一节课id
    private int exam_type;  // 题目类型（0表示选择题，1表示填空题，2表示主观题）
    private String question;    // 问题题目
    private String option1; // 选项1答案
    private String option2; // 选项2答案
    private String option3; // 选项3答案
    private String option4; // 选项4答案
    private String answer;  // 答案
    public MyExamBean() {
        super();
    }
    public int getMy_exam_id() {
        return my_exam_id;
    }
    public void setMy_exam_id(int my_exam_id) {
        this.my_exam_id = my_exam_id;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public int getExam_id() {
        return exam_id;
    }
    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }
    public String getStudent_ans() {
        return student_ans;
    }
    public void setStudent_ans(String student_ans) {
        this.student_ans = student_ans;
    }
    public int getMy_exam_state() {
        return my_exam_state;
    }
    public void setMy_exam_state(int my_exam_state) {
        this.my_exam_state = my_exam_state;
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
        return "MyExamBean [my_exam_id=" + my_exam_id + ", user_id=" + user_id
                + ", exam_id=" + exam_id + ", student_ans=" + student_ans
                + ", my_exam_state=" + my_exam_state + ", lesson_id="
                + lesson_id + ", exam_type=" + exam_type + ", question="
                + question + ", option1=" + option1 + ", option2=" + option2
                + ", option3=" + option3 + ", option4=" + option4 + ", answer="
                + answer + "]";
    }

}
