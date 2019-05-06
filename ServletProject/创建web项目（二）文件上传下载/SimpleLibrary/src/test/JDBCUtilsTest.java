package test;

import db.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtilsTest {

    @Test
    public void getConnection() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection);
    }
}