package com.czk.community.controller;

import com.czk.community.mapper.QuestionMapper;
import com.czk.community.mapper.UserMapper;
import com.czk.community.model.Question;
import com.czk.community.model.User;
import com.czk.community.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * created by srdczk 2019/9/26
 */
@Controller
public class QuestionController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping(value = "/question/{id}")
    public String question(@PathVariable(value = "id") Integer id, HttpServletRequest request, Model model) {
        User user = Util.getUserByCookies(request, userMapper);
        Question question = questionMapper.getQuestionById(id);
        model.addAttribute("error", "问题编号错误");
        model.addAttribute("question", question);
        model.addAttribute("user", userMapper.getById(question.getCreator()));
        if (user != null && user.getId().equals(question.getCreator())) {
            model.addAttribute("isMe",  "true");
        }
        return "question";
    }

    @GetMapping(value = "/profile/questions")
    public String question(HttpServletRequest request, Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {

        User user = Util.getUserByCookies(request, userMapper);

        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "questions";
        }
        request.getSession().setAttribute("user", user);
        String avatar = user.getAvatar();
        List<Question> questions = questionMapper.getPage(user.getId(), (page - 1) * 10, 10);
        model.addAttribute("questions", questions);
        model.addAttribute("avatar", avatar);
        model.addAttribute("pos", Util.getPageObject(questionMapper.getUserQuestionNum(user.getId()), page));
        return "questions";
    }
}
