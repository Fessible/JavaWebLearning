package com.example.demo.execption;

public class MyException extends RuntimeException {

    public MyException() {
        super("用户不存在");
    }
}
