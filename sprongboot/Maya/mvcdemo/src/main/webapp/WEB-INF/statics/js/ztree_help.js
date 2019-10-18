var shows=0; //1显示
//所显示的tree
var $myDiv=null;
var myDiv=null;

var zTreeObj;
//全选状态
var ChecState=true;  //true 全选
//清除选择项
function cleanOrg() {
    if($("#orgid").val()!=null){
        $("#orgid").val(null);
        $("#ORGNAME").val(null);
    }
}
//清除选择项关闭tree
function closeTree(e){
    cleanOrg();
    myDiv.style.display="none";
    shows=0;
    e=e||window.event;
    e.stopPropagation();
}
//保存关闭
function closeTree1(e){
    var Orgs=[];
    var nodes = zTreeObj.getCheckedNodes(true);
    for (var i in nodes) {
            Orgs.push({"orgName":nodes[i].orgName,"orgid":nodes[i].orgid});
    }
    addOrgs(Orgs);
    myDiv.style.display="none";
    shows=0;
    e=e||window.event;
    e.stopPropagation();
}

//初始化单选数据源
function getOrgData(d){
    myDiv = $myDiv[0];
    lw_post("/common/getAllSysOrganizebyId",{"orgid":d.orgid}, initTree);
    if(document!=null)
        document.addEventListener("click",function(){
            if(shows==2){
                myDiv.style.display="none";
                shows=0;
            }
            shows++;
        });
    if(myDiv!=null)
        myDiv.addEventListener("click",function(event){
            event=event||window.event;
            event.stopPropagation();
        });
}
//初始单选树结构
function initTree(data) {
    $.each(data, function (i, n) {
        data[i].icon = "../../../../images/tree/01.png";
    });
    var zNodes = data;
    zTreeObj = $.fn.zTree.init($("#OrgTree"), setting, zNodes);
    zTreeObj.expandAll(true); //展开所有
}
//初始化复选框数据源
function getOrgData1(d){
    myDiv = $myDiv[0];
    lw_post("/common/getAllSysOrganizebyId",{"orgid":d.orgid}, initTree1);
    if(document!=null)
        document.addEventListener("click",function(){
            if(shows==2){
                myDiv.style.display="none";
                shows=0;
            }
            shows++;
        });
    if(myDiv!=null)
        myDiv.addEventListener("click",function(event){
            event=event||window.event;
            event.stopPropagation();
        });
}
//初始复选框树结构
function initTree1(data) {
    $.each(data, function (i, n) {
        data[i].icon = "../../../../images/tree/01.png";
    });
    var zNodes = data;
    zTreeObj = $.fn.zTree.init($("#OrgTree"), setting1, zNodes);
    zTreeObj.expandAll(true); //展开所有
}

//单选父节点
var setting = {
    view: {
        showIcon: true
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "orgid",
            pIdKey: "pid",
            rootPId: 0
        },
        key: {
            name: "orgName"
        }
    },
    callback: {
        onClick: onClick
    }
};
//复选框树节点
var setting1 = {
    view: {
        showIcon: true
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "orgid",
            pIdKey: "pid",
            rootPId: 0
        },
        key: {
            name: "orgName"
        }
    },
    check: {
        enable: true,
        chkStyle: "checkbox",
        chkboxType: { "Y": "p", "N": "p" }   //Y:勾选（参数：p:影响父节点），N：不勾（参数s：影响子节点）[p 和 s 为参数]
    },
    callback: {
        onClick: onClick1
    }
};
//回调函数，选择节点的值保存起来
function onClick(e, treeId, treeNode) {
    $("#orgid").val(treeNode.orgid);
    if($("#optype").length){
      $("#optype").val(treeNode.levelNum);
    }
    $("#ORGNAME").val(treeNode.orgName);
    myDiv.style.display="none";
    e.stopPropagation();
}
//回调函数，选择节点的值保存起来
function onClick1(e, treeId, treeNode) {
    if(ChecState){
        CheckChildNodes(treeNode);
        ChecState=false;
    }
    else{
        CancelChildNodes(treeNode);
        ChecState=true;
    }
    e.stopPropagation();
}
$(function () {
    if($('#tree').length){
       $myDiv=$('#tree');
        //加载树组织结构
        var html='<ul id="OrgTree" class="ztree" style="-moz-user-select: none;height: 93%;overflow:auto;overflow-x: hidden;"></ul>';
        html+= '<a style="width:104%; margin-left: -14px;" onclick="closeTree(this.event)" class="layui-btn  layui-btn-xs">清除选择</a>';
        $myDiv.append(html);
        if(!$(".All").length)
        lw_getOne("/common/getCurrentUser", {}, getOrgData);
        else
            getOrgData('');
        //监听div点击事件
        $myDiv.on('click',function () {
            cleanOrg();
        });

    }
    else{
        $myDiv=$('#mutlitree');
        //加载树组织结构
        var html='<ul id="OrgTree" class="ztree" style="-moz-user-select: none;height: 93%;overflow:auto;overflow-x: hidden;"></ul>';
        html+= '<a style="width:100%; margin-left: 0px;" onclick="closeTree1(this.event)" class="layui-btn  layui-btn-xs">确认选择</a>';
        $myDiv.append(html);
        lw_getOne("/common/getCurrentUser", {}, getOrgData1);
    }
});

//全选
function CheckAll() {

    zTreeObj.checkAllNodes(true);
}

//全取消
function CancelAll() {
    zTreeObj.checkAllNodes(false);
}

//选中节点子节点
function CheckChildNodes(treeNode) {
    var childNodes = zTreeObj.transformToArray(treeNode);//把该节点的下的所有数据转换为数组
    for(i = 0; i < childNodes.length; i++) {
        zTreeObj.checkNode(childNodes[i]);
    }
}
//取消节点子节点
function CancelChildNodes(treeNode) {
    var childNodes = zTreeObj.transformToArray(treeNode);//把该节点的下的所有数据转换为数组
    for(i = 0; i < childNodes.length; i++) {
        zTreeObj.checkNode(childNodes[i],false,false);//取消子节点的选中
    }

}

//显示组织树
function showTree(obj){
    $myDiv.show();
    shows=1;
}
