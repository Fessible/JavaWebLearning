package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 日志默认级别由地到高 trace<debug<info<warn<error
     */
    @Test
    public void contextLoads() {
        logger.trace("---trace");
        logger.debug("---debug");
        logger.info("---info");
        logger.warn("---warn");
        logger.error("---error");
    }
}
