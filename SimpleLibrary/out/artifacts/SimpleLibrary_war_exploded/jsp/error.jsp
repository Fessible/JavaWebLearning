<%--
  Created by IntelliJ IDEA.
  User: rhm
  Date: 2019-04-22
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    response.setContentType("application/x-msdownload");
//    response.setHeader("Content-Disposition","attachment;filename=abc.txt");
%>
<h1>error</h1> ${requestScope.message}
<a href="<%=request.getContextPath()%>/jsp/uploadFile.jsp">return</a>
</body>
</html>
