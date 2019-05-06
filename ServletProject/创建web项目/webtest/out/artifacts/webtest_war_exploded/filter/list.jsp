<%--
  Created by IntelliJ IDEA.
  User: Fessi
  Date: 2019/4/16
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="a.jsp">AAAA</a>
<a href="b.jsp">BBBB</a>
<a href="c.jsp">CCCC</a>

<%
    String name = request.getParameter("name");
    if (name != null) {
        session.setAttribute("SESSIONKEY", name);
    }
%>

</body>
</html>
