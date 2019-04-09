<%@ page import="bean.Customer" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/3/24
  Time: 6:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<form method="post" action="query.do">
    <table>
        <tr>
            <td>Address:</td>
            <td><input type="text" name="address"></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><input type="text" name="phone"></td>
        </tr>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td><input type="submit" value="query"></td>
            <td><input type="button" value="add" onclick="window.location.href='addCustomer.do'"></td>
        </tr>
    </table>
</form>
<br>
<%
    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
    if (customers == null || customers.isEmpty()) {
        return;
    }
%>

<table border="1" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>PHONE</th>
        <th>ADDRESS</th>
        <th>UPDATE/DELETE</th>
    </tr>
    <%
        for (Customer customer : customers) {
    %>
    <tr>
        <td><%= customer.getId()%>
        </td>
        <td><%= customer.getName()%>
        </td>
        <td><%= customer.getPhone()%>
        </td>
        <td><%= customer.getAddress()%>
        </td>
        <td><a href="update.do">UPDATE</a> <a href="update.do">DELETE</a></td>

    </tr>

    <%
        }
    %>
</table>

</body>

</html>
