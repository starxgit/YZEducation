package com.fstech.yzeduds.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fstech.yzeduds.model.CommunicationBean;
import com.fstech.yzeduds.model.CommunicationCommentBean;

public interface CommunicationMapper {
    // 一节课的课程交流列表
    public List<CommunicationBean> findListByLessonId(
            @Param("lesson_id") int lesson_id);

    // 添加新的课程交流
    public int addCommunication(@Param("lesson_id") int lesson_id,
            @Param("user_id") int user_id,
            @Param("communication_content") String communication_content);

    // 这个课程交流的评论列表
    public List<CommunicationCommentBean> findByCommunicationId(
            @Param("communication_id") int communication_id);

    // 添加课程交流评论
    public int addComment(@Param("communication_id") int communication_id,
            @Param("user_id") int user_id, @Param("reply") int reply,
            @Param("comment_content") String comment_content);

    // 删除我发的课程交流
    public int delCommunication(@Param("communication_id") int communication_id);

    // 删除我的评论
    public int delComment(@Param("comment_id") int comment_id);

    // 是否为我发的课程交流
    public int isMyCommunication(
            @Param("communication_id") int communication_id,
            @Param("user_id") int user_id);

    // 是否为我发的评论
    public int isMyComment(@Param("comment_id") int comment_id,
            @Param("user_id") int user_id);

    // 增加一条交流的评论数量
    public void increCommentNum(@Param("communication_id") int communication_id);

    // 减少一条交流的评论数量
    public void decreCommentNum(@Param("comment_id") int comment_id);
    
    // 所有敏感词列表
    public  List<String> sensitiveWordList();
}
