package com.fstech.yzeduas.controller;

import com.fstech.yzeduas.dao.SensitiveWordDao;
import com.fstech.yzeduas.model.SensitiveWord;
import com.fstech.yzeduas.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("word")
public class SensitiveWordController {
    @Autowired
    private SensitiveWordDao sensitiveWordDao;

    // 敏感词列表
    @RequestMapping(value = "wordList", method = RequestMethod.GET)
    public String wordList(Model model, HttpServletRequest request) {
        if (SessionUtil.isLogin(request) == true) {
            List<SensitiveWord> words = sensitiveWordDao.findList();
            model.addAttribute("words", words);
            return "/word-list";
        } else {
            return "/relogin";
        }
    }

    // 到添加敏感词页面
    @RequestMapping(value = "addWord", method = RequestMethod.GET)
    public String toAddWord(HttpServletRequest request) {
        if (SessionUtil.isLogin(request) == true) {
            return "/add_word";
        } else {
            return "/relogin";
        }
    }

    // 添加敏感词
    @RequestMapping(value = "addWord", method = RequestMethod.POST)
    public String addWord(HttpServletRequest request, @RequestParam String word) {
        if (SessionUtil.isLogin(request) == true) {
            sensitiveWordDao.addSensitiviWord(word);
            return "redirect:/word/wordList";
        } else {
            return "/relogin";
        }
    }

    // 删除敏感词
    @RequestMapping(value = "delWord", method = RequestMethod.GET)
    public String delWord(HttpServletRequest request, @RequestParam int id) {
        if (SessionUtil.isLogin(request) == true) {
            sensitiveWordDao.delSensitiviWord(id);
            return "redirect:/word/wordList";
        } else {
            return "/relogin";
        }
    }
}