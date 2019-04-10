package dao;

import bean.CriteriaCustomer;
import bean.Customer;

import java.util.List;

public interface CustomerDao {
    /**
     * 模糊查询
     */
    List<Customer> getWithIndistinct(CriteriaCustomer criteriaCustomer);

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
    public long getCountWithName(String name);
}
