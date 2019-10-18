<%@ page import="com.example.Onecloud.pojo.hero" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix='fmt' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    hero he=new hero();
    he.setId(12);
    he.setName("sd");
    request.setAttribute("he", he);
%>
<div style="width:500px;border:1px solid lightgray;margin:200px auto;padding:80px">
    列表：
    ${exception}
    <br><br>
    数据：
    <table align='center' border='1' cellspacing='0'>
        <tr>
            <td>EL取值</td>
            <td>JSTL取值</td>
            <td>编辑</td>
            <td>删除</td>
        </tr>
        <c:forEach items="${url}" var="hero" varStatus="st"  >
            <tr>
                <td>${hero.CODE_ID}</td>
                <td><c:out value="${hero.CODE_VALUE}" /></td>
                <td><c:out value="${hero.CODE_CODE}" /></td>
                <td>${he.id}</td>
                <td>
                    <fmt:formatNumber type="number" value="${hero.CODE_ID}" minFractionDigits="2"/>
                </td>
                <td> ${hero.code_cache ge 1? "缓存":"不缓存" }</td>

            </tr>
        </c:forEach>

    </table>
    <input width="400px"  height="400px" type="image" src="D:\JavaDemo\Maya\Springboot_demo\src\main\webapp\upload\15534971161472.jpg" alt="">
    <form action="upload" method="post" enctype="multipart/form-data">
        选择图片:<input type="file" name="file" accept="image/*" /> <br>
        <input type="hidden" name="_method" value="PUT">
        <input type="submit" value="上传">
    </form>
</div>