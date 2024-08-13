<%--
  Created by IntelliJ IDEA.
  User: shuyun
  Date: 2024/8/12
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>出错了</h1>
<!--打印异常信息-->
${requestScope.exception.message}
</body>
</html>
