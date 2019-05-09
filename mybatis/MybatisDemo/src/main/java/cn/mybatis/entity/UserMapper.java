package cn.mybatis.entity;

import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from user where uid =#{id}")
    User findById(int i);
}
