package com.czk.community.mapper;

import com.czk.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * created by srdczk 2019/9/20
 */
@Mapper
public interface QuestionMapper {
    String INSERT_FILED = "title, description, gmt_create, gmt_modified, creator, tag";
    @Insert(value = "insert into question (" + INSERT_FILED + ") values (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    void create(Question question);

}
