package com.fstech.yzeduds.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fstech.yzeduds.mapper.CommunicationMapper;
import com.fstech.yzeduds.model.CommunicationBean;
import com.fstech.yzeduds.model.CommunicationCommentBean;

@Repository
public class CommunicationDao implements CommunicationMapper {
    @Autowired
    private CommunicationMapper communicationMapper;

    @Override
    public List<CommunicationBean> findListByLessonId(int lesson_id) {
        return communicationMapper.findListByLessonId(lesson_id);
    }

    @Override
    public int addCommunication(int lesson_id, int user_id,
            String communication_content) {
        return communicationMapper.addCommunication(lesson_id, user_id,
                communication_content);
    }

    @Override
    public List<CommunicationCommentBean> findByCommunicationId(
            int communication_id) {
        return communicationMapper.findByCommunicationId(communication_id);
    }

    @Override
    public int addComment(int communication_id, int user_id, int reply,
            String comment_content) {
        return communicationMapper.addComment(communication_id, user_id, reply,
                comment_content);
    }

    @Override
    public int delCommunication(int communication_id) {
        return communicationMapper.delCommunication(communication_id);
    }

    @Override
    public int delComment(int comment_id) {
        return communicationMapper.delComment(comment_id);
    }

    @Override
    public int isMyCommunication(int communication_id, int user_id) {
        return communicationMapper.isMyCommunication(communication_id, user_id);
    }

    @Override
    public int isMyComment(int comment_id, int user_id) {
        return communicationMapper.isMyComment(comment_id, user_id);
    }

    @Override
    public void increCommentNum(int communication_id) {
        communicationMapper.increCommentNum(communication_id);
    }

    @Override
    public void decreCommentNum(int communication_id) {
        communicationMapper.decreCommentNum(communication_id);
    }

    @Override
    public List<String> sensitiveWordList() {
        return communicationMapper.sensitiveWordList();
    }

}
