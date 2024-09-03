<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">

<%@include file="/WEB-INF/commom/include-head.jsp" %>
<script type="text/javascript">
    $(function () {

        //右移按钮绑定单击事件
        $("#toRightBtn").click(function () {
            // select 是标签选择器
            // :eq(0)表示选择页面上的第一个
            // :eq(1)表示选择页面上的第二个
            // “>”表示选择子元素
            // :selected 表示选择“被选中的”option
            // appendTo()能够将 jQuery 对象追加到指定的位置
            $("select:eq(0)>option:selected").appendTo("select:eq(1)");

            //移动后，已分配列表取消选中状态
            $("select:eq(1)>option:selected").prop("selected",false);
        });


        //左移按钮绑定单击事件
        $("#toLeftBtn").click(function () {
            // select 是标签选择器
            // :eq(0)表示选择页面上的第一个
            // :eq(1)表示选择页面上的第二个
            // “>”表示选择子元素
            // :selected 表示选择“被选中的”option
            // appendTo()能够将 jQuery 对象追加到指定的位置
            $("select:eq(1)>option:selected").appendTo("select:eq(0)");

            //移动后，未分配列表取消选中状态
            $("select:eq(0)>option:selected").prop("selected",false);
        });

        //确定按钮绑定单击事件，将已分配角色列表交给后端去保存
        $("#assignConfirmBtn").click(function () {

            //获取已分配角色列表


        });



    });


</script>

<body>
<%@include file="/WEB-INF/commom/include-bodynav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/commom/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form role="form" class="form-inline">
                        <div class="form-group">
                            <label for="unAssignRoles">未分配角色列表</label><br>
                            <select class="form-control" multiple="" size="10" style="width:100px;overflow-y:auto;">
                                <c:forEach items="${requestScope.unAssignRoles}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li id="toRightBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br>
                                <li id="toLeftBtn" class="btn btn-default glyphicon glyphicon-chevron-left"
                                    style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label for="assignRoles">已分配角色列表</label><br>
                            <select class="form-control" multiple="" size="10" style="width:100px;overflow-y:auto;">
                                <c:forEach items="${requestScope.assignRoles}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="modal-footer">
                            <button id="assignConfirmBtn" type="button" class="btn btn-danger"><i
                                    class="glyphicon glyphicon-ok"></i> OK
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>