package com.fstech.yzeduds.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fstech.yzeduds.model.LessonBean;
import com.fstech.yzeduds.model.knowledge;

public interface LessonMapper {
    // 属于这门课程的章节列表
    public List<LessonBean> findListByCourseId(@Param("courseId") int courseId);

    // 属于这节课的知识点列表
    public List<String> findListByLessonId(@Param("lessonId") int lessonId);

    // 添加一节课
    public int addLesson(LessonBean lessonBean);
    
    // 添加知识点
    public void addKnowledge(@Param("lessonId") int lessonId,@Param("content")String content);
}
