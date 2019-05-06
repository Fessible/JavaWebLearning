package bean;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        // 从结果集里面把数据得到
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String address = resultSet.getString("address");

        //把数据封装到对象中
        User user = new User();
        user.setAddress(address);
        user.setPassword(password);
        user.setUsername(username);

        return user;
    }
}
