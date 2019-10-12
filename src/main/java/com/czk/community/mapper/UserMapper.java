package com.czk.community.mapper;

import com.czk.community.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    String INSERT_FIELD = "account_id, name, token, gmt_create, gmt_modified, bio, avatar";

    String SELECT_FIELD = "id, account_id, name, token, gmt_create, gmt_modified";

    @Insert(value = "insert into user (" + INSERT_FIELD + ") values (#{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified}, #{bio}, #{avatar})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User getByToken(@Param(value = "token") String token);

    @Select("select * from user where id = #{id}")
    User getById(@Param(value = "id") Integer id);

    @Select("select * from user where name = #{name}")
    User getByName(@Param(value = "name") String name);

    @Select("select * from user where account_id = #{accountId}")
    User getByAccountId(@Param(value = "accountId") String accountId);

    @Update("update user set name = #{name}, bio = #{bio}, avatar = #{avatar}, gmt_modified = #{gmtModified} where id = #{id}")
    int update(User user);

    @Update("update user set unread_count = #{unreadCount} where id = #{id}")
    int updateUnreadCount(User user);
}
