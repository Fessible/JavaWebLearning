<%@ page import="bean.Customer" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/3/24
  Time: 6:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<html>
<head>
    <title>$Title$</title>
</head>
<script src="../scripts/jquery-3.3.1.js"></script>
<script type="text/javascript">
    $(function () {
        $(".delete").click(function () {
            //通过这个按钮找到包含name的td，第一个parent找到td，第二个parent找到tr
            var content = $(this).parent().parent().find("td:eq(1)").text().replace("\n", "");
            var flag = confirm("确定要删除" + content + "的信息吗？");
            return flag;
        })
    })
</script>
<body>
<<<<<<< HEAD

<%
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        customer = new Customer();
        session.setAttribute("customer", customer);
        System.out.println("创建一个customer");
    } else {
        System.out.println("从session获得customer");
    }
%>

=======
>>>>>>> 2650e9d977186ee62c1cbc92402afe0853733516
<%--从jsp页面会到主页面--%>
<form method="post" action="../query.do">
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
            <%--当前目录下的jsp--%>
            <td><a href="addCustomer.jsp">addCustomer</a></td>
            <%--<td><input type="button" value="add" onclick="window.location.href='addCustomer.do'"></td>--%>
        </tr>
    </table>
</form>
<br>
<%--<%--%>
<%--    List<Customer> customers = (List<Customer>) request.getAttribute("customers");--%>
<%--    if (customers == null || customers.isEmpty()) {--%>
<%--        return;--%>
<%--    }--%>
<%--%>--%>

<c:if test="${!empty requestScope.customers}">

    <table border="1" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>PHONE</th>
            <th>ADDRESS</th>
            <th>UPDATE/DELETE</th>
        </tr>

        <c:forEach items="${requestScope.customers}" var="customer">
            <tr>
                <td>
                        ${customer.id}
                </td>
                <td> ${customer.name}
                </td>
                <td> ${ customer.phone}
                </td>
                <td>${ customer.address}
                </td>
                <td><a href="edit.do?id=${customer.id}">UPDATE</a>
                    <a href="delete.do?id=${customer.id}" class="delete">DELETE</a>
                </td>
            </tr>

        </c:forEach>

            <%--    <%--%>
            <%--        for (Customer customer : customers) {--%>
            <%--    %>--%>
            <%--    <tr>--%>
            <%--        <td><%= customer.getId()%>--%>
            <%--        </td>--%>
            <%--        <td><%= customer.getName()%>--%>
            <%--        </td>--%>
            <%--        <td><%= customer.getPhone()%>--%>
            <%--        </td>--%>
            <%--        <td><%= customer.getAddress()%>--%>
            <%--        </td>--%>
            <%--        <td><a href="edit.do?id=<%=customer.getId()%>">UPDATE</a>--%>
            <%--            <a href="delete.do?id=<%=customer.getId()%>" class="delete">DELETE</a>--%>
            <%--        </td>--%>
            <%--    </tr>--%>

            <%--    <%--%>
            <%--        }--%>
            <%--    %>--%>
    </table>
</c:if>


</body>

</html>
