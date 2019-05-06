<%--
  Created by IntelliJ IDEA.
  User: rhm
  Date: 2019-05-06
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello</h1>

<a href="hello">Hello world!</a>

<form action="user/params" method="post">
    姓名：<input type="text" name="name"><br>
    住址：<input type="text" name="address"><br>
<%--    用户的真实姓名：<input type="text" name="user.username"><br>--%>
<%--    用户的电话：<input type="text" name="user.phone"><br>--%>
    <input type="submit">
</form>

<form action="user/saveCustomer" method="post">
    姓名：<input type="text" name="name"><br>
    住址：<input type="text" name="address"><br>
    用户的真实姓名：<input type="text" name="user.username"><br>
    用户的电话：<input type="text" name="user.phone"><br>
    <input type="submit">
</form>
</body>
</html>
