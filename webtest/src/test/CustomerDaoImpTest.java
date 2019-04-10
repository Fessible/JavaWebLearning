package test;

import bean.CriteriaCustomer;
import bean.Customer;
import dao.CustomerDao;
import dao.CustomerDaoImp;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerDaoImpTest {
    CustomerDao customerDao = new CustomerDaoImp();

    @Test
    public void testPattern(){
        Pattern pattern = Pattern.compile("\\/(\\w+).do$");
        Matcher matcher = pattern.matcher("/jsp/addCustomer.do");
        matcher.find();
        System.out.println(matcher.group(1));
    }

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
        long count = customerDao.getCountWithName("小明");
        System.out.println(count);
    }

    @Test
    public void testUpdate(){
        Customer customer = new Customer("王五","小里","");
        customer.setId(1);
        customerDao.update(customer);
    }
}