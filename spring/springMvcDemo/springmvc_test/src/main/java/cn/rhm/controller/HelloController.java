package cn.rhm.controller;

import cn.rhm.entity.Customer;
import cn.rhm.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

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
    public String getSession(ModelMap modelMap) {
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

    @RequestMapping("/voidTest")
    public void test() {
        System.out.println("返回值为 void ");
    }

    @RequestMapping("/modelView")
    public ModelAndView testModelView(){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("success");

        //模拟从数据库中取出数据
        User user = new User();
        user.setUsername("王五");
        user.setPhone("223434");

        //添加对象
        modelAndView.addObject("user", user);

        //设置要跳转的页面
        modelAndView.setViewName("success");

        return modelAndView;
    }

    @RequestMapping("/forwardtest")
    public String forward(){
        System.out.println("执行了forward方法");
        return "forward:/WEB-INF/pages/success.jsp";
    }

    @RequestMapping("/redirect")
    public String redirect(){
        return "redirect:/index.jsp";
    }
}
