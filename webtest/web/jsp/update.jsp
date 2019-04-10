<%@ page import="bean.Customer" %><%--
  Created by IntelliJ IDEA.
  User: rhm
  Date: 2019/4/10
  Time: 9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<form method="post" action="update.do">

    <%
        Customer customer = (Customer) request.getAttribute("customer");
        String name = null;
        String phone = null;
        String address = null;
        String id = null;

        if (customer != null) {
            name = customer.getName();
            id = customer.getId() + "";
            address = customer.getAddress();
            phone = customer.getPhone();
        } else {
            name = request.getParameter("name");
            phone = request.getParameter("phone");
            address = request.getParameter("address");
            id = request.getParameter("id");
        }
        Object message = request.getAttribute("message");
        if (message != null) {

    %>
    <font color="red"><%=message%>
    </font>
    <%
        }

    %>
    <%--使用隐藏域--%>
    <input type="hidden" name="id" value="<%=id%>">
    <input type="hidden" name="oldName" value="<%=name%>">
    <table>
        <tr>
            <td>Address:</td>
            <td><input type="text" name="address"
                       value="<%=address%>"></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><input type="text" name="phone"
                       value="<%=phone%>"></td>
        </tr>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name"
                       value="<%=name%>"
            ></td>
        </tr>
        <tr>
            <td><input type="submit" value="update"></td>
        </tr>
    </table>
</form>
</body>
</html>
