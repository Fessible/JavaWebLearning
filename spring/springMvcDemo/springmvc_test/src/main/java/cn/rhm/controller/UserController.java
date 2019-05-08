package cn.rhm.controller;

import cn.rhm.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("/testString")
    public String testString(){
        return "success";
    }


    @RequestMapping("/testAjax")
    public @ResponseBody User testAjax(@RequestBody User body){
        System.out.println("testAjax");
        System.out.println(body);

        //模拟数据库操作
        body.setUsername("王二小");
        return body;
    }



}
