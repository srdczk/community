package com.czk.community.mapper;

import com.czk.community.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * created by srdczk 2019/9/28
 */
@Mapper
public interface CommentMapper {

    @Select("select * from comment")
    List<Comment> getAll();

    @Select("select * from comment where parent_id = #{parentId}")
    List<Comment> getQuestionComments(@Param(value = "parentId") Long parentId);

    String INSERT_FIELD = "parent_id, type, creator, gmt_create, gmt_modified, text";

    @Insert(value = "insert into comment (" + INSERT_FIELD + ") values (#{parentId}, #{type}, #{creator}, #{gmtCreate}, #{gmtModified}, #{text})")
    void addComment(Comment comment);

    @Select("select * from comment where id = #{id}")
    Comment getCommentById(@Param(value = "id") Long id);

    @Update("update comment set comment_count = #{commentCount} where id = #{id}")
    int updateCommentCount(Comment comment);

}
