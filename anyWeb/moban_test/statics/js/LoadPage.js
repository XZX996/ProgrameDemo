function load(url,callback){
    $.ajax({
        type:"get",
        url:url, //需要获取的页面内容
        async:false,
        success:function(data){
            if(typeof callback == 'function')
            callback(data);
        },
        error:function(e){
            console.log(e);
        }
    });
}