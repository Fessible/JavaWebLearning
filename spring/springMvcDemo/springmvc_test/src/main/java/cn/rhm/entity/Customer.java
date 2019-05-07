package cn.rhm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Customer implements Serializable{

    @Override
    public String toString() {
        return "Customer{" +
                "userList=" + userList +
                ", map=" + map +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", date=" + date +
                ", user=" + user +
                '}';
    }

    private List<User> userList;
    private Map<String,User> map;

    private String name;

    public Map<String, User> getMap() {
        return map;
    }

    public void setMap(Map<String, User> map) {
        this.map = map;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    private String address;

    private Date date;

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
