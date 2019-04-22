package bean;

public class CriteriaCustomer {
    private String name;
    private String phone;
    private String address;

    public CriteriaCustomer() {
    }

    public CriteriaCustomer(String name, String phone, String address) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    //添加模糊查询的方法
    private String get(String value) {
        if (value == null) {
            value = "%%";
        } else {
            value = "%" + value + "%";
        }
        return value;
    }

    public String getName() {
        return get(name);
    }

    public String getPhone() {
        return get(phone);
    }

    public String getAddress() {
        return get(address);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
