<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/3/28
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BB</title>
</head>
<body>
<h1>This is B Page</h1>
<%
    //转发
//    request.getRequestDispatcher("c.jsp").forward(request,response);
    //请求重定向
    response.sendRedirect("http://www.baidu.com");
%>

</body>
</html>
