package com.czk.community.controller;

import com.czk.community.exception.CustomizeErrorCode;
import com.czk.community.exception.CustomizeException;
import com.czk.community.mapper.QuestionMapper;
import com.czk.community.mapper.ReplyMapper;
import com.czk.community.mapper.UserMapper;
import com.czk.community.model.Question;
import com.czk.community.model.Reply;
import com.czk.community.model.User;
import com.czk.community.model.ViewObject;
import com.czk.community.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * created by srdczk 2019/10/12
 */
@Controller
public class RepliesController {

    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @GetMapping(value = "/profile/replies")
    public String replies(HttpServletRequest request, Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        User user = Util.getUserByCookies(request, userMapper);
        if (user == null) {
            throw new CustomizeException(CustomizeErrorCode.USER_NOT_SIGN_IN);
        }
        int sum = replyMapper.getNum(user.getId());
        page = Util.getCorrectPage(sum, page);
        List<Reply> replies = replyMapper.getPage(user.getId(), (page - 1) * 10, 10);
        List<ViewObject> vos = new ArrayList<>();
        for (Reply reply : replies) {
            ViewObject vo = new ViewObject();
            vo.set("type", Util.getType(reply.getType()));
            vo.set("showString", reply.getShowString());
            vo.set("questionId", reply.getQuestionId());
            vo.set("creator", reply.getCreator());
            vo.set("vis", reply.getVis());
            vo.set("replyId", reply.getId());
            vos.add(vo);
        }

        Integer unread = user.getUnreadCount();


        model.addAttribute("unread", unread);
        model.addAttribute("vos", vos);
        model.addAttribute("pos", Util.getPageObject(sum, page));

        return "replies";
    }
}
