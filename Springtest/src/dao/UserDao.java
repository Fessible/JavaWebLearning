package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.UserService;

import javax.annotation.Resource;

@Component
public class UserDao {

    @Resource
    UserService userService2;

    public void add(){
        System.out.println("UserDao ... add");
        userService2.service();
    }
}
