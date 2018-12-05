package com.fstech.yzeduas.controller;

import com.fstech.yzeduas.mapper.AdviceMapper;
import com.fstech.yzeduas.model.Advice;
import com.fstech.yzeduas.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created By shaoxin On 12/5/18
 */
@Controller
@RequestMapping("advice")
public class AdviceController {
    @Autowired
    private AdviceMapper adviceMapper;

    // 未读消息列表
    @RequestMapping(value = "adviceList", method = RequestMethod.GET)
    public String adviceList(Model model, HttpServletRequest request) {
        if (SessionUtil.isLogin(request) == true) {
            List<Advice> advices = adviceMapper.findList();
            model.addAttribute("advices", advices);
            return "/advice_list";
        } else {
            return "/relogin";
        }
    }

    // 标记消息已读
    @RequestMapping(value = "readAdvice", method = RequestMethod.GET)
    public String readAdvice(HttpServletRequest request, @RequestParam int advice_id) {
        if (SessionUtil.isLogin(request) == true) {
            adviceMapper.updateRead(advice_id);
            return "redirect:/advice/adviceList";
        } else {
            return "/relogin";
        }
    }
}