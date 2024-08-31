
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
            var checkbox = "<td ><input type='checkbox'></td>";
            var roleName = "<td>"+role.name+"</td>";
            var checkButton = "<button type='button' class='btn btn-success btn-xs'><i class='glyphicon glyphicon-check'></i></button>";
            var pencilButton = "<button id='" +roleId+"'type='button' class='btn btn-primary btn-xs pencilBtn'><i class=' glyphicon glyphicon-pencil'></i></button>";
            var removeButton = "<button type='button' class='btn btn-danger btn-xs'><i class=' glyphicon glyphicon-remove'></i></button>";
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






