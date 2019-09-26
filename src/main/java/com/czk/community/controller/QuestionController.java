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

    @GetMapping(value = "/profile/questions")
    public String question(HttpServletRequest request, Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    user = userMapper.getByToken(cookie.getValue());
                    break;
                }
            }
        }
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
