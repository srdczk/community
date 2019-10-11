package com.czk.community.service;

import com.czk.community.mapper.QuestionMapper;
import com.czk.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * created by srdczk 2019/10/11
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;


    public List<Question> selectRelated(Question question) {

        Integer id = question.getId();
        StringBuilder sb = new StringBuilder();
        String tags = question.getTag();
        if (tags == null || tags.length() == 0) return new ArrayList<>();
        String[] ss = question.getTag().split(",");
        int cnt = 0;
        for (String c : ss) {
            if (cnt++ > 0) sb.append('|');
            sb.append(c);
        }

        return questionMapper.selectRelated(id, sb.toString());

    }


}
