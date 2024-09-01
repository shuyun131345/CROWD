<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">

<%@include file="/WEB-INF/commom/include-head.jsp" %>
<link rel="stylesheet" href="ztree/zTreeStyle.css">
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">

    $(function () {

        //加载树形结构菜单目录
        $.ajax({
            "url": "menu/rootMenu.json",
            "type": "post",
            "dataType": "json",
            "success": function (response) {
                let result = response.result;
                if ("SUCCESS" == result){
                    //获取菜单目录
                    var root = response.data;

                    //创建json对象，用于存储树形结构的设置
                    var settings = {};

                    //初始化树形目录
                    $.fn.zTree.init($("#treeDemo"),settings,root);

                }else {
                    layer.msg("请求失败："+response.message);
                    return;
                }

            },
            "error": function (response) {
                layer.msg("请求失败，请联系管理员");
            }
        });


    });


</script>
<body>
<%@include file="/WEB-INF/commom/include-bodynav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/commom/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <div class="panel panel-default">
                <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

