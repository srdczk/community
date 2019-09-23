package com.czk.community.mapper;

import com.czk.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

}
