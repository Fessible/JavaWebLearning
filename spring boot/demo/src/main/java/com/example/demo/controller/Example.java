package com.example.demo.controller;

import com.example.demo.execption.MyException;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
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
    public String success() {
        return "success";
    }

//    @RequestMapping(value = "/,/login.html")
//    public ModelAndView test(ModelAndView mv) {
//        mv.setViewName("/login");
//        return mv;
//    }


    @PostMapping("user/login")
    public String login(HttpSession session, Map<String, Object> map, @RequestParam("username") String username, @RequestParam("password") String password) {
        if (!StringUtils.isEmpty(username) && password.equals("123456")) {

            session.setAttribute("user", username);
            //登录成功重定向到main.html
            return "redirect:/main.html";
        } else {
            map.put("msg", "信息填写错误");
            return "login";
        }
    }

    @GetMapping("/hello")
    @ResponseBody
    public String Hello(@RequestParam("user") String user) {
        if (user.equals("aaa")) {
            throw new MyException();
        }
        return "hello";
    }
}
