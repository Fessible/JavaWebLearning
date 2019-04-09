package dao;

import java.util.List;

/**
 * 封装了基本的CRUD方法，以供子类使用
 */
public class DAO<T> {

    /**
     * 返回对应的list
     */
    public List<T> getForList(String sql, Object... args) {
        return null;
    }

    /**
     * 返回某个字段的值
     */
    public <E> E getForValue(String sql, Object... args) {
        return null;
    }

    /**
     * 返回对象
     */
    public T get(String sql, Object... args) {
        return null;
    }

    /**
     * 执行insert，delete，update操作
     */
    public void update(String sql, Object... args) {

    }


}
