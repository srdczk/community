package com.czk.community.controller;

import com.czk.community.exception.CustomizeErrorCode;
import com.czk.community.exception.CustomizeException;
import com.czk.community.mapper.CommentMapper;
import com.czk.community.mapper.QuestionMapper;
import com.czk.community.mapper.UserMapper;
import com.czk.community.model.Comment;
import com.czk.community.model.Question;
import com.czk.community.model.User;
import com.czk.community.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * created by srdczk 2019/9/26
 */
@Controller
public class QuestionController {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping(value = "/question/{id}")
    public String question(@PathVariable(value = "id") String id, HttpServletRequest request, Model model) {
        Integer i;
        try {
            i = Integer.valueOf(id);
        } catch (Exception e) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        User user = Util.getUserByCookies(request, userMapper);
        Question question = questionMapper.getQuestionById(i);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        if (user == null || !user.getId().equals(question.getCreator())) {
            question.setViewCount(question.getViewCount() + 1);
            if (questionMapper.updateViewCount(question.getViewCount(), question.getId()) != 1) {
                throw new CustomizeException(CustomizeErrorCode.VIEW_COUNT_UPDATE_ERROR);
            }
        }
        List<Comment> comments = commentMapper.getQuestionComments((long)question.getId());
        model.addAttribute("question", question);
        model.addAttribute("comments", comments);
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
            throw new CustomizeException(CustomizeErrorCode.USER_NOT_SIGN_IN);
        }

        String avatar = user.getAvatar();
        List<Question> questions = questionMapper.getPage(user.getId(), (page - 1) * 10, 10);
        model.addAttribute("questions", questions);
        model.addAttribute("avatar", avatar);
        model.addAttribute("pos", Util.getPageObject(questionMapper.getUserQuestionNum(user.getId()), page));
        return "questions";
    }
}
