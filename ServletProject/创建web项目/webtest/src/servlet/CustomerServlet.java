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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        Pattern pattern = Pattern.compile("\\/(\\w+).do$");
        Matcher matcher = pattern.matcher(path);
        String methodName = null;
        if (matcher.find()) {
            methodName = matcher.group(1);
        }
//        String methodName = path.substring(path.indexOf("/") + 1, path.indexOf("."));
        //通过反射调用对应的方法
        Method method = null;
        try {
            method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            //添加错误页面
            resp.sendRedirect("jsp/error.jsp");
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数
        String idStr = request.getParameter("id");
        int id = 0;
        String forwardPath = "jsp/error.jsp";
        //防止id转换错误,如果错误仍然执行query.do
        try {
            id = Integer.parseInt(idStr);
            //2.获取id对应的参数对象,得到Customer，然后转发
            Customer customer = customerDao.get(id);
            if (customer != null) {
                forwardPath = "jsp/update.jsp";
                request.setAttribute("customer", customer);
            }

        } catch (Exception e) {
        }

        request.getRequestDispatcher(forwardPath).forward(request, response);

    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取表单数据
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String oldName = request.getParameter("oldName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        //2.检查name是否已经存在
        if (!name.equals(oldName)) {
            long count = customerDao.getCountWithName(name);
            //检查数据库中时否已经存在
            if (count > 0) {
                request.setAttribute("message", "用户" + name + "已经存在");
                //转发到原来的界面
                request.getRequestDispatcher("jsp/update.jsp").forward(request, response);
                return;
            }

            //数据库中不存在，就更新用户
            Customer customer = new Customer(name, phone, address);
            customer.setId(Integer.parseInt(id));
            customerDao.update(customer);

            response.sendRedirect("query.do");
        } else {
            request.getRequestDispatcher("jsp/update.jsp").forward(request, response);
        }
    }

    private void addCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取表单参数
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");

        //2.查看名字是否被占用
        //2.1 getCountWithName()来查看
        int count = (int) customerDao.getCountWithName(name);

        //2.2 如果值大于0 转发响应newCustomer.jsp，需要保持原数据信息，并显示错误消息
//        req.getRequestDispatcher("/jsp/addCustomer.jsp");
        if (count > 0) {
            req.setAttribute("message", "用户名" + name + "已被占用，请重新选择");
            req.getRequestDispatcher("addCustomer.jsp").forward(req, resp);
            return;
        }

        Customer customer = new Customer(name, address, phone);
        customerDao.save(customer);
        //3.如果不存在，则添加Customer，并回到添加成功界面
        //因为在当前路径 /jsp，所以直接使用success
        resp.sendRedirect("success.jsp");
    }

    private void delete(HttpServletRequest req, HttpServletResponse response) throws IOException {
        String strId = req.getParameter("id");
        int id = 0;
        //防止id转换错误,如果错误仍然执行query.do
        try {
            id = Integer.parseInt(strId);
            customerDao.delete(id);
        } catch (Exception e) {
        }
        response.sendRedirect("query.do");
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
        req.getRequestDispatcher("jsp/index.jsp").forward(req, resp);
    }

}
