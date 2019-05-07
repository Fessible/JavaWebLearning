package cn.rhm.controller;

import cn.rhm.entity.Customer;
import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/user")
@SessionAttributes(value = {"msg"}) //把msg中的妹妹存到session中
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
    public String save(Customer customer) {
        System.out.println(customer);
        return "success";
    }

    @RequestMapping("/testParam")
    public String sayHello(@RequestParam(value = "cName", required = false) String name) {
        System.out.println(name);
        return "success";
    }

    @RequestMapping("/say")
    public String say(@RequestBody String body) {
        System.out.println("Hello----");
        System.out.println(body);
        return "success";
    }

    @RequestMapping("/sayTest/{id}")
    public String sayTest(@PathVariable("id") String id) {
        System.out.println("Hello----");
        System.out.println(id);
        return "success";
    }

    @RequestMapping("/cookieTest")
    public String cookieTest(@CookieValue(value = "JSESSIONID") String cookieValue) {
        System.out.println(cookieValue);
        return "success";
    }


    //通过model存储到request中
    @RequestMapping("/save")
    public String save(Model model) {
        model.addAttribute("msg", "妹妹");
        return "success";
    }

    @RequestMapping("/getSession")
    public String getSession(ModelMap modelMap){
        String msg = (String) modelMap.get("msg");
        System.out.println(msg);
        return "success";
    }

    @RequestMapping("/deleteSession")
    public String deleteSession(SessionStatus sessionStatus) {
        System.out.println("delete");
        sessionStatus.setComplete();
        return "success";
    }


}
