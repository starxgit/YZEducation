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
import com.fstech.yzeduds.util.ResponseUtil;
import com.fstech.yzeduds.util.TokenUtil;

@Controller
@RequestMapping("/learn")
public class LearnController {
    @Autowired
    private ExamDao examDao;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public void test(HttpServletResponse response) {

        JSONArray answer_list = new JSONArray();
        JSONObject item1 = new JSONObject();
        item1.put("exam_id", 2);
        item1.put("student_ans", "A");
        JSONObject item2 = new JSONObject();
        item2.put("exam_id", 3);
        item2.put("student_ans", "A");
        JSONObject item3 = new JSONObject();
        item3.put("exam_id", 4);
        item3.put("student_ans", "A");
        JSONObject item4 = new JSONObject();
        item4.put("exam_id", 5);
        item4.put("student_ans", "JVM虚拟机");
        JSONObject item5 = new JSONObject();
        item5.put("exam_id", 6);
        item5.put("student_ans", "不知道");
        answer_list.add(item1);
        answer_list.add(item2);
        answer_list.add(item3);
        answer_list.add(item4);
        answer_list.add(item5);
        System.out.println(answer_list.toString());
    }

    /**
     * 一节课的课后习题列表
     * */
    @RequestMapping(value = "examList", method = RequestMethod.GET)
    public void examList(@RequestParam Integer lesson_id,
            @RequestParam String token, HttpServletResponse response) {
        int user_id = TokenUtil.decodeUserId(token);
        int isDo = examDao.isDoExamByLesson(user_id, lesson_id);
        JSONObject return_data = new JSONObject();
        return_data.put("isDo", isDo);
        if (isDo == 0) {
            List<ExamBean> examBeans = examDao.findExamByLessonId(lesson_id);
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
    @RequestMapping(value = "submitMyExam", method = RequestMethod.GET)
    public void submitMyExam(@RequestParam Integer lesson_id,
            @RequestParam String token, HttpServletResponse response) {
        String answers = "[{'student_ans':'A','exam_id':2},{'student_ans':'A','exam_id':3},{'student_ans':'A','exam_id':4},{'student_ans':'JVM','exam_id':5},{'student_ans':'不知道','exam_id':6}]";
        int user_id = TokenUtil.decodeUserId(token);
        // TODO 使用Json数组上传答案，从字符串解析出数组，再遍历插入
        JSONArray jsonArray = JSONArray.fromObject(answers);;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject item = jsonArray.getJSONObject(i);
            int exam_id = item.getInt("exam_id");
            String student_ans = item.getString("student_ans");
            examDao.addMyExam(user_id, exam_id, student_ans);
        }
        ResponseUtil.normalResponse(response, null);
        autoCheck(user_id, lesson_id);
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
