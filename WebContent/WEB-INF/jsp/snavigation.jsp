<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav>
     <ul class="primary_nav">
		<c:choose>
			<c:when test="${type == 'home'}">
			 <li class="active"><a href="myshop.html">店铺预览</a></li>
			 <li><a href="shopSet.html">商铺装修</a></li>
	         <li><a href="managegoods.html">商品管理</a></li>
	         <li><a href="addgoods.html">发布新品</a></li>
	         <li><a href="sbill.html">卖家账单</a></li>
			</c:when>
			<c:when test="${type == 'set' }">
			 <li><a href="myshop.html">店铺预览</a></li>
			 <li class="active"><a href="shopSet.html">商铺装修</a></li>
	         <li><a href="managegoods.html">商品管理</a></li>
	         <li><a href="addgoods.html">发布新品</a></li>
	         <li><a href="sbill.html">卖家账单</a></li>
			</c:when>
			<c:when test="${type == 'manage' }">
			 <li><a href="myshop.html">店铺预览</a></li>
			 <li><a href="shopSet.html">商铺装修</a></li>
	         <li class="active"><a href="managegoods.html">商品管理</a></li>
	         <li><a href="addgoods.html">发布新品</a></li>
	         <li><a href="sbill.html">卖家账单</a></li>
			</c:when>
			<c:when test="${type == 'add' }">
			 <li><a href="myshop.html">店铺预览</a></li>
			 <li><a href="shopSet.html">商铺装修</a></li>
	         <li><a href="managegoods.html">商品管理</a></li>
	         <li class="active"><a href="addgoods.html">发布新品</a></li>
	         <li><a href="sbill.html">卖家账单</a></li>
			</c:when>
			<c:when test="${type == 'bill' }">
			 <li><a href="myshop.html">店铺预览</a></li>
			 <li><a href="shopSet.html">商铺装修</a></li>
	         <li><a href="managegoods.html">商品管理</a></li>
	         <li><a href="addgoods.html">发布新品</a></li>
	         <li class="active"><a href="sbill.html">卖家账单</a></li>
			</c:when>
		</c:choose>     
     </ul>
     <!-- 
     <div class="minicart"> <a href="#" class="minicart_link"> <span class="item"><b>2</b> 条</span> <span class="price"><b>新消息</b></span> </a>
         <div class="cart_drop"> <span class="darw"></span>
             <ul>
                 <li><img src="images/mini_c_item1.png" /><a href="#">Rum Cheese Cake 朗姆芝士</a> <span class="price">$49.90</span></li>
                 <li><img src="images/mini_c_item2.png" /><a href="#">Rum Cheese Cake 朗姆芝士</a> <span class="price">$12.90</span></li>
                 <div class="cart_bottom">
                     <a href="leisure_cart.html">知道了</a></div>
             </ul>
　　　　　　</div>
     </div>
      -->
</nav>