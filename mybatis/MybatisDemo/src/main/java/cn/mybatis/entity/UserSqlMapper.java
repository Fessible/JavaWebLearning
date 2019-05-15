package cn.mybatis.entity;

import org.apache.ibatis.annotations.Param;

import java.util.List;

//动态sql
public interface UserSqlMapper {

    List<User> findByIdAndNameAndAddress(@Param("id") Integer id, @Param("username") String userName, @Param("address") String address);

    List<User> findByChoose(User user);

    void update(User user);

    void update2(User user);

    List<User> getByForEach(List<Integer> list);

}
