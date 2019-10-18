function load(url){
    $.ajax({
        type:"get",
        url:url, //需要获取的页面内容
        async:true,
        success:function(data){
            console.log(data);
            return data;
        },
        error:function(e){
            console.log(e);
        }
    });
}