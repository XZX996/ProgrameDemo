var cache=[];

//获取页面缓存
function getCache(url,callback) {
    var data=false;
    $.each(cache, function (i, n) {
        if (n.url == url){
            data=n.data;
            return false;
        }
    });
    callback(data);
}

//设置页面缓存
function setCache(url,data,callback) {
    //console.log(JSON.stringify(data));
    let i=existCache(url);
    if (i<0)/* {
        console.log("替换值"+i);
        cache[i].data=data;
    } else*/ {
        cache.push({"url":url,"data":data});
    }
    if(typeof callback == 'function'){
        callback();
    }

}

function existCache(url) {
    let exit=-1;
    $.each(cache, function (i, n) {
        if (n.url==url)
        {
            exit=i;
            return false;
        }
    });
    return exit;
}


//1. 路由规则
const routes={
    '/':"App.html",
    '/home':"/view/common/home.html",
    '/test':"/view/list/TestLst.html",
    '/formLst':"/view/list/TestLst.html",
};

function load(url,callback){
    // 变更地址栏URL
    //history.pushState(routes[url], url, url);
    //显示保存的数据
    //alert(history.state);
    //replaceState方法的作用是替换当前的历史记录，其他的都与pushState()方法一模一样
    //replaceState(state,title,url)
    $.ajax({
        type:"get",
        url:routes[url], //需要获取的页面内容
        async:false,
        beforeSend: function(XMLHttpRequest) {
            loading();
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
            setTimeout(removeLoading,100);
        },
        error:function(e){
            console.log(e);
        }
    });
}

function load_cache(url,callback){

    var cadata=getCache(url);
    if(cadata!=false){
        console.log("读取缓存");
        callback(cadata);
    }else {
        callback(null);
    }/*else {
        $.ajax({
            type:"get",
            url:routes[url], //需要获取的页面内容
            async:false,
            beforeSend: function(XMLHttpRequest) {
                loading();
                //Pause(this,100000);
            },
            success:function(data){
                if(typeof callback == 'function'){
                    callback(data);
                }
                // alert('s');
            },
            complete: function(XMLHttpRequest, textStatus) {
                //隐藏正在查询图片
                //removeLoading();
                setTimeout(removeLoading,100);
            },
            error:function(e){
                console.log(e);
            }
        });*/
}



function loading(){
    $('#loading').show();
}

function removeLoading(){
    $('#loading').hide();
}


/*
//2. 路由控制类
class Router {
    start() {
        // 点击浏览器后退/前进按钮时会触发window.onpopstate事件, 我们在这时切换到相应页面
        // https://developer.mozilla.org/en-US/docs/Web/Events/popstate
        window.addEventListener('popstate', () => {
            this.load(location.pathname)
        });

        // 打开页面时加载当前页面 在单页面入口文件中要调用start方法
        this.load(location.pathname,callback)
    }

    // 前往path, 变更地址栏URL, 并加载相应页面
    go(path,callback) {
        // 变更地址栏URL
        history.pushState({}, '', path);
        // 加载页面
        this.load(path,callback)
    }

    // 加载path路径的页面
    load(path,callback) {
        // 首页
        //if (path === '/') path = '/foo'
        // 创建页面实例
        //const view = new routes[path]();
        // 调用页面方法, 把页面加载到document.body中
        $.ajax({
            type:"get",
            url:path, //需要获取的页面内容
            async:false,
            success:function(data){
                if(typeof callback == 'function')
                    callback(data);
                // alert('s');
            },
            error:function(e){
                console.log(e);
            }
        })
    }
}
*/
