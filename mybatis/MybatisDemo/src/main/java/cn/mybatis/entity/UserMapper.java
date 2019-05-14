package cn.mybatis.entity;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    //    @Select("select * from user where uid =#{id}")
    User findById(int i);

    int addUser(User user);

    void delete(int i);

    void update(User user);

    User findByIdAndUsername(@Param("id") int id, @Param("username") String username);

    List<User> findByUserLikeName(String name);

    Map<String, Object> getByMap(Integer id);

    @MapKey("uid")
    Map<Integer, User> getMapByName(String name);

    User getByResultMap(int id);

    User getUser(int id);
}
