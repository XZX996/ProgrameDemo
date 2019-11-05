//1. 路由规则
const routes_map={
    '/p1':"/view/Vue/p1.html",
    '/p2':"/view/Vue/p1.html",
};

function load_page(url,callback){
    // 变更地址栏URL
    //history.pushState(routes[url], url, url);
    //显示保存的数据
    //alert(history.state);
    //replaceState方法的作用是替换当前的历史记录，其他的都与pushState()方法一模一样
    //replaceState(state,title,url)
    $.ajax({
        type:"get",
        url:routes_map[url], //需要获取的页面内容
        async:false,
        beforeSend: function(XMLHttpRequest) {
            //loading();
            //Pause(this,100000);
        },
        success:function(data){
            if(typeof callback == 'function')
                callback(data);
            // alert('s');
        },
        complete: function(XMLHttpRequest, textStatus) {
            //隐藏正在查询图片
            //removeLoading();
            //setTimeout(removeLoading,100);
        },
        error:function(e){
            console.log(e);
        }
    });
}