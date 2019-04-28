package entity;

import java.util.HashSet;
import java.util.Set;

//销售人员
public class Salesman {

    //id
    private int sid;

    private String sName;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Set<Customer> getCustomerSet() {
        return customerSet;
    }

    public void setCustomerSet(Set<Customer> customerSet) {
        this.customerSet = customerSet;
    }

    //多个客户
    private Set<Customer> customerSet = new HashSet<>();
}
