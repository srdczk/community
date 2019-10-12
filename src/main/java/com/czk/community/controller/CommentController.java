package com.czk.community.controller;

import com.czk.community.dto.CommentDTO;
import com.czk.community.dto.ResultDTO;
import com.czk.community.exception.CustomizeErrorCode;
import com.czk.community.exception.CustomizeException;
import com.czk.community.mapper.QuestionMapper;
import com.czk.community.mapper.UserMapper;
import com.czk.community.model.Comment;
import com.czk.community.model.Question;
import com.czk.community.model.User;
import com.czk.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;


/**
 * created by srdczk 2019/9/28
 */

@Controller
public class CommentController {


    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomizeException(CustomizeErrorCode.USER_NOT_SIGN_IN);
        }

        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setText(commentDTO.getText());
        comment.setType(commentDTO.getType());
        comment.setCreator(user.getId());
        commentService.insert(comment);
        return new ResultDTO(200, "成功");
    }
}
