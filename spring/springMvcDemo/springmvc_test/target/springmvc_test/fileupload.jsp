<%--
  Created by IntelliJ IDEA.
  User: Fessi
  Date: 2019/5/8
  Time: 7:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="uploadFile" method="post" enctype="multipart/form-data">
    <input type="file" name="upload">
    <input type="submit">
</form>

<form action="fileupload2" method="post" enctype="multipart/form-data">
    <input type="file" name="upload">
    <input type="submit">
</form>


<form action="fileupload3" method="post" enctype="multipart/form-data">
    <input type="file" name="upload">
    <input type="submit">
</form>

</body>
</html>
