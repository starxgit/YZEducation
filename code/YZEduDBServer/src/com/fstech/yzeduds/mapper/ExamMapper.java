package com.fstech.yzeduds.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fstech.yzeduds.model.ExamBean;
import com.fstech.yzeduds.model.MyExamBean;

public interface ExamMapper {
    // 获取这节课的题目列表
    public List<MyExamBean> findExamByLessonId(@Param("lesson_id") int lesson_id);

    // 学生是否做过这节课的题目
    public int isDoExamByLesson(@Param("lesson_id") int lesson_id,
            @Param("user_id") int user_id);

    // 学生完成题目情况
    public List<MyExamBean> studentExamByLessonId(
            @Param("lesson_id") int lesson_id, @Param("user_id") int user_id);

    // 提交作业
    public void addMyExam(@Param("user_id") int user_id,
            @Param("exam_id") int exam_id,
            @Param("student_ans") String student_ans);

    // 这节课的所有待批改学生习题列表
    public List<MyExamBean> findMyExamByLessonId(
            @Param("lesson_id") int lesson_id);

    // 批改作业
    public int updateCheckMyExam(@Param("my_exam_id") int my_exam_id,
            @Param("my_exam_state") int my_exam_state);

    // 添加习题
    public int addExam(ExamBean examBean);

    // 本题正确学生人数
    public int trueStudentNum(@Param("exam_id") int exam_id);

    // 本题完成学生人数
    public int finishStudentNum(@Param("exam_id") int exam_id);

}
