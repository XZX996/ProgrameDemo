<!DOCTYPE html>
<html>
<head>
    <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
    <title>开始使用layui</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.min.js"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/LoadPage.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<div id="head">
    <p>sds</p>
</div>
<div id="Content">
    <p>sds</p>
</div>
<div id="foot">
    <p>sds</p>
</div>

<!-- 你的HTML代码 -->


<script>
    //一般直接写在一个js文件中
    layui.use(['layer', 'form'], function(){
        var layer = layui.layer
            ,form = layui.form;

        layer.msg('Hello World');
    });

    //$("#Content").html(/home.html);
    //var html=load("/home/home.html");
    //$("#Content").html(html);
</script>
</body>
</html>