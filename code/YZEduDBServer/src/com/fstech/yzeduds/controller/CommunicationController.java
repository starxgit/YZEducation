package com.fstech.yzeduds.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fstech.yzeduds.dao.CommunicationDao;
import com.fstech.yzeduds.dao.UserDao;
import com.fstech.yzeduds.model.CommunicationBean;
import com.fstech.yzeduds.model.CommunicationCommentBean;
import com.fstech.yzeduds.model.UserBean;
import com.fstech.yzeduds.util.ErrorCode;
import com.fstech.yzeduds.util.ResponseUtil;
import com.fstech.yzeduds.util.TokenUtil;

@Controller
@RequestMapping("/communication")
public class CommunicationController {
    @Autowired
    private CommunicationDao communicationDao;
    @Autowired
    private UserDao userDao;

    /**
     * 这节课的讨论列表
     * */
    @RequestMapping(value = "communicationList", method = RequestMethod.GET)
    public void communicationList(@RequestParam String token,
            @RequestParam int lesson_id, HttpServletResponse response) {
        int user_id = TokenUtil.decodeUserId(token);
        List<CommunicationBean> communicationList = communicationDao
                .findListByLessonId(lesson_id);
        for (CommunicationBean cb : communicationList) {
            int cbId = cb.getCommunication_id();
            // 设置是否为我的
            if (communicationDao.isMyCommunication(cbId, user_id) == 1) {
                cb.setIsMy(1);
            } else {
                cb.setIsMy(0);
            }
            // 设置用户昵称和头像
            UserBean user = userDao.findUserById(cb.getCommunication_author());
            if (user != null) {
                cb.setAvatar(user.getUser_avatar());
                if (user.getStudent_name() != null) {
                    cb.setNick_name(user.getStudent_name());
                } else if (user.getTeacher_name() != null) {
                    cb.setNick_name(user.getTeacher_name());
                } else {
                    cb.setNick_name(user.getUser_account());
                }
            }
        }
        ResponseUtil.normalResponse(response, communicationList);
    }

    /**
     * 一个问题下的回答列表
     * */
    @RequestMapping(value = "commentList", method = RequestMethod.GET)
    public void commentList(@RequestParam String token,
            @RequestParam int communication_id, HttpServletResponse response) {
        int user_id = TokenUtil.decodeUserId(token);
        List<CommunicationCommentBean> commentList = communicationDao
                .findByCommunicationId(communication_id);
        for (CommunicationCommentBean ccb : commentList) {
            int ccbId = ccb.getCommunication_comment_id();
            // 设置是否为我的评论
            if (communicationDao.isMyComment(ccbId, user_id) == 1) {
                ccb.setIsMy(1);
            } else {
                ccb.setIsMy(0);
            }
            // 设置作者头像和昵称
            UserBean author = userDao.findUserById(ccb
                    .getCommunication_comment_user());
            if (author != null) {
                ccb.setAuthor_avatar(author.getUser_avatar());
                if (author.getStudent_name() != null) {
                    ccb.setAuthor_name(author.getStudent_name());
                } else if (author.getTeacher_name() != null) {
                    ccb.setAuthor_name(author.getTeacher_name());
                } else {
                    ccb.setAuthor_name(author.getUser_account());
                }
            }
            // 设置回复的人昵称
            if (ccb.getCommunication_reply() > 0) {
                UserBean reply = userDao.findUserById(ccb
                        .getCommunication_reply());
                if (reply.getStudent_name() != null) {
                    ccb.setReply_name(reply.getStudent_name());
                } else if (reply.getTeacher_name() != null) {
                    ccb.setReply_name(reply.getTeacher_name());
                } else {
                    ccb.setReply_name(reply.getUser_account());
                }
            }
        }
        ResponseUtil.normalResponse(response, commentList);
    }

    /**
     * 提一个新问题
     * */
    @RequestMapping(value = "addCommunication", method = RequestMethod.POST)
    public void addCommunication(@RequestParam String token,
            @RequestParam String communication_content,
            @RequestParam int lesson_id, HttpServletResponse response) {
        int user_id = TokenUtil.decodeUserId(token);
        int result = communicationDao.addCommunication(lesson_id, user_id,
                communication_content);
        if (result > 0) {
            ResponseUtil.normalResponse(response, null);
        } else {
            ResponseUtil
                    .errorResponse(response, null, ErrorCode.CODE_SYSTEM_ERROR,
                            ErrorCode.MESSAGE_SYSTEM_ERROR);
        }
    }

    /**
     * 回答一个问题
     * */
    @RequestMapping(value = "addComment", method = RequestMethod.POST)
    public void addComment(@RequestParam String token,
            @RequestParam String comment_content,
            @RequestParam int communication_id, @RequestParam int reply_id,
            HttpServletResponse response) {
        int user_id = TokenUtil.decodeUserId(token);
        int result = communicationDao.addComment(communication_id, user_id, reply_id, comment_content);
        if(result>0){
            communicationDao.increCommentNum(communication_id);
            ResponseUtil.normalResponse(response, null);
        }else{
            ResponseUtil
            .errorResponse(response, null, ErrorCode.CODE_SYSTEM_ERROR,
                    ErrorCode.MESSAGE_SYSTEM_ERROR);
        }
    }

    /**
     * 删除我的问题
     * */
    @RequestMapping(value = "delCommunication", method = RequestMethod.POST)
    public void delCommunication(@RequestParam String token,
            @RequestParam int communication_id, HttpServletResponse response) {
        int user_id = TokenUtil.decodeUserId(token);
        if (communicationDao.isMyCommunication(communication_id, user_id) > 0) {
            int result = communicationDao.delCommunication(communication_id);
            if (result > 0) {
                ResponseUtil.normalResponse(response, null);
            } else {
                ResponseUtil.errorResponse(response, null,
                        ErrorCode.CODE_SYSTEM_ERROR,
                        ErrorCode.MESSAGE_SYSTEM_ERROR);
            }
        } else {
            ResponseUtil.errorResponse(response, null,
                    ErrorCode.CODE_NOT_YOUR_COMMINICATION,
                    ErrorCode.MESSAGE_NOT_YOUR_COMMINICATION);
        }
    }

    /**
     * 删除我的评论
     * */
    @RequestMapping(value = "delComment", method = RequestMethod.GET)
    public void delComment(@RequestParam String token,
            @RequestParam int comment_id, HttpServletResponse response) {
        int user_id = TokenUtil.decodeUserId(token);
        if(communicationDao.isMyComment(comment_id, user_id)>0){
            int result = communicationDao.delComment(comment_id);
            if (result > 0) {
                communicationDao.delComment(comment_id);
                ResponseUtil.normalResponse(response, null);
            } else {
                ResponseUtil.errorResponse(response, null,
                        ErrorCode.CODE_SYSTEM_ERROR,
                        ErrorCode.MESSAGE_SYSTEM_ERROR);
            }
        }else{
            ResponseUtil.errorResponse(response, null,
                    ErrorCode.CODE_NOT_YOUR_COMMENT,
                    ErrorCode.MESSAGE_NOT_YOUR_COMMENT);
        }
    }
}
