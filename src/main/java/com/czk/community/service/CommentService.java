package com.czk.community.service;

import com.czk.community.enums.CommentTypeEnum;
import com.czk.community.exception.CustomizeErrorCode;
import com.czk.community.exception.CustomizeException;
import com.czk.community.mapper.CommentMapper;
import com.czk.community.mapper.QuestionMapper;
import com.czk.community.model.Comment;
import com.czk.community.model.Question;
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
    private QuestionMapper questionMapper;
    @Transactional
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_ERROR);
        }
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        if (comment.getType().equals(CommentTypeEnum.QUESTION)) {
            Question question = questionMapper.getQuestionById((int)(long)comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            question.setCommentCount(question.getCommentCount() + 1);
            questionMapper.updateCommentCount(question);
            commentMapper.addComment(comment);
        } else {
            Comment father = commentMapper.getCommentById(comment.getParentId());
            if (father == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            father.setCommentCount(father.getCommentCount() + 1);
            father.setGmtModified(System.currentTimeMillis());
            commentMapper.updateCommentCount(comment);
            commentMapper.addComment(comment);
        }
    }

}
