package com.czk.community.mapper;

import com.czk.community.model.Reply;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * created by srdczk 2019/10/12
 */

@Mapper
public interface ReplyMapper {
    //CREATE TABLE reply
    //(
    //    id INT AUTO_INCREMENT PRIMARY KEY,
    //    question_id INT,
    //    type INT,
    //    creator VARCHAR(30),
    //    owner INT,
    //    gmt_create BIGINT
    //);

    String INSERT_FIELD = "question_id, type, creator, owner, gmt_create, show_string";
    @Insert("insert into reply (" + INSERT_FIELD + ") values (#{questionId}, #{type}, #{creator}, #{owner}, #{gmtCreate}, #{showString})")
    void add(Reply reply);

    @Select("select * from reply where owner = #{id} order by gmt_create desc")
    List<Reply> selectByUser(@Param(value = "id") Integer id);

//    @Select("select * from question where creator = #{userId} order by gmt_create desc limit #{off}, #{cnt}")
//    List<Question> getPage(@Param("userId") Integer userId, @Param("off") Integer off, @Param("cnt") Integer cnt);

    @Select("select * from reply where owner = #{userId} order by gmt_create desc limit #{off}, #{cnt}")
    List<Reply> getPage(@Param("userId") Integer userId, @Param("off") Integer off, @Param("cnt") Integer cnt);
    @Select("select count(*) from reply where owner = #{userId}")
    Integer getNum(@Param("userId") Integer userId);

    @Update("update reply set vis = 1 where id = #{id}")
    int updateVis(Reply reply);

    @Select("select * from reply where id = #{id}")
    Reply getReplyById(@Param("id") Integer id);
}
