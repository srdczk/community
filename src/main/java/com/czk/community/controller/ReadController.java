package com.czk.community.controller;

import com.czk.community.exception.CustomizeErrorCode;
import com.czk.community.exception.CustomizeException;
import com.czk.community.mapper.ReplyMapper;
import com.czk.community.mapper.UserMapper;
import com.czk.community.model.Reply;
import com.czk.community.model.User;
import com.czk.community.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * created by srdczk 2019/10/12
 */
@Controller
public class ReadController {
    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private UserMapper userMapper;
    @GetMapping(value = "/read")
    public String read(@RequestParam(value = "questionId") Integer questionId, @RequestParam(value = "replyId") Integer replyId, HttpServletRequest request) {
        User user = Util.getUserByCookies(request, userMapper);
        if (user == null) {
            throw new CustomizeException(CustomizeErrorCode.USER_NOT_SIGN_IN);
        }

        Reply reply = replyMapper.getReplyById(replyId);
        if (reply == null) {
            throw new CustomizeException(CustomizeErrorCode.REPLY_NOT_FOUND);
        }

        if (reply.getVis().equals(0)) {
            user.setUnreadCount(user.getUnreadCount() - 1);
            userMapper.updateUnreadCount(user);
            replyMapper.updateVis(reply);
        }

        return "redirect:/question/" + questionId;

    }

}
