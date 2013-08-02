<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>商家排行榜</h1>
<ul class="category departments">
    <li class="header">口味最佳</li>
    	<c:forEach items="${newshop }" var="shop">
    	<li><a href="shop.html?id=${shop.shopId }">${shop.shopName }</a></li>
    	</c:forEach>    
</ul>
<ul class="category departments">
    <li class="header">环境最佳</li>
		<c:forEach items="${newshop }" var="shop">
    	<li><a href="shop.html?id=${shop.shopId }">${shop.shopName }</a></li>
    	</c:forEach>  
</ul>
<ul class="category departments">
    <li class="header">本月人气商铺</li>
		<c:forEach items="${newshop }" var="shop">
    	<li><a href="shop.html?id=${shop.shopId }">${shop.shopName }</a></li>
    	</c:forEach>  
</ul>
<ul class="category departments">
    <li class="header">最新特约商铺</li>
		<c:forEach items="${newshop }" var="shop">
    	<li><a href="shop.html?id=${shop.shopId }">${shop.shopName }</a></li>
    	</c:forEach>  
</ul>