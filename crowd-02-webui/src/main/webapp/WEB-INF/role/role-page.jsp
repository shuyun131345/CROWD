<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">

<%@include file="/WEB-INF/commom/include-head.jsp" %>
<link rel="stylesheet" href="css/pagination.css"/>
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<script type="text/javascript" src="crowd/role-function.js"></script>
<script type="text/javascript">
    $(function () {
        //1.初始化的分页参数
        window.keyword = "";
        window.pageNum = 1;
        window.pageSize = 10;
        //调用分页函数进行初始化页面
        generatePage();

        //2.关键字查询角色信息
        $("#searchBtn").click(function () {
            //将查询的关键字赋值给全局变量，在调用分页函数的时候使用到keyword
            window.keyword = $("#keywordInput").val();
            //调用分页函数
            generatePage();
        });

        //3.新增按钮,显示模态框
        $("#showAddModalBtn").click(function () {
            //显示模态框
            $("#addModal").modal("show");
        });

        //4.给新增角色的模态框的保存按钮绑定单击事件，点击保存时,获取输入的角色信息然后发送后端保存数据
        $("#saveRoleBtn").click(function () {
            //获取到输入的 角色名称.从模态框的后代中找到name=roleName的元素，就是输入框。然后获取输入框的值就是角色名
            var roleName = $("#addModal [name=roleName]").val();

            //发送ajax请求给后端保存
            $.ajax({
                "url": "role/saveRole.json",
                "type": "post",
                "data": {
                    "roleName": roleName
                },
                "dataType": "json",
                "success": function (response) {
                    var result = response.result;
                    if ("SUCCESS" == result) {
                        //提示操作成功
                        layer.msg("操作成功");

                        //将页码定位到最后一页
                        window.pageNum = 9999;

                        //重新加载页面
                        generatePage();

                    } else {
                        layer.msg("操作失败："+response.message);
                    }
                },
                "error": function (response) {
                    //请求失败
                    layer.msg(response.status+" "+response.statusText);
                }
            });

            //关闭模态框
            $("#addModal").modal("hide");

            //清空模态框中的输入内容
            $("#addModal [name=roleName]").val("");
        });

        //5.修改角色信息单击事件，弹出修改模态框
        //注意，传统的绑定事件在pagination翻页后会失效，要使用jQuery的on()函数
        //先找到所有动态生成的元素附着的静态元素，即<tbody>标签
        //on()函数的第一个参数表示 绑定的事件；第二个参数表示 需要真正绑定事件的元素选择器；第三个参数表示 事件响应函数
        $("#rolePageBody").on("click",".pencilBtn",function () {
            //弹出 更新模态框
            $("#editModal").modal("show");

            //获取当前元素的角色名称:当前元素的父元素的前一个元素，就是角色名称
            var roleName = $(this).parent().prev().text();

            //将角色名称反显到模态框的输入框中
            $("#editModal [name=roleName]").val(roleName);

            //为了更新操作能获取到角色id，把角色id放到全局变量中
            window.roleId = this.id;
        });

        //6.角色更新按钮绑定单击事件，获取输入框的内容，然后发送ajax请求让后端更新角色信息
        $("#updateRoleBtn").click(function () {

            //获取输入框的内容
            var roleName = $("#editModal [name=roleName]").val();

            //发送ajax请求
            $.ajax({
                "url": "role/update.json",
                "type": "post",
                "data": {
                    "id": window.roleId,
                    "name": roleName
                },
                "dataType": "json",
                "success": function (response) {
                    var result = response.result;
                    if ("SUCCESS" == result){
                        layer.msg("操作成功");
                        //重新加载页面，当前页面且保留了关键字。因为页码和关键字做成了全局变量
                        generatePage();
                    }else {
                        layer.msg("更新失败："+response.message);
                    }
                },
                "error": function (response) {
                    layer.msg("请求失败，请联系管理员");
                }
            });

            //关闭模态框
            $("#editModal").modal("hide");
            //清空模态框的输入内容
            $("#editModal [name=roleName]").val("");

        });

        //7.给单个删除按钮绑定单击事件，因为是动态生成的，不能用传统的单击事件绑定（翻页后失效）
        //on()函数的第一个参数表示 绑定的事件；第二个参数表示 需要真正绑定事件的元素选择器；第三个参数表示 事件响应函数
        $("#rolePageBody").on("click",".removeBtn",function () {

            //获取当前元素的父元素的前一个元素的内容，即角色名称
            var name = $(this).parent().prev().text();

            //构造角色数组
            var roleArray = [{
                "id":this.id,
                "name":name
            }];

            //调用删除模态框的公用方法
            showRemoveModal(roleArray);

        });

        //8.单击模态框的确定删除按钮时，发送ajax请求后端，删除角色
        //角色列表在js方法中存到全局变量，在这里直接拿来即可
        $("#removeRoleBtn").click(function () {
            //获取角色列表，判断是否为空
            var roleList = window.roleList;
            if (roleList.length == 0){
                layer.msg("请至少选择一个删除！");
                return ;
            }

            //转成json格式
            var roleRequest = JSON.stringify(roleList);
            //ajax请求
            $.ajax({
                "url": "role/removeRoles.json",
                "type": "post",
                "data": roleRequest,
                "contentType":"application/json;charset=UTF-8",
                "dataType": "json",
                "success": function (response) {
                    var result = response.result;
                    if ("SUCCESS" == result){
                        layer.msg("操作成功");
                        //重新加载页面，当前页面且保留了关键字。因为页码和关键字做成了全局变量
                        generatePage();
                    }else {
                        layer.msg("更新失败："+response.message);
                    }

                },
                "error": function (response) {
                    layer.msg("操作失败，请联系管理员");
                }
            });

            //关闭模态框
            $("#removeModal").modal("hide");

        });





    });

</script>

<body>
<%@include file="/WEB-INF/commom/include-bodynav.jsp" %>
<%@include file="/WEB-INF/commom/modal-role-add.jsp" %>
<%@include file="/WEB-INF/commom/modal-role-edit.jsp" %>
<%@include file="/WEB-INF/commom/modal-role-remove.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/commom/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="keywordInput" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="searchBtn" type="button" class="btn btn-warning"><i
                                class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button type="button" id="roleRemove" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" class="btn btn-primary" style="float:right;" id="showAddModalBtn"><i
                            class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox" id="chechedAllbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="rolePageBody">
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

