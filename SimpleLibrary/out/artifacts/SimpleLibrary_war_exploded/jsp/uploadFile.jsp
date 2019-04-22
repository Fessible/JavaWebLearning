<%--
  Created by IntelliJ IDEA.
  User: Fessi
  Date: 2019/4/19
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/uploadServlet" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="text" name="desc"> desc
    <input type="submit">
</form>

</body>
</html>
