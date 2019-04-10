package dao;

import bean.CriteriaCustomer;
import bean.Customer;

import java.util.List;

/**
 * 具体的Customer数据操作
 */
public class CustomerDaoImp extends DAO<Customer> implements CustomerDao {
    @Override
    public List<Customer> getWithIndistinct(CriteriaCustomer criteriaCustomer) {
        String sql = "select id,name,phone,address from customers where name like ? and phone like ? and address like ?";
        return getForList(sql, criteriaCustomer.getName(), criteriaCustomer.getPhone(), criteriaCustomer.getAddress());
    }

    @Override
    public List<Customer> getAll() {
        String sql = "select id,name,phone,address from customers";
        return getForList(sql);
    }

    @Override
    public Customer get(Integer id) {
        String sql = "select id,name,phone,address from customers where id = ?";
        return get(sql, id);
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from customers where id = ?";
        update(sql, id);
    }

    @Override
    public void save(Customer customer) {
        String sql = "insert into customers (name,phone,address) values(?,?,?)";
        update(sql, customer.getName(), customer.getPhone(), customer.getAddress());
    }

    @Override
    public long getCountWithName(String name) {
        String sql = "select count(id) from customers where name =?";
        return getForValue(sql, name);
    }
}
