package cn.rhm.controller;

import cn.rhm.entity.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class HelloController {

    @RequestMapping("/hello")
    public String sayHello() {
        return "success";
    }

    @RequestMapping("/params")
    public String params(String name, String address) {

        System.out.println(name + "-----" + address);
        return "success";
    }

    @RequestMapping("/saveCustomer")
    public String save(Customer customer){
        System.out.println(customer);
        return "success";
    }

}
