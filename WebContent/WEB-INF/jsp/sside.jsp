<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="category departments">
	<li class="header">本店推荐</li>
	<c:forEach items="${recommend }" var="var">
		<li><a href="product.html?id=${var.goodsId }">${var.goodsName }</a></li>
	</c:forEach>
</ul>
<ul class="category price">
    <li class="header">排序方式</li>
    <li><a href="#">按价格</a></li>
    <li><a href="#">按销量</a></li>
</ul>