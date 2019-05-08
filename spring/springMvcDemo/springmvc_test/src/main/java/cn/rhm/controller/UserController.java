package cn.rhm.controller;

import cn.rhm.entity.User;
import cn.rhm.execption.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("/testString")
    public String testString() {
        return "success";
    }

    @RequestMapping("/testAjax")
    public @ResponseBody
    User testAjax(@RequestBody User body) {
        System.out.println("testAjax");
        System.out.println(body);

        //模拟数据库操作
        body.setUsername("王二小");
        return body;
    }


    @RequestMapping("/testException")
    public String testException() throws SysException {
        System.out.println("异常处理");
        try {
            //模拟异常
            int a = 1 / 0;
        } catch (Exception e) {
            throw new SysException("用户信息配置错误");
        }
        return "success";
    }

}
