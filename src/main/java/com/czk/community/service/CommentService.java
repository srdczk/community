package com.czk.community.service;

import com.czk.community.enums.CommentTypeEnum;
import com.czk.community.exception.CustomizeErrorCode;
import com.czk.community.exception.CustomizeException;
import com.czk.community.mapper.CommentMapper;
import com.czk.community.mapper.QuestionMapper;
import com.czk.community.mapper.ReplyMapper;
import com.czk.community.mapper.UserMapper;
import com.czk.community.model.Comment;
import com.czk.community.model.Question;
import com.czk.community.model.Reply;
import com.czk.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * created by srdczk 2019/9/28
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private QuestionMapper questionMapper;
    @Transactional
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_ERROR);
        }
        Reply reply = new Reply();
        reply.setGmtCreate(System.currentTimeMillis());
        reply.setType(comment.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        if (comment.getType().equals(CommentTypeEnum.QUESTION.getType())) {
            Question question = questionMapper.getQuestionById((int) (long) comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            reply.setQuestionId(question.getId());
            String showString = question.getTitle();
            if (showString.length() > 15) showString = showString.substring(0, 12) + "...";
            reply.setShowString(showString);
            reply.setCreator(userMapper.getById(comment.getCreator()).getName());
            reply.setOwner(question.getCreator());
            User user = userMapper.getById(question.getCreator());
            user.setUnreadCount(user.getUnreadCount() + 1);
            userMapper.updateUnreadCount(user);
            question.setCommentCount(question.getCommentCount() + 1);
            questionMapper.updateCommentCount(question);
            commentMapper.addComment(comment);
            replyMapper.add(reply);
        } else {
            Comment father = commentMapper.getCommentById(comment.getParentId());
            if (father == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            reply.setQuestionId((int) (long) father.getParentId());
            String showString = father.getText();
            if (showString.length() > 15) showString = showString.substring(0, 12) + "...";
            reply.setShowString(showString);
            reply.setCreator(userMapper.getById(comment.getCreator()).getName());
            reply.setOwner(father.getCreator());
            User a = userMapper.getById(father.getCreator());
            a.setUnreadCount(a.getUnreadCount() + 1);
            userMapper.updateUnreadCount(a);

            father.setCommentCount(father.getCommentCount() + 1);
            father.setGmtModified(System.currentTimeMillis());
            commentMapper.updateCommentCount(father);
            commentMapper.addComment(comment);
            replyMapper.add(reply);
        }
    }

}
