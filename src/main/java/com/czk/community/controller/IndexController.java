package com.czk.community.controller;


import com.czk.community.mapper.CommentMapper;
import com.czk.community.mapper.QuestionMapper;
import com.czk.community.mapper.UserMapper;
import com.czk.community.model.*;
import com.czk.community.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;


    @GetMapping(value = "/")
    public String index(HttpServletRequest request, Model model, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "tag", required = false) String tag) {
        //通过cookies实现持久登录态
        Util.getUserByCookies(request, userMapper);
        List<Question> questions;
        if (tag == null) {
            questions = questionMapper.getUserBy((page - 1) * 10, 10);
        } else {
            String likeTag = "%" + tag + "%";
            System.out.println(likeTag);
            questions = questionMapper.getByTag((page - 1) * 10, 10, likeTag);
        }


        List<ViewObject> vos = new ArrayList<>();
        for (Question question : questions) {
            ViewObject vo = new ViewObject();
            vo.set("question", question);
            vo.set("user", userMapper.getById(question.getCreator()));
            vos.add(vo);
        }
        System.out.println(vos.size());
        model.addAttribute("vos", vos);
        model.addAttribute("pos", Util.getPageObject(questionMapper.getNum(), page));
        return "index";
    }
}
