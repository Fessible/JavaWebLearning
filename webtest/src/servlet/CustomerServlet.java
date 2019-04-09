package servlet;

import bean.CriteriaCustomer;
import bean.Customer;
import dao.CustomerDao;
import dao.CustomerDaoImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

public class CustomerServlet extends HttpServlet {

    private static final long serialValueUID = 1L;
    private CustomerDao customerDao = new CustomerDaoImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码类型，避免中文乱码出现
        req.setCharacterEncoding("utf-8");

        String path = req.getServletPath();
        System.out.println(path);
        String methodName = path.substring(path.indexOf("/") + 1, path.indexOf("."));
        //通过反射调用对应的方法
        Method method = null;
        try {
            method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            //添加错误页面
            resp.sendRedirect("error.jsp");
        }
    }

    private void addCustomer(HttpServletRequest req, HttpServletResponse resp) {


    }

    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取模糊查询的请求参数
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        CriteriaCustomer criteriaCustomer = new CriteriaCustomer(name, phone, address);

        //1.获取数据
        List<Customer> customerList = customerDao.getWithIndistinct(criteriaCustomer);
        //2.设置数据
        req.setAttribute("customers", customerList);
        //3.页面转发
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) {
    }
}
