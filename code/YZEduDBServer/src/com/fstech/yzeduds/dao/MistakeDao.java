package com.fstech.yzeduds.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fstech.yzeduds.mapper.MistakeMapper;
import com.fstech.yzeduds.model.MistakeBean;
import com.fstech.yzeduds.model.MyExamBean;

@Repository
public class MistakeDao implements MistakeMapper {
    @Autowired
    private MistakeMapper mistakeMapper;

    @Override
    public List<MistakeBean> findMistakeList(int user_id, int course_id) {
        return mistakeMapper.findMistakeList(user_id, course_id);
    }

    @Override
    public void addToMyMistake(int user_id, int my_exam_id, int course_id) {
        mistakeMapper.addToMyMistake(user_id, my_exam_id, course_id);
    }

    @Override
    public void removeFromMyMistake(int mistake_id) {
        mistakeMapper.removeFromMyMistake(mistake_id);
    }

    @Override
    public MyExamBean MistakeDetail(int user_id, int my_exam_id) {
        return mistakeMapper.MistakeDetail(user_id, my_exam_id);
    }

    @Override
    public int mistakeIsExist(int user_id, int my_exam_id) {
        return mistakeMapper.mistakeIsExist(user_id, my_exam_id);
    }

}
