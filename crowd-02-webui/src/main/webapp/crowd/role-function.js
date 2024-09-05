
//角色的权限操作，用于展开权限的树形结构并反显已有权限
function fillAuthTree() {

    //ajax请求后端，查询auth数据
    var authResponse = $.ajax({
        "url": "auth/getAuthList.json",
        "type": "post",
        "dataType":"json",
        "async": false
    });

    if (authResponse.status != 200){
        layer.msg("请求错误，请联系管理员");
        return ;
    }

    //获取权限列表
    //从服务器端查询到的 list 不需要组装成树形结构，这里我们交给zTree 去组装.simpleData为true时，只需指定父id即可
    var authList = authResponse.responseJSON.data;

    //准备ztree配置的对象
    var seting = {
        "data":{
            "simpleData":{
                //开启简单JSON功能
                "enable": true,

                //使用catagoryId关联父节点，不使用默认的pId
                "pIdKey": "catagoryId"
            },
            "key":{
                //使用 title 属性显示节点名称，不用默认的 name 作为属性名了
                "name":"title"
            }
        },
        "check":{
            "enable": true
        }
    };

    //生成树形结构，附着在模态框的静态标签<ul id="authTreeDemo" className="ztree"></ul>
    $.fn.zTree.init($("#authTreeDemo"),seting,authList);

    //获取树形结构的对象
    var zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");

    //展开树形结构的所有节点
    zTreeObj.expandAll(true);

    //查询角色已拥有权限
    var role = {
        "id": window.roleId
    };
    var requestBody = JSON.stringify(role);
    var assignAuthResponse = $.ajax({
        "url": "auth/getAssignAuthList.json",
        "type": "post",
        // "contentType":"application/json;charset=UTF-8",
        "data": requestBody,
        "dataType":"json",
        "async": false
    });

    if (assignAuthResponse.status != 200){
        layer.msg("请求错误，请联系管理员");
        return ;
    }

    //取出已分配权限的列表
    var assignAuth = assignAuthResponse.responseJSON.data;
    //遍历已分配权限列表，把树形结构的节点勾选上
    for (let i = 0; i < assignAuth.length; i++) {

        let authId = assignAuth[i].id;
        //根据id查询树形结构的节点
        let node = zTreeObj.getNodeByParam("id",authId);

        //将该节点设置为选中状态
        //参数2 true表示选中状态，参数3表示不联动（避免选上子树未分配的权限）
        zTreeObj.checkNode(node,true,false);
        
    }

}





//删除角色时，全选复选框状态与当前页面元素选中状态保持一致
function checkedBox(){
    //当前被选中的元素个数和总的个数相等时，说明全部被选中，全选勾上
    let total = $(".itemBox").length;
    let checkedCount = $(".itemBox:checked").length;
    if (checkedCount == 0){
        $("#chechedAllbox").prop("checked",false);
    }else {
        //设置全选按钮复选框的选中状态，用prop给属性复制，true表示被选中
        $("#chechedAllbox").prop("checked",total == checkedCount);
    }
}



