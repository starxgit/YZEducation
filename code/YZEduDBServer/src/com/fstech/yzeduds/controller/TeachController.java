package com.fstech.yzeduds.controller;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fstech.yzeduds.dao.ExamDao;
import com.fstech.yzeduds.dao.LessonDao;
import com.fstech.yzeduds.model.ExamBean;
import com.fstech.yzeduds.model.LessonBean;
import com.fstech.yzeduds.model.MyExamBean;
import com.fstech.yzeduds.util.ErrorCode;
import com.fstech.yzeduds.util.ResponseUtil;

/**
 * Created By shaoxin On 2018-12-22 教师教学的控制器
 * */
@Controller
@RequestMapping("teach")
public class TeachController {

    @Autowired
    private ExamDao examDao;

    @Autowired
    private LessonDao lessonDao;

    /**
     * 一节课的课后习题列表
     * */
    @RequestMapping(value = "examList", method = RequestMethod.GET)
    public void examList(@RequestParam Integer lesson_id,
            @RequestParam String token, HttpServletResponse response) {
        JSONObject return_data = new JSONObject();
        List<MyExamBean> examBeans = examDao.findExamByLessonId(lesson_id);
        for (MyExamBean meb : examBeans) {
            int trueStudentNum = examDao.trueStudentNum(meb.getExam_id());
            int finishStudentNum = examDao.finishStudentNum(meb.getExam_id());
            meb.setStudent_ans(trueStudentNum + " / ");
            meb.setMy_exam_state(finishStudentNum);
        }
        return_data.put("exam_list", examBeans);
        ResponseUtil.normalResponse(response, return_data);
    }

    // 教师修改课程

    // 教师添加课时
    @RequestMapping(value = "addLesson", method = RequestMethod.POST)
    public void addLesson(HttpServletResponse response,
            @RequestParam int course_id, @RequestParam String title,
            @RequestParam String knowledge, @RequestParam String videoUrl) {
        LessonBean lessonBean = new LessonBean(0, course_id, title, videoUrl);
        int result = lessonDao.addLesson(lessonBean);
        if (result > 0) {
            int lessonId = lessonBean.getLesson_id();
            System.out.println(lessonId);
            // 添加知识点
            StringTokenizer st = new StringTokenizer(knowledge, ";；");
            String kno;
            while (st.hasMoreTokens()) {
                kno = st.nextToken();
                lessonDao.addKnowledge(lessonId, kno);
                System.out.println(kno);
            }
            ResponseUtil.normalResponse(response, null);
        } else {
            ResponseUtil.errorResponse(response, null,
                    ErrorCode.CODE_ADD_LESSON_FAIL,
                    ErrorCode.MESSAGE_ADD_LESSON_FAIL);
        }
    }

    // 教师上传课程资料

    // 教师删除课程资料

    // 教师下载课程资料

    // 添加课后习题
    @RequestMapping(value = "addExam", method = RequestMethod.POST)
    public void addExam(HttpServletResponse response,
            @RequestParam int exam_type, @RequestParam int lesson_id,
            @RequestParam String question, @RequestParam String option1,
            @RequestParam String option2, @RequestParam String option3,
            @RequestParam String option4, @RequestParam String answer) {
        ExamBean examBean = new ExamBean();
        examBean.setQuestion(question);
        examBean.setLesson_id(lesson_id);
        examBean.setExam_type(exam_type);
        examBean.setOption1(option1);
        examBean.setOption2(option2);
        examBean.setOption3(option3);
        examBean.setOption4(option4);
        examBean.setAnswer(answer);
        int result = examDao.addExam(examBean);
        if (result > 0) {
            ResponseUtil.normalResponse(response, null);
        } else {
            ResponseUtil
                    .errorResponse(response, null, ErrorCode.CODE_SYSTEM_ERROR,
                            ErrorCode.MESSAGE_SYSTEM_ERROR);
        }
    }

    // 待批改习题列表（按题目分组）
    @RequestMapping(value = "checkExamList", method = RequestMethod.GET)
    public void checkExamList(HttpServletResponse response,
            @RequestParam int lesson_id) {
        List<MyExamBean> myExamBeanList = examDao
                .findMyExamByLessonId(lesson_id);
        ResponseUtil.normalResponse(response, myExamBeanList);
    }

    // 人工批改习题
    @RequestMapping(value = "teacherCheckExam", method = RequestMethod.POST)
    public void teacherCheckExam(HttpServletResponse response,
            @RequestParam int my_exam_id, @RequestParam int state) {
        int result = examDao.updateCheckMyExam(my_exam_id, state);
        if (result > 0) {
            ResponseUtil.normalResponse(response, null);
        } else {
            ResponseUtil.errorResponse(response, null,
                    ErrorCode.CODE_CHECK_FAIL, ErrorCode.MESSAGE_CHECK_FAIL);
        }
    }

}
