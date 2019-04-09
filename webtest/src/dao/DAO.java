package dao;

import db.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 封装了基本的CRUD方法，以供子类使用
 * 整个DAO采用DBUtils解决
 */
public class DAO<T> {
    private QueryRunner queryRunner = new QueryRunner();
    private Class<T> clazz;

    //获取<T>具体是哪个类
    public DAO() {
        Type superClassType = getClass().getGenericSuperclass();

        if (superClassType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) superClassType;

            //获取到T的数组
            Type[] typeArgs = parameterizedType.getActualTypeArguments();
            if (typeArgs != null && typeArgs.length > 0) {
                clazz = (Class<T>) typeArgs[0];
                System.out.println(clazz);
            }
        }
    }

    /**
     * 返回对应的list
     */
    public List<T> getForList(String sql, Object... args) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            return queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回某个字段的值
     */
    public <E> E getForValue(String sql, Object... args) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            return queryRunner.query(connection, sql, new ScalarHandler<>(), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.releaseConnection(connection);
        }

        return null;
    }

    /**
     * 返回对象
     */
    public T get(String sql, Object... args) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            return queryRunner.query(connection, sql, new BeanHandler<>(clazz), args);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseConnection(connection);
        }

        return null;
    }

    /**
     * 执行insert，delete，update操作
     */
    public void update(String sql, Object... args) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseConnection(connection);
        }
    }
}
