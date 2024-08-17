<%--
  Created by IntelliJ IDEA.
  User: shuyun
  Date: 2024/8/17
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>错误页面</title>
</head>
<body>
<h1>出错了</h1>
<%--打印错误信息--%>
${requestScope.exception.message}
</body>
</html>
