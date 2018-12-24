package com.fstech.yzeduds.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fstech.yzeduds.mapper.ExamMapper;
import com.fstech.yzeduds.model.ExamBean;
import com.fstech.yzeduds.model.MyExamBean;

@Repository
public class ExamDao implements ExamMapper {
    @Autowired
    private ExamMapper examMapper;

    @Override
    public List<MyExamBean> findExamByLessonId(int lesson_id) {
        return examMapper.findExamByLessonId(lesson_id);
    }

    @Override
    public int isDoExamByLesson(int lesson_id, int user_id) {
        return examMapper.isDoExamByLesson(lesson_id, user_id);
    }

    @Override
    public List<MyExamBean> studentExamByLessonId(int lesson_id, int user_id) {
        return examMapper.studentExamByLessonId(lesson_id, user_id);
    }

    @Override
    public void addMyExam(int user_id, int exam_id, String student_ans) {
        examMapper.addMyExam(user_id, exam_id, student_ans);
    }

    @Override
    public List<MyExamBean> findMyExamByLessonId(int lesson_id) {
        return examMapper.findMyExamByLessonId(lesson_id);
    }

    @Override
    public int updateCheckMyExam(int my_exam_id, int my_exam_state) {
        return examMapper.updateCheckMyExam(my_exam_id, my_exam_state);
    }

    @Override
    public int addExam(ExamBean examBean) {
        return examMapper.addExam(examBean);
    }

    @Override
    public int trueStudentNum(int exam_id) {
        return examMapper.trueStudentNum(exam_id);
    }

    @Override
    public int finishStudentNum(int exam_id) {
        return examMapper.finishStudentNum(exam_id);
    }
}
