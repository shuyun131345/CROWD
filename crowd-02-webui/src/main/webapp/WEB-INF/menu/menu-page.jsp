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
            let pid = window.pid;

            //发送ajax请求
            $.ajax({
                "url": "menu/addMenu.json",
                "type": "post",
                "data": {
                    "pid": pid,
                    "name": nodeName,
                    "url": nodeUrl,
                    "icon": nodeIcon
                },
                "dataType": "json",
                "success": function (response) {
                    let result = response.result;
                    if ("SUCCESS" == result){
                        layer.msg("操作成功");
                        //重新加载目录，注意要保证后端处理完成后再加载，否则会出现加载不到最新数据
                        generateMenu();

                    }else {
                        layer.msg("操作失败:"+response.message);
                        return ;
                    }
                },
                "error": function (response) {
                    layer.msg("操作失败，请联系管理员");
                }
            });

            //关闭模态框
            $("#menuAddModal").modal("hide");

            //清空表单
            // jQuery 对象调用 click()函数，里面不传任何参数，相当于用户点击了一下
            $("#menuResetBtn").click();
        });


        //删除按钮绑定单击事件，弹出删除模态框。因为是动态生成标签，所以需要用静态标签的on()方法
        $("#treeDemo").on("click",".removeBtn",function () {
            //将当前节点的id作为新增节点的父id，存到全局变量中
            window.id = this.id;
            //弹出模态框
            $("#menuConfirmModal").modal("show");

            //获取zTreeObj对象，该对象存储了树形目录的所有节点信息
            let zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");

            //根据id来查询节点对象
            var currentNode = zTreeObj.getNodeByParam("id",window.id);

            //将该节点的名称显示到模态框中
            $("#removeNodeSpan").html(" 【 <i class='"+currentNode.icon+"'></i>"+currentNode.name+"】");

            //阻止超链接的跳转
            return false;
        });

        //模态框 确认删除 按钮绑定单击事件，发送请求给后端，删除节点
        $("#confirmBtn").click(function () {
            $.ajax({
                "url": "menu/removeMenu.json",
                "data": {
                    "id": window.id
                },
                "type": "post",
                "dataType": "json",
                "success": function (response) {
                    let result = response.result;
                    if ("SUCCESS" == result){
                        layer.msg("操作成功");
                        //重新加载目录，注意要保证后端处理完成后再加载，否则会出现加载不到最新数据
                        generateMenu();
                    }else {
                        layer.msg("操作失败:"+response.message);
                        return ;
                    }
                },
                "error": function (response) {
                    layer.msg("请求失败，请联系管理员");
                }
            });

            //关闭模态框
            $("#menuConfirmModal").modal("hide");
        });


        //编辑按钮弹出模态框
        $("#treeDemo").on("click",".editBtn",function () {
            //弹出模态框
            $("#menuEditModal").modal("show");

            //将当前节点的id存到全局变量中
            window.id = this.id;

            //反显要更新的菜单信息，根据id到树形目录中查找
            let zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
            let currentNode = zTreeObj.getNodeByParam("id",window.id);

            //反显 节点名称、url、图标信息
            $("#menuEditModal [name=name]").val(currentNode.name);
            $("#menuEditModal [name=url]").val(currentNode.url);
            // 回显 radio 可以这样理解：被选中的 radio 的 value 属性可以组成一个数组，
            // 然后再用这个数组设置回 radio，就能够把对应的值选中
            $("#menuEditModal [name=icon]").val([currentNode.icon]);

            //阻止超链接的跳转
            return false;
        });


        //模态框的更新按钮绑定单击事件，发送ajax请求，让后端更新节点信息
        $("#menuEditBtn").click(function () {
            //获取表单数据：节点名、url、图标类型
            let nodeName = $.trim($("#menuEditModal [name=name]").val());
            let nodeUrl = $.trim($("#menuEditModal [name=url]").val());
            //被选中的图标
            let nodeIcon = $.trim($("#menuEditModal [name=icon]:checked").val());

            //发送ajax请求
            $.ajax({
                "url": "menu/editMenu.json",
                "type": "post",
                "data": {
                    "id": window.id,
                    "name": nodeName,
                    "url": nodeUrl,
                    "icon": nodeIcon
                },
                "dataType": "json",
                "success": function (response) {
                    let result = response.result;
                    if ("SUCCESS" == result){
                        layer.msg("操作成功");
                        //重新加载目录，注意要保证后端处理完成后再加载，否则会出现加载不到最新数据
                        generateMenu();

                    }else {
                        layer.msg("操作失败:"+response.message);
                        return ;
                    }
                },
                "error": function (response) {
                    layer.msg("操作失败，请联系管理员");
                }
            });

            //关闭模态框
            $("#menuEditModal").modal("hide");
        });





    });


</script>
<body>
<%@include file="/WEB-INF/commom/include-bodynav.jsp" %>
<%@include file="/WEB-INF/commom/modal-menu-add.jsp"%>
<%@include file="/WEB-INF/commom/modal-menu-remove.jsp"%>
<%@include file="/WEB-INF/commom/modal-menu-edit.jsp"%>
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

