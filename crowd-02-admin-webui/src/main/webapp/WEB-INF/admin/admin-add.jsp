<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">

<%@include file="/WEB-INF/commom/include-head.jsp" %>

<body>
<%@include file="/WEB-INF/commom/include-bodynav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/commom/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">新增</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <form role="form" action="admin/addAdminCheck.html" method="post">
                        <p>${requestScope.exception.message}</p>
                        <div class="form-group">
                            <label for="loginAcct">登陆账号</label>
                            <input type="text" class="form-control" id="loginAcct" name="loginAcct" placeholder="请输入登陆账号">
                        </div>
                        <div class="form-group">
                            <label for="userName">用户名称</label>
                            <input type="text" class="form-control" id="userName" name="userName" placeholder="请输入用户名称">
                        </div>
                        <div class="form-group">
                            <label for="userPswd">密码</label>
                            <input type="text" class="form-control" id="userPswd" name="userPswd" placeholder="请输入密码">
                        </div>
                        <div class="form-group">
                            <label for="email">邮箱地址</label>
                            <input type="email" class="form-control" id="email" name="email" placeholder="请输入邮箱地址">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>
                        <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增
                        </button>
                        <button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

