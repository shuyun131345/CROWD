<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <!-- http://localhost:8080/atcrowdfunding02-admin-webui/test/ssm.html -->
    <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript">

        //页面完成加载后
        $(function ()
        {

            //绑定一个单击事件
            $("#btn1").click(function(){

                //要发送的数据
                var student = {
                    "name" : "shuyun",
                    "age" : 18,
                    "gender" : "男"
                }

                //转换成json
                var requestBody = JSON.stringify(student);

                //发送ajax请求
                $.ajax({
                    "url" : "send/object/student.html",
                    "type" : "post",
                    "data" : requestBody,
                    "contentType" : "application/json;charset=UTF-8",
                    "dataType" : "json",
                    "success" : function (response) {
                        console.log(response);
                    },
                    "error" : function (response) {
                        console.log(response);
                    }
                })

            });

        });

    </script>
</head>
<body>

<a href="test/ssm.html">测试SSM整合环境</a>

<br/>
<br/>

<button id="btn1">Send String</button>





</body>
</html>