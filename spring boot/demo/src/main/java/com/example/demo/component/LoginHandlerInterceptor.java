package com.example.demo.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginHandlerInterceptor implements HandlerInterceptor {

    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //我们根据user是否为空来判断用户是否登录了
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("user");
        if (username != null) {
//            已登录，放行
            return true;
        }
//        未登录,转发到登录页面
        request.setAttribute("msg", "没有权限请先登录");
        request.getRequestDispatcher("/index.html").forward(request, response);
        return false;
    }
}
