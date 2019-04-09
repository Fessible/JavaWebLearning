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
<script src="scripts/jquery-3.3.1.js"></script>
<script type="text/javascript">
    $(function () {
        $(".delete").click(function () {
            //通过这个按钮找到包含name的td，第一个parent找到td，第二个parent找到tr
            var content = $(this).parent().parent().find("td:eq(1)").text().replace("\n","");
            var flag = confirm("确定要删除" + content + "的信息吗？");
            return flag;
        })
    })
</script>
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
        <td><%= customer.getId()%></td>
        <td><%= customer.getName()%></td>
        <td><%= customer.getPhone()%></td>
        <td><%= customer.getAddress()%></td>
        <td><a href="update.do">UPDATE</a>
            <a href="delete.do?id=<%=customer.getId()%>" class="delete">DELETE</a>
        </td>
    </tr>

    <%
        }
    %>
</table>

</body>

</html>
