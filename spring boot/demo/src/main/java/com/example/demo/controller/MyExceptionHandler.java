package com.example.demo.controller;

import com.example.demo.execption.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    //处理的异常内容，返回json数据
//    @ExceptionHandler(MyException.class)
//    @ResponseBody
//    public Map<String, Object> handleException() {
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", "user.notExist");
//        map.put("message", "用户不存在");
//        return map;
//    }


    @ExceptionHandler(MyException.class)
    public String handleException(HttpServletRequest request) {
        //传入错误的状态码
        request.setAttribute("javax.servlet.error.status_code", 500);

        Map<String, Object> map = new HashMap<>();
        map.put("code", "user.notExist");
        map.put("message", "用户不存在");

        request.setAttribute("ext", map);

        return "forward:/error";
    }
}
