<%@ page import="bean.Customer" %><%--
  Created by IntelliJ IDEA.
  User: rhm
  Date: 2019/4/10
  Time: 9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<form method="post" action="update.do">
    <c:set var="name" value="${customer!=null? customer.getName(): name}"></c:set>
    <c:set var="id" value="${customer!=null? customer.getId(): id}"></c:set>
    <c:set var="address" value="${customer!=null? customer.getAddress(): address}"></c:set>
    <c:set var="phone" value="${customer!=null? customer.getPhone(): phone}"></c:set>

    <%--    <%--%>
    <%--        Customer customer = (Customer) request.getAttribute("customer");--%>
    <%--        String name = null;--%>
    <%--        String phone = null;--%>
    <%--        String address = null;--%>
    <%--        String id = null;--%>

    <%--        if (customer != null) {--%>
    <%--            name = customer.getName();--%>
    <%--            id = customer.getId() + "";--%>
    <%--            address = customer.getAddress();--%>
    <%--            phone = customer.getPhone();--%>
    <%--        } else {--%>
    <%--            name = request.getParameter("name");--%>
    <%--            phone = request.getParameter("phone");--%>
    <%--            address = request.getParameter("address");--%>
    <%--            id = request.getParameter("id");--%>
    <%--        }--%>
    <%--        Object message = request.getAttribute("message");--%>
    <%--        if (message != null) {--%>

    <%--    %>--%>


    <c:if test="${message!=null}">
        <font color="red">${message}
        </font>
    </c:if>
    <%--    <%--%>
    <%--        }--%>

    <%--    %>--%>
    <%--使用隐藏域--%>
    <input type="hidden" name="id" value="${id}">
    <input type="hidden" name="oldName" value="${name}">
    <table>
        <tr>
            <td>Address:</td>
            <td><input type="text" name="address"
                       value="${address}"></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><input type="text" name="phone"
                       value="${phone}"></td>
        </tr>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name"
                       value="${name}"
            ></td>
        </tr>
        <tr>
            <td><input type="submit" value="update"></td>
        </tr>
    </table>
    <%--    <table>--%>
    <%--        <tr>--%>
    <%--            <td>Address:</td>--%>
    <%--            <td><input type="text" name="address"--%>
    <%--                       value="<%=address%>"></td>--%>
    <%--        </tr>--%>
    <%--        <tr>--%>
    <%--            <td>Phone:</td>--%>
    <%--            <td><input type="text" name="phone"--%>
    <%--                       value="<%=phone%>"></td>--%>
    <%--        </tr>--%>
    <%--        <tr>--%>
    <%--            <td>Name:</td>--%>
    <%--            <td><input type="text" name="name"--%>
    <%--                       value="<%=name%>"--%>
    <%--            ></td>--%>
    <%--        </tr>--%>
    <%--        <tr>--%>
    <%--            <td><input type="submit" value="update"></td>--%>
    <%--        </tr>--%>
    <%--    </table>--%>
</form>
</body>
</html>
