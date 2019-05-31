package cn.spring.demo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MoneyAspect {

    @Pointcut("execution(* cn.spring.demo.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void before() {
        System.out.println("before controller");
    }
}
