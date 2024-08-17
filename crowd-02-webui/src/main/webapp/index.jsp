<%--
  Created by IntelliJ IDEA.
  User: shuyun
  Date: 2024/8/17
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
<head>
    <title>首页</title>
    <!-- http://localhost:8080/atcrowdfunding02-admin-webui/test/ssm.html -->
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

    <%--引入jquery--%>
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>

    <%--jquery代码--%>
    <script type="text/javascript">

        //页面加载完成后
        $(function () {

            //绑定一个单击事件
            $("#btn1").click(function () {

                //1.准备要发送的数据
                var student = {
                    "name": "shuyun",
                    "age": 18,
                    "gender": "男"
                }

                //2.转换成json格式
                var requestBody = JSON.stringify(student);

                //3.发送ajax请求
                $.ajax({
                    "url": "test/ajax.html",
                    "type": "post",
                    "data": requestBody,
                    "contentType": "application/json;charset=UTF-8",
                    "dataType": "json",
                    "success": function (response) {
                        console.log(response);
                    },
                    "error": function (response) {
                        console.log(response);
                    }
                });
            });
        });

    </script>

</head>
<body>
<a href="test/admin.html">首页跳转测试<a/>

    <br/>
    <br/>
    <button id="btn1">Send String</button>

</body>
</html>
