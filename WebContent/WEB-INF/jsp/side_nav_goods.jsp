<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>产品排行榜</h1>
<ul class="category departments">
    <li class="header">口味最佳</li>
    	<c:forEach items="${newgoods }" var="goods">
    	<li><a href="product.html?id=${goods.goodsId }">${goods.goodsName }</a></li>
    	</c:forEach>
</ul>
<ul class="category departments">
    <li class="header">价格最实惠</li>
		<c:forEach items="${newgoods }" var="goods">
    	<li><a href="product.html?id=${goods.goodsId }">${goods.goodsName }</a></li>
    	</c:forEach>
</ul>
<ul class="category departments">
    <li class="header">本月人气产品</li>
    	<c:forEach items="${newgoods }" var="goods">
    	<li><a href="product.html?id=${goods.goodsId }">${goods.goodsName }</a></li>
    	</c:forEach>
</ul>
<ul class="category departments">
    <li class="header">最新产品</li>
    	<c:forEach items="${newgoods }" var="goods">
    	<li><a href="product.html?id=${goods.goodsId }">${goods.goodsName }</a></li>
    	</c:forEach>
</ul>