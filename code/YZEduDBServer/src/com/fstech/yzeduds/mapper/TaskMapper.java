package com.fstech.yzeduds.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fstech.yzeduds.model.LessonBean;

public interface TaskMapper {
    // 这门课程的实训任务列表
    public List<LessonBean> findListByCourseId(@Param("courseId") int courseId);
}
