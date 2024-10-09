

//菜单的权限操作，用于展开权限的树形结构并反显已有权限
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

    //查询菜单已拥有权限
    var assignAuthResponse = $.ajax({
        "url": "auth/getMenuAssignAuthList.json",
        "type": "post",
        "data": {
            "id": window.menuId
        },
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




//生成树形目录并初始化
function generateMenu(){

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
                var settings = {
                    "view": {
                        "addDiyDom": myAddDiyDom,
                        "addHoverDom": myAddHoverDom,
                        "removeHoverDom": myRemoveHoverDom
                    },
                    "data":{
                        "key":{
                            "url":"maomi"
                        }
                    }
                };

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
}

//鼠标移出时，移除菜单按钮
function myRemoveHoverDom(treeId, treeNode) {

    //找到按钮组的id
    let btnGroupId = treeNode.tId + "_btnGroup";

    //移除
    $("#" + btnGroupId).remove();

}


//鼠标移入时显示按钮组件
function myAddHoverDom(treeId, treeNode) {

    // 按钮组的标签结构：<span><a><i></i></a><a><i></i></a></span>
    // 按钮组出现的位置：节点中 treeDemo_n_a <a>标签超链接的后面

    //先找到附着的a标签，然后在a标签后面动态添加按钮
    let achorId = treeNode.tId + "_a";

    //为了在移除按钮时能准确定位到按钮所在span，给动态添加的按钮增加有规律的id
    let btnGroupId = treeNode.tId + "_btnGroup";

    //如果这个按钮的id已经存在，就不重复添加了
    if ($("#" + btnGroupId).length > 0) {
        return;
    }

    //要增加的按钮
    let editBtn = "<a id='"+treeNode.id+"' class='editBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='修改节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-edit rbg '></i></a>";
    let removeBtn = "<a id='"+treeNode.id+"' class='removeBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#'  title='删除节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg '></i></a>";
    let addBtn = "<a id='"+treeNode.id+"' class='addBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#'  title='增加节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg '></i></a>";
    //权限分配按钮
    let checkBtn = "<a id='"+treeNode.id+"' class='checkButton btn btn-success dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#'  title='权限分配'><i class='glyphicon glyphicon-check'></i></a>";

    //根据当前节点的层级，动态添加按钮。1.叶子节点只有修改和删除按钮；2.中间节点，有子节点的菜单，没有删除按钮；3.根节点只有增加按钮
    //要展示的按钮组合
    let showBtn = "";

    //当前节点的层级：0-根节点，1-中间节点，2-叶子节点
    let level = treeNode.level;

    if (0 == level) {
        showBtn = addBtn;
    } else if (1 == level) {
        showBtn = editBtn + addBtn;
        //没有子节点可以移除
    }
    if (treeNode.children.length == 0) {
        showBtn = showBtn + removeBtn
    } else if (2 == level) {
        showBtn = editBtn + removeBtn;
    }

    //最后加上权限分配按钮
    showBtn = showBtn + checkBtn;
    //最终添加的标签
    let btnHTML = "<span id='" + btnGroupId + "'>" + showBtn + "</span>"

    //将按钮增加到a标签的后面
    $("#" + achorId).after(btnHTML);
}


//替换树形目录的图标
function myAddDiyDom(treeId, treeNode) {

    //treeId是整个菜单目录附着的静态ul标签的Id
    // console.log(treeId);
    //treeNode是当前节点的全部数据，包括后端返回的属性值
    // console.log(treeNode);

    //需要将span标签的class属性替换为数据库的值
    // zTree 生成 id 的规则
    // 例子：treeDemo_7_ico
    // 解析：ul 标签的 id_当前节点的序号_功能
    // 提示：“ul 标签的 id_当前节点的序号”部分可以通过访问 treeNode 的 tId 属性得到
    // 根据 id 的生成规则拼接出来 span 标签的 id
    let spanId = treeNode.tId + "_ico";

    //替换span标签的class属性
    $("#" + spanId).removeClass().addClass(treeNode.icon);
}




