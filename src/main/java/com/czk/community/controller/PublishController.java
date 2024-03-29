package com.czk.community.controller;

import com.czk.community.exception.CustomizeErrorCode;
import com.czk.community.exception.CustomizeException;
import com.czk.community.mapper.QuestionMapper;
import com.czk.community.mapper.UserMapper;
import com.czk.community.model.Question;
import com.czk.community.model.User;
import com.czk.community.util.StringUtil;
import com.czk.community.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * created by srdczk 2019/9/18
 */
@Controller
public class PublishController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;


    @PutMapping("/publish")
    public String update(@RequestParam("title") String title,
                         @RequestParam("text") String description,
                         @RequestParam("tag") String tag,
                         @RequestParam("id") String id,
                         HttpServletRequest request,
                         Model model) {
        model.addAttribute("id", id);
        model.addAttribute("title", title);
        model.addAttribute("text", description);
        model.addAttribute("tag", tag);
        if (title == null || title.equals("")) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description.equals("")) {
            model.addAttribute("error", "内容不能为空");
            return "publish";
        }
        if (tag == null || tag.equals("")) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        if (!StringUtil.canUse(tag)) {
            model.addAttribute("error", "不能使用非法标签");
            return "publish";
        }

        User user = Util.getUserByCookies(request, userMapper);
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setId(Integer.valueOf(id));
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtModified(System.currentTimeMillis());
        questionMapper.update(question);
        return "redirect:/";
    }


    @PostMapping("/publish")
    public String publish(@RequestParam("title") String title,
                          @RequestParam("text") String description,
                          @RequestParam("tag") String tag,
                          HttpServletRequest request,
                          Model model) {
        model.addAttribute("title", title);
        model.addAttribute("text", description);
        model.addAttribute("tag", tag);
        if (title == null || title.equals("")) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description.equals("")) {
            model.addAttribute("error", "内容不能为空");
            return "publish";
        }
        if (tag == null || tag.equals("")) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        User user = Util.getUserByCookies(request, userMapper);
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        if (!StringUtil.canUse(tag)) {
            model.addAttribute("error", "不能使用非法标签");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(System.currentTimeMillis());
        question.setCreator(user.getId());
        questionMapper.create(question);
        return "redirect:/";
    }


    @GetMapping("/publish/{id}")
    public String update(@PathVariable(value = "id") String id, Model model, HttpServletRequest request) {
        Integer i = null;
        try {
            i = Integer.valueOf(id);
        } catch (Exception e) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        Question question = questionMapper.getQuestionById(i);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        User user = Util.getUserByCookies(request, userMapper);
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        model.addAttribute("id", id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("text", question.getDescription());
        model.addAttribute("tag", question.getTag());

        return "publish";
    }

    @GetMapping("/publish")
    public String publish(HttpServletRequest request) {
        Util.getUserByCookies(request, userMapper);
        return "publish";
    }
}
