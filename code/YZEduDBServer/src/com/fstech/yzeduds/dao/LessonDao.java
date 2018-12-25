package com.fstech.yzeduds.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fstech.yzeduds.mapper.LessonMapper;
import com.fstech.yzeduds.model.LessonBean;
import com.fstech.yzeduds.model.knowledge;

@Repository
public class LessonDao implements LessonMapper{
    
    @Autowired
    private LessonMapper lessonMapper;

    @Override
    public List<LessonBean> findListByCourseId(int courseId) {
        return lessonMapper.findListByCourseId(courseId);
    }

    @Override
    public List<String> findListByLessonId(int lessonId) {
        return lessonMapper.findListByLessonId(lessonId);
    }

    @Override
    public int addLesson(LessonBean lessonBean) {
        return lessonMapper.addLesson(lessonBean);
    }

    @Override
    public void addKnowledge(int lessonId, String content) {
        lessonMapper.addKnowledge(lessonId, content);
    }

}
