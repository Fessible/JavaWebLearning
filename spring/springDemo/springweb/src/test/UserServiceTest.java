package test;

import bean.User;
import dao.UserDao;
import dao.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class UserServiceTest {

    @Test
    public void accountMoney() {

        ApplicationContext context = new FileSystemXmlApplicationContext("/web/WEB-INF/applicationContext.xml");
        UserService userService = (UserService) context.getBean("userService");
//        userService.accountMoney();

//        UserDao userDao = (UserDao) context.getBean("userDao");

        User user = new User();
        user.setUsername("王五");
        user.setPassword("147566");
        user.setAddress("五道口");
        userService.save(user);
    }
}