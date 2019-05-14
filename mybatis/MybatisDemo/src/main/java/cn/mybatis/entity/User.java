package cn.mybatis.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("usertest")
public class User implements Serializable {

    private Integer uid;
    private String username;
    private String address;
    private String password;
    private Clazz clazz;

    public Clazz getClazz() {
        return clazz;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", clazz=" + clazz +
                '}';
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
