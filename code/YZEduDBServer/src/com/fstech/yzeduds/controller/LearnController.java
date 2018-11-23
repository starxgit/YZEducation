package com.fstech.yzeduds.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fstech.yzeduds.dao.ExamDao;
import com.fstech.yzeduds.model.ExamBean;
import com.fstech.yzeduds.model.MyExamBean;
import com.fstech.yzeduds.util.ErrorCode;
import com.fstech.yzeduds.util.ResponseUtil;
import com.fstech.yzeduds.util.TokenUtil;

@Controller
@RequestMapping("/learn")
public class LearnController {
    @Autowired
    private ExamDao examDao;

    /**
     * 一节课的课后习题列表
     * */
    @RequestMapping(value = "examList", method = RequestMethod.GET)
    public void examList(@RequestParam Integer lesson_id,
            @RequestParam String token, HttpServletResponse response) {
        int user_id = TokenUtil.decodeUserId(token);
        int isDo = examDao.isDoExamByLesson(lesson_id, user_id);
        JSONObject return_data = new JSONObject();
        return_data.put("isDo", isDo);
        if (isDo == 0) {
            List<MyExamBean> examBeans = examDao.findExamByLessonId(lesson_id);
            return_data.put("exam_list", examBeans);
        } else {
            List<MyExamBean> myExamBeans = examDao.studentExamByLessonId(
                    lesson_id, user_id);
            return_data.put("exam_list", myExamBeans);
        }
        ResponseUtil.normalResponse(response, return_data);
    }

    /**
     * 提交我的答案
     * */
    @RequestMapping(value = "submitMyExam", method = RequestMethod.POST)
    public void submitMyExam(@RequestParam Integer lesson_id,
            @RequestParam String answers, @RequestParam String token,
            HttpServletResponse response) {
        int user_id = TokenUtil.decodeUserId(token);
        int isDo = examDao.isDoExamByLesson(lesson_id, user_id);
        if (isDo == 0) {
            // TODO 使用Json数组上传答案，从字符串解析出数组，再遍历插入
            JSONArray jsonArray = JSONArray.fromObject(answers);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                int exam_id = item.getInt("exam_id");
                String student_ans = item.getString("my_ans");
                examDao.addMyExam(user_id, exam_id, student_ans);
            }
            ResponseUtil.normalResponse(response, null);
            autoCheck(user_id, lesson_id);
        } else {
            ResponseUtil.errorResponse(response, null,
                    ErrorCode.CODE_HAVE_DONE_EXAM,
                    ErrorCode.MESSAGE_HAVE_DONE_EXAM);
        }
    }

    /**
     * 自动批改客观题的方法
     * */
    private void autoCheck(int user_id, int lesson_id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    List<MyExamBean> myExamBeans = examDao
                            .studentExamByLessonId(lesson_id, user_id);
                    for (int i = 0; i < myExamBeans.size(); i++) {
                        MyExamBean mb = myExamBeans.get(i);
                        int type = mb.getExam_type();
                        String answer = mb.getAnswer();
                        String studentAns = mb.getStudent_ans();
                        int myExamId = mb.getMy_exam_id();
                        if (type != 2) {
                            // 选择题或填空题
                            if (studentAns.equals(answer)) {
                                examDao.updateCheckMyExam(myExamId, 1);
                            } else {
                                examDao.updateCheckMyExam(myExamId, 2);
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
