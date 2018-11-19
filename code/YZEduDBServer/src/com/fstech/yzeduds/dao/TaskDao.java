package com.fstech.yzeduds.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fstech.yzeduds.mapper.TaskMapper;
import com.fstech.yzeduds.model.LessonBean;

@Repository
public class TaskDao implements TaskMapper{
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<LessonBean> findListByCourseId(int courseId) {
        return taskMapper.findListByCourseId(courseId);
    }
}
