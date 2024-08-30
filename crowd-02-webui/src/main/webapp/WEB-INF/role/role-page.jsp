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

    });

</script>

<body>
<%@include file="/WEB-INF/commom/include-bodynav.jsp" %>
<%@include file="/WEB-INF/commom/modal-role-add.jsp" %>
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
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
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
                                <th width="30"><input type="checkbox"></th>
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

