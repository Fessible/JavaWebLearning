package dao;

import bean.Customer;

import java.util.List;

public interface CustomerDao {
    /**
     * 获取所有信息
     */
    List<Customer> getAll();

    /**
     * 根据id进行查询
     */
    public Customer get(Integer id);

    /**
     * 删除
     */
    public void delete(Integer id);

    /**
     * 保存Customer
     */
    public void save(Customer customer);

    /**
     * 根据name查询记录数
     */
    public void getCountWithName(String name);
}
