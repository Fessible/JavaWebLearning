<%--
  Created by IntelliJ IDEA.
  User: rhm
  Date: 2019/4/9
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>AddCustomer</title>
</head>
<body>
<%--<%--%>
<%--    Object message = request.getAttribute("message");--%>
<%--    if (message != null) {--%>

<%--%>--%>
<c:if test="${message !=null}">
<font color="red">${message}</font>
</c:if>
<%--<%--%>
<%--    }--%>

<%--%>--%>
<form method="post" action="addCustomer.do">
    <table>
        <tr>
            <td>Address:</td>
<%--            <td><input type="text" name="address"--%>
<%--                       value="<%=request.getParameter("address")==null?"":request.getParameter("address")%>"></td>--%>
         <td><input type="text" name="address"
                       value="${address}"></td>
        </tr>
        <tr>
            <td>Phone:</td>
<%--            <td><input type="text" name="phone"--%>
<%--                       value="<%=request.getParameter("phone")==null?"":request.getParameter("phone")%>"></td>--%>
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
            <td><input type="submit" value="add"></td>
            <td><a href=""></a></td>
        </tr>
    </table>
</form>
</body>
</html>
