package com.czk.community.controller;


import com.czk.community.mapper.QuestionMapper;
import com.czk.community.mapper.UserMapper;
import com.czk.community.model.PageObject;
import com.czk.community.model.Question;
import com.czk.community.model.User;
import com.czk.community.model.ViewObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "/")
    public String index(HttpServletRequest request, Model model, @RequestParam(value = "page", defaultValue = "1") String page) {
        //通过cookies实现持久登录态
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.getByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        int sum = questionMapper.getNum();
        int max = sum % 10 == 0 ? sum / 10 : sum / 10 + 1;

        int p = Integer.valueOf(page);
        if (p < 1) p = 1;
        if (p > max) p = max;
        List<PageObject> pos = new ArrayList<>();
        if (p > 3) pos.add(new PageObject("<<", 1));
        if (p > 1) pos.add(new PageObject("<", p - 1));
        for (int i = Math.max(1, p - 2); i <= p; i++) {
            pos.add(new PageObject(String.valueOf(i), i));
        }
        for (int i = p + 1; i <= Math.min(p + 2, max); i++) {
            pos.add(new PageObject(String.valueOf(i), i));
        }
        if (p < max) pos.add(new PageObject(">", p + 1));
        if (p < max - 2) pos.add(new PageObject(">>", max));
        List<Question> questions = questionMapper.getUserBy((p - 1) * 10, 10);
        List<ViewObject> vos = new ArrayList<>();
        for (Question question : questions) {
            ViewObject vo = new ViewObject();
            vo.set("question", question);
            vo.set("user", userMapper.getById(question.getCreator()));
            vos.add(vo);
        }
        System.out.println(vos.size());
        model.addAttribute("vos", vos);
        model.addAttribute("pos", pos);
        return "index";
    }
}
