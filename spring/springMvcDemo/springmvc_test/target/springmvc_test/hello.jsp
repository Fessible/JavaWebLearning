<%--
  Created by IntelliJ IDEA.
  User: rhm
  Date: 2019-05-07
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="js/jquery-3.4.1.min.js"></script>
<script>
    //页面加载后，指定单机事件
    $(function () {
        $("#btn").click(function () {
            // alert("Hello")
            $.ajax({
                url: "testAjax",
                contentType: "application/json;charset=UTF-8",
                data: '{"username":"hehe","phone":"123456"}',
                dataType: "json",
                type: "post",
                success: function (data) {
                    //data为服务器响应的数据

                    alert(data.username + data.phone)

                }
            })
        });
    })

</script>

<body>

<a href="testString">testString</a>
<button id="btn" value="使用ajax发送请求"></button>

</body>
</html>
