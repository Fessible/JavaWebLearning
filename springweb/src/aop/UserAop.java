package aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class UserAop {

    @Before(value = "execution(* bean.User.*(..))")
    public void before(){
        System.out.println("Before.....");
    }
}
