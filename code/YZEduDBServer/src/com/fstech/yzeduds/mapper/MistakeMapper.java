package com.fstech.yzeduds.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fstech.yzeduds.model.MistakeBean;
import com.fstech.yzeduds.model.MyExamBean;

public interface MistakeMapper {

    // 查看我的错题列表
    public List<MistakeBean> findMistakeList(@Param("user_id") int user_id,
            @Param("course_id") int course_id);
    
    // 添加到我的错题
    public void addToMyMistake(@Param("user_id") int user_id,
            @Param("my_exam_id") int my_exam_id,
            @Param("course_id") int course_id);

    // 从我的错题移除
    public void removeFromMyMistake(@Param("mistake_id") int mistake_id);

    // 查看我的错题详情(返回题目的形式)
    public MyExamBean MistakeDetail(@Param("user_id") int user_id,
            @Param("my_exam_id") int my_exam_id);

    // 这题错题是否存在(每个人对每一题只能添加一次)
    public int mistakeIsExist(@Param("user_id") int user_id,
            @Param("my_exam_id") int my_exam_id);
}
