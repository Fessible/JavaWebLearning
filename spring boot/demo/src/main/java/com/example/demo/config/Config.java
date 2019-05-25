package com.example.demo.config;

import com.example.demo.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public HelloService helloService() {
        return new HelloService();
    }
}
