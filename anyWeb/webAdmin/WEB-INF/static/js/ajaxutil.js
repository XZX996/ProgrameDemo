const base_url='http://11.101.4.105:8088';
var layer1='';
layui.use('layer', function(){
    layer1 = layui.layer;
});

function doget(url,callback){
    $.ajax({
        type:"GET",
        url:base_url+url,
        data: '',
        dataType: "json",
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", window.sessionStorage.getItem("token"));
        },
        success:function(data){
            if(IsHref(data))
                callback(data);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus+':'+errorThrown);
            layer1.error("连接服务器失败,状态:"+XMLHttpRequest.status);
        },
    });
}

function dopost(url,Jsondata,callback) {
    $.ajax({
        type:"POST",
        url:base_url+url,
        data: Jsondata,
        dataType: "json",
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", window.sessionStorage.getItem("token"));
        },
        success:function(data){
            if(IsHref(data))
                callback(data);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus+':'+errorThrown);
            layer1.msg("连接服务器失败,状态:"+XMLHttpRequest.status);
        },
    });
}

function IsHref(re){
    //未登陆跳转至登陆页面
    if (re.meta.code == '1') {
        window.location.href="../../../WEB-INF/jsp/common/login.html";
        return false;
    }
    //访问无权限资源
    else if (re.meta.code == '3') {
        window.location.href="../../../WEB-INF/jsp/common/403.html";
        return false;
    }

    //访问无权限资源
    else if (re.meta.code == '500') {
        layer1.alert(re.meta.message);
        return false;
    }
    //访问无权限资源
    else if (re.meta.code != '200') {
        layer1.alert(re.meta.message);
    }
    return true;
}


