<%--
  Created by IntelliJ IDEA.
  User: Fessi
  Date: 2019/4/19
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/fileUpload" enctype="multipart/form-data">

    <c:if test="${requestScope.message!=null}">${requestScope.message}</c:if>

    <input type="file" name="file1"> file1
    <input type="text" name="desc1"> desc1

    <input type="file" name="file2"> file2
    <input type="text" name="desc2"> desc2
    <input type="submit">
</form>

</body>
</html>
