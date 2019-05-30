package com.example.demo.controller;

import com.example.demo.execption.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String Hello(@RequestParam("user") String user) {
        if (user.equals("aaa")) {
            throw new MyException();
        }
        return "hello";
    }
}
