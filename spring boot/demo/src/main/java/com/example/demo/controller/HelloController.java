package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class HelloController {

    @RequestMapping("/helloworld")
    public String hello(Map<String,Object> map){
        map.put("hello", "你好");
        return "successful";
    }
}
