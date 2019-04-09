package db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 提供数据库连接
 */
public class JDBCUtils {

    public static DataSource dataSources = null;

    static {
        dataSources = new ComboPooledDataSource("mvcapp");
    }

    /**
     * 释放连接
     */
    public static void releaseConnection(){

    }

    /**
     * 获取连接
     */
    public static Connection getConnection() throws SQLException {
        return dataSources.getConnection();
    }
}
