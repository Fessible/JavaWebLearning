package com.example.demo;

import com.example.demo.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.applet.AppletContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    Person person;

    @Autowired
    ApplicationContext context;

    @Test
    public void get(){
        boolean b = context.containsBean("helloService");
        System.out.println(b);
    }

    @Test
    public void contextLoads() {
        System.out.println(person);
    }


    @Test
    public  void  log(){
        logger.trace("---trace");
        logger.debug("---debug");
        logger.info("---info");
        logger.warn("---warn");
        logger.error("---error");
    }
}
