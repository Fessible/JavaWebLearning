package test;

import bean.CriteriaCustomer;
import bean.Customer;
import dao.CustomerDao;
import dao.CustomerDaoImp;
import org.junit.Test;

public class CustomerDaoImpTest {
    CustomerDao customerDao = new CustomerDaoImp();

    @Test
    public void getWithIndistinct() {
        CriteriaCustomer criteriaCustomer = new CriteriaCustomer("张","","");
        System.out.println(customerDao.getWithIndistinct(criteriaCustomer));
    }

    @Test
    public void get() {
        System.out.println(customerDao.get(1));
    }

    @Test
    public void getAll() {
        System.out.println(customerDao.getAll());
    }

    @Test
    public void delete() {
        customerDao.delete(1);
    }

    @Test
    public void save() {
        Customer customer = new Customer();
        customer.setAddress("武當山");
        customer.setName("小明");
        customer.setPhone("12345678");
        customerDao.save(customer);
    }

    @Test
    public void getCountWithName() {
        customerDao.getCountWithName("张三");
    }
}