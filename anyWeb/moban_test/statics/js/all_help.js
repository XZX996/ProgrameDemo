var base_url="http://localhost:8088";
var time_out=3000;
layui.use('layer', function(){
    layer1 = layui.layer;
});

/**
 * 页面帮助区
 * **/
/*$(function () {
    //监听子页面点击事件，代理调用子页面方法，防止方法混用
    $(".layui-tab-content").click(function (e) {
        if( typeof $(e.target).attr("p_click") != 'undefined'){
            let fn=$(e.target).parents('.Xpage').attr("p-id")+'.'+$(e.target).attr("p_click");
            fn = eval(fn);
        }
    });
});*/
//左侧导航栏放大缩小
var isShow = true;  //定义一个标志位
$('.kit-side-fold').click(function(){
    //选择出所有的span，并判断是不是hidden
    $('.layui-nav-item span').each(function(){
        if($(this).is(':hidden')){
            $(this).show();
        }else{
            $(this).hide();
        }
    });
    //判断isshow的状态
    if(isShow){
        $('.layui-side.layui-bg-black').width(20); //设置宽度
        $('.kit-side-fold i').css('margin-left', '1%');  //修改图标的位置
        $('.kit-side-fold i').removeClass("layui-icon-shrink-right");
        $('.kit-side-fold i').addClass("layui-icon-spread-left");
        //将footer和body的宽度修改
        $('.layui-body').css('left', 60+'px');
        $('.layui-footer').css('left', 60+'px');
        //将二级导航栏隐藏
        $('dd span').each(function(){
            $(this).hide();
        });
        //修改标志位
        isShow =false;
    }else{
        $('.layui-side.layui-bg-black').width(200);
        $('.kit-side-fold i').css('margin-left', '82%');  //修改图标的位置
        $('.kit-side-fold i').addClass("layui-icon-shrink-right");
        $('.kit-side-fold i').removeClass("layui-icon-spread-left");
        $('.layui-body').css('left', 200+'px');
        $('.layui-footer').css('left', 200+'px');
        $('dd span').each(function(){
            $(this).show();
        });
        isShow =true;
    }
});

/**
 * ajax处理区
 * **/
//定义锁，防止重复的提交
var lock = false;
function _base_ajax(url, type, dataType, headers, data, async, timeout,index,success) {
    jQuery.support.cors = true;
    if(!lock) {
        $.ajax({
            url: base_url + url,  //地址
            type: type,      //get,post,put,delete
            headers: headers, //请求头 验证
            async: async,   //true异步，false 同步
            dataType: dataType,  //数据类型 json,html...
            data: data,
            timeout: timeout,   //超时时间
            beforeSend: function (XMLHttpRequest) {
                lock = true;
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (XMLHttpRequest.responseText == "") {
                    alert("请求超时,请检查网络!");
                } else {
                    alert(JSON.stringify(XMLHttpRequest));
                }
                lock = false;
                layer.close(index);
            },
            success: function (d) {
                lock = false;
                if (d.code == '1') {
                    DelToken("JSESSIONID");
                    alert("session失效，重新登录");
                    window.location.href="/login.html";
                    layer.close(index);
                }
                else {
                    layer.close(index);
                    //回调
                    success(d);

                }
            }
        });
    }
}
//基本带回调查询
function base_search(url,index,callback) {
    //url, type, dataType, headers, data, async, timeout, success
    let data = $("form").serialize();
    _base_ajax(url,'POST','JSON',{'JSESSIONID': getToken()},data,true,time_out,index,function () {
        if(typeof callback=='function'){
            callback();
        }
    })
}
//基本带回调查询
function login(url,data,index,callback) {
    //url, type, dataType, headers, data, async, timeout, success
    _base_ajax(url,'POST','JSON',{'JSESSIONID':''},data,true,time_out,index,function (data) {
        if(typeof callback=='function'){
            setToken(data.data);
            callback(data);
        }
    })
}
//基本带回调查询
function loginOut() {
    var index = layer.load(1, {
        shade: [0.1,'#fff'], //0.1透明度的白色背景
    });
    //url, type, dataType, headers, data, async, timeout, success
    _base_ajax('/login/logout','GET','JSON',{'JSESSIONID':getToken()},null,false,time_out,index,function (data) {
            DelToken("JSESSIONID");
        alert("session失效，重新登录");
        window.location.href='/login.html';
    })
}

/**
 * layui 组件封装区
 * **/

/*
自定义layui table表头，隔行换色样式
 */
function custom_style() {
    $('th').css({'background-color': '#5792c6', 'color': '#fff', 'font-weight': '500', 'font-size': '14px'});
}
/*
render table
*/
function render_table(table, elem, height, url, where, cols) {
    return table.render({
        elem: elem
        , height: height
        , url: base_url + url
        , headers: { "JSESSIONID":getToken()}
        ,response:{
            statusName:'code', //规定返回的状态码字段为code
            statusCode:200 //规定成功的状态码味200
        }
        , parseData: function (res) {
            if (res.meta.code == '1') {
                DelToken("JSESSIONID");
                alert("session失效，重新登录");
                window.location.href="/login.html";
            }
            return {
                "code":res.meta.code,
                "msg": res.meta.message,
                "count": res.data.total,
                "data": res.data.list
            };
        }
        , method: 'POST'
        , where: where
        , page: true
        , even: false
        , limits:[10, 20, 30, 50]
        , cols: cols
        , done: function (res, curr, count) {
            custom_style();
        }
    });

}

/*操作jessionId*/
function getToken() {
    if(window.sessionStorage){
        console.log(sessionStorage.getItem('JSESSIONID'));
       return sessionStorage.getItem('JSESSIONID');
    }else{
        return $.cookie('JSESSIONID');
    }

}
function setToken(data) {
    if(window.sessionStorage){
        sessionStorage.setItem('JSESSIONID',data);
    }else{
        $.cookie('JSESSIONID', data, { expires: 7 });
    }
}
function DelToken(key) {
    if(window.sessionStorage){
        sessionStorage.removeItem(key);
    }else{
        $.cookie(key, null);
    }
}