package com.czk.community.mapper;

import com.czk.community.model.Question;
import org.apache.ibatis.annotations.*;

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

    @Select("select * from question order by gmt_create desc limit #{off}, #{cnt}")
    List<Question> getUserBy(@Param(value = "off") Integer off, @Param(value = "cnt") Integer cnt);

    @Select("select * from question where creator = #{userId}")
    List<Question> getByUserId(@Param("userId") Integer userId);

    @Select("select * from question where creator = #{userId} order by gmt_create desc limit #{off}, #{cnt}")
    List<Question> getPage(@Param("userId") Integer userId, @Param("off") Integer off, @Param("cnt") Integer cnt);

    @Select("select count(*) from question where creator = #{userId}")
    Integer getUserQuestionNum(@Param("userId") Integer userId);

    @Select("select * from question where id = #{id}")
    Question getQuestionById(@Param("id") Integer id);
    //UPDATE Person SET FirstName = 'Fred' WHERE LastName = 'Wilson'
    @Update("update question set title = #{title}, description = #{description}, gmt_modified = #{gmtModified}, tag = #{tag} where id = #{id}")
    int update(Question question);

    @Update("update question set view_count = #{viewCount} where id = #{id}")
    int updateViewCount(@Param(value = "viewCount") Integer viewCount, @Param(value = "id") Integer id);

    @Select("select * from question where id != #{id} and tag regexp #{tag} order by gmt_create limit 0, 20")
    List<Question> selectRelated(@Param(value = "id") Integer id, @Param(value = "tag") String tag);

    @Select("select * from question where tag like #{tag} order by gmt_create desc limit #{off}, #{cnt}")
    List<Question> getByTag(@Param("off") Integer off, @Param("cnt") Integer cnt, @Param("tag") String tag);

    @Select("select count(*) from question where tag like #{tag}")
    Integer getTagNum(@Param("tag") String tag);

    @Update("update question set comment_count = #{commentCount}, gmt_modified = #{gmtModified} where id = #{id}")
    int updateCommentCount(Question question);

}