//弹出删除模态框，并展示选中的要删除的角色名
function showRemoveModal(roleArray) {

    //弹出模态框
    $("#removeModal").modal("show");

    //先清空模态框
    $("#roleNameDiv").empty();

    //将角色列表存到全局变量，后面点击模态框的确认按钮时可以直接使用全局变量
    window.roleList = [];

    //遍历角色列表，并添加到模态框中展示
    for (let i = 0; i < roleArray.length; i++) {
        let role = roleArray[i];
        var roleName = role.name;
        $("#roleNameDiv").append(roleName + "<br/>");
        window.roleList.push(role);
    }
}


    function generatePage() {

        //1.获取后端分页数据
        var pageInfo = getRemotePageInfo();

        //2.填充表格
        fillTableBody(pageInfo);



    }


    //ajax同步请求获取后端数据
    function getRemotePageInfo() {
        var ajaxResult = $.ajax({
            "url": "role/pageInfo.json",
            "type": "post",
            "data": {
                "keyword": window.keyword,
                "pageNum": window.pageNum,
                "pageSize": window.pageSize
            },
            "dataType": "json",
            "async": false
        });

        console.log(ajaxResult);

        //如果ajax请求没返回200，表示请求出错，打印报错信息
        var ajaxStatus = ajaxResult.status;
        if (200 != ajaxStatus){
            layer.msg("ajax请求出错，状态码为："+ajaxStatus);
            return null;
        }

        var resultEntity = ajaxResult.responseJSON;
        //请求正常，判断请求结果是否为SUCCESS
        var result = resultEntity.result;
        if ("SUCCESS" == result){
            //成功则返回role的pageInfo
            return resultEntity.data;
        }else{
            //失败则显示失败信息
            layer.msg(resultEntity.message);
            return null;
        }
    }


    //遍历后端返回的role列表，填充角色信息
    function fillTableBody(pageInfo) {

        //清空角色列表中的旧内容
        $("#rolePageBody").empty();

        //清除导航条，在没有结果时不显示导航条
        $("#Pagination").empty();

        var roleList = pageInfo.list;
        //没有角色信息返回
        if (roleList == null || roleList == undefined || roleList.length == 0){
            $("#rolePageBody").append("<tr><td colspan='4' align='center'>抱歉！没有查询到您搜索的数据！</td></tr>");
        }

        //遍历角色列表，填充信息
        for (let i = 0; i < roleList.length; i++) {
            var role = roleList[i];
            var roleId = role.id;

            var tSortNumber ="<td>"+(i+1)+"</td>";
            var checkbox = "<td ><input type='checkbox' class='itemBox' id='" +roleId+"'></td>";
            var roleName = "<td>"+role.name+"</td>";
            var checkButton = "<button id='" +roleId+"' type='button' class='btn btn-success btn-xs checkButton'><i class='glyphicon glyphicon-check'></i></button>";
            var pencilButton = "<button id='" +roleId+"'type='button' class='btn btn-primary btn-xs pencilBtn'><i class=' glyphicon glyphicon-pencil'></i></button>";
            var removeButton = "<button id='" +roleId+"' type='button' class='btn btn-danger btn-xs removeBtn'><i class=' glyphicon glyphicon-remove'></i></button>";
            var tdBtn = "<td>"+checkButton+" "+pencilButton+" "+removeButton+"</td>";
            var tr = "<tr>"+tSortNumber+checkbox+roleName+tdBtn+"</tr>";

            $("#rolePageBody").append(tr);
        }
        //生成分页导航条
        generateNavigator(pageInfo);
    }


    //生成分页导航条
    function generateNavigator(pageInfo) {
        //总记录数
        var total = pageInfo.total;

        //分页属性设置
        var pageProperties = {
            //边缘页
            num_edge_entries: 3,
            //主体页数
            num_display_entries: 4,
            //回调函数
            callback: paginationCallBack,
            //当前页：pagination的页码从0开始，pageInfo的页码从1开始，所以要减1
            current_page: pageInfo.pageNum -1,
            prev_text: "上一页",
            next_text: "下一页",
            //每页显示的数量
            items_per_page: pageInfo.pageSize
        };


        //调用pagination函数
        $("#Pagination").pagination(total,pageProperties);

    }

    //pagination分页设置的回调函数：回调函数是由系统或框架调用，不是自己调用
    //用户点击页码或者 上一页、下一页时调用该函数
    //形参pageIndex是pagination的页码，从0开始
    function paginationCallBack(pageIndex,jQuery) {
        //修改全局变量的页码
        window.pageNum = pageIndex + 1;
        //调用分页函数，加载该页的角色信息
        generatePage();

        //由于每一个页码按钮都是超链接，所以在这个函数最后取消超链接的默认行为
        return false;
    }






