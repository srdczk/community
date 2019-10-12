package com.czk.community.controller;


import com.czk.community.mapper.CommentMapper;
import com.czk.community.mapper.QuestionMapper;
import com.czk.community.mapper.ReplyMapper;
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
    private ReplyMapper replyMapper;
    @Autowired
    private UserMapper userMapper;


    @GetMapping(value = "/")
    public String index(HttpServletRequest request, Model model, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "tag", required = false) String tag) {
        //通过cookies实现持久登录态
        Util.getUserByCookies(request, userMapper);
        List<Question> questions;
        int sum;
        if (tag == null) {
            sum = questionMapper.getNum();
            page = Util.getCorrectPage(sum, page);
            questions = questionMapper.getUserBy((page - 1) * 10, 10);
        } else {
            String likeTag = "%" + tag + "%";
            sum = questionMapper.getTagNum(likeTag);
            page = Util.getCorrectPage(sum, page);
            questions = questionMapper.getByTag((page - 1) * 10, 10, likeTag);
        }
//        private Integer questionId;
//        //回复的方式
//        private Integer type;
//        //回复人昵称
//        private String creator;
//        //回复了谁
//        private Integer owner;
//        private Long gmtCreate;

        List<ViewObject> vos = new ArrayList<>();
        for (Question question : questions) {
            ViewObject vo = new ViewObject();
            vo.set("question", question);
            vo.set("user", userMapper.getById(question.getCreator()));
            vos.add(vo);
        }
        System.out.println(vos.size());
        model.addAttribute("vos", vos);
        model.addAttribute("pos", Util.getPageObject(sum, page));
        return "index";
    }
}
