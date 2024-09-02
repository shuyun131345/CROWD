<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">

<%@include file="/WEB-INF/commom/include-head.jsp" %>
<link rel="stylesheet" href="ztree/zTreeStyle.css">
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="crowd/menu-function.js"></script>
<script type="text/javascript">

    $(function () {
        generateMenu();

        //给新增节点按钮绑定单击事件，动态标签需要从静态标签的on()方法绑定动态标签的单击事件
        $("#treeDemo").on("click",".addBtn",function() {
            //将当前节点的id作为新增节点的父id，存到全局变量中
            window.pid = this.id;

            //弹出模态框
            $("#menuAddModal").modal("show");

            //阻止超链接的跳转
            return false;
        });

        //给新增按钮模态框的保存按钮绑定单击事件，获取表单数据，然后发送后端保存
        $("#menuSaveBtn").click(function () {
            //获取表单数据：节点名、url、图标类型
            let nodeName = $.trim($("#menuAddModal [name=name]").val());
            let nodeUrl = $.trim($("#menuAddModal [name=url]").val());
            //被选中的图标
            let nodeIcon = $.trim($("#menuAddModal [name=icon]:checked").val());

            //发送ajax请求
            $.ajax({
                "url": "menu/addMenu.json",
                "type": "post",
                "data": {
                    "id": this.id,
                    "pid": window.pid,
                    "name": nodeName,
                    "url": nodeUrl,
                    "icon": nodeIcon
                },
                "dataType": "json",
                "contentType":"application/json;charset=UTF-8",
                "success": function (response) {
                    let result = response.result;
                    if ("SUCCESS" == result){
                        layer.msg("操作成功");
                    }else {
                        layer.msg("操作失败:"+response.message);
                        return ;
                    }
                },
                "error": function (response) {
                    layer.msg("操作失败，请联系管理员");
                }
            });





        });
    });


</script>
<body>
<%@include file="/WEB-INF/commom/include-bodynav.jsp" %>
<%@include file="/WEB-INF/commom/modal-menu-add.jsp"%>
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

