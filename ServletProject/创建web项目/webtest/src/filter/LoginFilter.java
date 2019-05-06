package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LoginFilter implements Filter {

    private String sessionKey;
    private String redirectUrl;
    private String unCheckedUrl;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        sessionKey = filterConfig.getServletContext().getInitParameter("userSessionKey");
        redirectUrl = filterConfig.getServletContext().getInitParameter("redirectPage");
        unCheckedUrl = filterConfig.getServletContext().getInitParameter("unCheckedUrls");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        //1.获取url
        //http://localhost:8080/webtest/filter/list.jsp
        ///webtest/filter/list.jsp
        ///filter/list.jsp
        String requestURL = req.getRequestURL().toString();
        String requestURI = req.getRequestURI();
        String serverletPath = req.getServletPath();
        System.out.println(requestURL);
        System.out.println(requestURI);
        System.out.println(serverletPath);

        //2.检查1中获取的servletPath是否为不需要检查的URL中的一个，如果是，直接放行

        List<String> urls = Arrays.asList(unCheckedUrl.split(","));

        if (urls.contains(serverletPath)) {
            filterChain.doFilter(req, response);
            return;
        }
        //3.从session中获取sessionKey,若不存在就重定向到redirectURl
        Object user = req.getSession().getAttribute(sessionKey);
        if (user == null) {
            response.sendRedirect(req.getContextPath()+redirectUrl);
            return;
        }

        //4.如果存在则放行继续访问
        filterChain.doFilter(req, response);


    }

    @Override
    public void destroy() {

    }
}
