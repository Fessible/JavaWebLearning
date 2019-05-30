package com.example.demo.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Example {
    @Value(value = "${person.name}")
    String name;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello world " + name;
    }


//    @RequestMapping("/success")
//    public ModelAndView success(ModelAndView modelAndView) {
//        //跳转到success.html
//        modelAndView.setViewName("/success");
//        return modelAndView;
//    }


    @RequestMapping("/success")
    public String  success(){
        return "success";
    }

    @RequestMapping(value = "/,/login.html")
    public ModelAndView test(ModelAndView mv) {
        mv.setViewName("/login");
        return mv;
    }

}
