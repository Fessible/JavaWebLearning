package test;

import bean.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class userTest {

    @Test
    public void usetTest(){
        ApplicationContext context = new FileSystemXmlApplicationContext("/web/WEB-INF/applicationContext.xml");

        User user = (User) context.getBean("user");
        user.add();
        user.update();
    }
}
