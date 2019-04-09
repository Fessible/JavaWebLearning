import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //使用getParameter
        String params = req.getParameter("interesting");
        System.out.println(params);


        //使用getParameterValues获取多个
        String[] strings = req.getParameterValues("interesting");
        System.out.println(Arrays.asList(strings));

        //使用Enumeration 获取requests中的name值
        Enumeration<String> enumeration = req.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();

            String value = req.getParameter(name);
            System.out.println(name + "---" + value);

            //由于内容有多个，所以用getParameterValues
            String values[] = req.getParameterValues("interesting");
            System.out.println(name + "--->" + Arrays.asList(values));
        }

        //使用Map
        Map<String, String[]> maps = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : maps.entrySet()) {
            System.out.println(entry.getKey() + "***" + Arrays.asList(entry.getValue()));
        }

        //获取uri
        System.out.println(req.getRequestURI());

        //获取url
        System.out.println(req.getRequestURL());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
