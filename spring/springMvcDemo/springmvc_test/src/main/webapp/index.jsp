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
    日期: <input type="text" name="date"><br>

    用户姓名: <input type="text" name="userList[0].username"><br>
    用户电话: <input type="text" name="userList[0].phone"><br>


    用户姓名: <input type="text" name="map['one'].username"><br>
    用户电话: <input type="text" name="map['one'].phone"><br>

    用户的真实姓名：<input type="text" name="user.username"><br>
    用户的电话：<input type="text" name="user.phone"><br>
    <input type="submit">
</form>


<form action="user/testParam" method="post">
    姓名：<input type="text" name="cName"><br>
    <input type="submit">
</form>

<form action="user/say" method="post">
    姓名：<input type="text" name="cName"><br>
    <input type="submit">
</form>

<a href="user/sayTest/200">click here</a>
<a href="user/cookieTest">CookieTest here</a>
<a href="user/save">save here</a>
<a href="user/getSession">get session here</a>
<a href="user/deleteSession">delete session here</a>

<a href="user/voidTest">void here</a>
<br>
<a href="user/modelView">model View</a>

<br>
<a href="user/forwardtest">forward </a>
<br>
<a href="user/redirect">redirect</a>

<br>
</body>
</html>
