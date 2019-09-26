package com.czk.community.mapper;

import com.czk.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * created by srdczk 2019/9/20
 */
@Mapper
public interface QuestionMapper {

    String INSERT_FILED = "title, description, gmt_create, gmt_modified, creator, tag";
    @Insert(value = "insert into question (" + INSERT_FILED + ") values (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    void create(Question question);

    @Select("select * from question")
    List<Question> getAll();

    @Select("select count(*) from question")
    Integer getNum();

    @Select("select * from question limit #{off}, #{cnt}")
    List<Question> getUserBy(@Param(value = "off") Integer off, @Param(value = "cnt") Integer cnt);

    @Select("select * from question where creator = #{userId}")
    List<Question> getByUserId(@Param("userId") Integer userId);

    @Select("select * from question where creator = #{userId} limit #{off}, #{cnt}")
    List<Question> getPage(@Param("userId") Integer userId, @Param("off") Integer off, @Param("cnt") Integer cnt);

    @Select("select count(*) from question where creator = #{userId}")
    Integer getUserQuestionNum(@Param("userId") Integer userId);

}
