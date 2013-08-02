<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav>
     <ul class="primary_nav">
     	<c:choose>
     		<c:when test="${type == 'index'}">
			 <li class="active"><a href="index.html">购物大厅</a></li>
	         <li><a href="shopList.html">外卖商家</a></li>
	         <li><a href="goodsList.html">百货选购</a></li> 
	         <li><a href="hotelList.html">旅店预定</a></li>  
	         <!--   
	         <li><a href="honest.html">诚实勇敢</a></li>
	          -->
	         <li><a onclick="alert('评教外挂即将下线')">秒评外挂</a></li>
     		</c:when>
     		<c:when test="${type == 'honest'}">
			  <li><a href="index.html">购物大厅</a></li>
	         <li><a href="shopList.html">外卖商家</a></li>
	         <li><a href="goodsList.html">百货选购</a></li> 
	         <li><a href="hotelList.html">旅店预定</a></li>    
	         <!--
	         <li class="active"><a href="honest.html">诚实勇敢</a></li> 	
	         -->
	         <li><a href="pingjiao.html">秒评外挂</a></li>	
     		</c:when>
     		<c:when test="${type == 'shop'}">
			  <li><a href="index.html">购物大厅</a></li>
	         <li class="active"><a href="shopList.html">外卖商家</a></li>
	         <li><a href="goodsList.html">百货选购</a></li> 
	         <li><a href="hotelList.html">旅店预定</a></li>  
	         <!--
	         <li><a href="honest.html">诚实勇敢</a></li>   		
	         -->
	         <li><a href="pingjiao.html">秒评外挂</a></li>
     		</c:when>
     		<c:when test="${type == 'hotelList'}">
			  <li><a href="index.html">购物大厅</a></li>
	         <li><a href="shopList.html">外卖商家</a></li>
	         <li><a href="goodsList.html">百货选购</a></li> 
	         <li class="active"><a href="hotelList.html">旅店预定</a></li>    
	         <!--
	         <li><a href="honest.html">诚实勇敢</a></li> 		
	         -->
	         <li><a href="pingjiao.html">秒评外挂</a></li>
     		</c:when>
     		<c:when test="${type == 'goodsList'}">
			  <li><a href="index.html">购物大厅</a></li>
	         <li><a href="shopList.html">外卖商家</a></li>
	         <li class="active"><a href="goodsList.html">百货选购</a></li> 
	         <li><a href="hotelList.html">旅店预定</a></li>    
	         <!--
	         <li><a href="honest.html">诚实勇敢</a></li> 	
	         -->
	         <li><a href="pingjiao.html">秒评外挂</a></li>	
     		</c:when>
     		<c:when test="${type == 'snap'}">
			  <li><a href="index.html">购物大厅</a></li>
	         <li><a href="shopList.html">外卖商家</a></li>
	         <li><a href="goodsList.html">百货选购</a></li> 
	         <li><a href="hotelList.html">旅店预定</a></li>    
	         <!--
	         <li><a href="honest.html">诚实勇敢</a></li> 	
	         -->
	         <li><a href="pingjiao.html">秒评外挂</a></li>
     		</c:when>
     		<c:otherwise>
     		 <li><a href="index.html">购物大厅</a></li>
	         <li><a href="shopList.html">外卖商家</a></li>
	         <li><a href="goodsList.html">百货选购</a></li> 
	         <li><a href="hotelList.html">旅店预定</a></li> 
	         <!--
	         <li><a href="honest.html">诚实勇敢</a></li>
	         -->
	         <li><a href="pingjiao.html">秒评外挂</a></li>
     		</c:otherwise>
     	</c:choose>

     </ul>
     <div class="minicart" title="去餐车下订单"> <a href="cart.html"> 去餐车，下订单
     <span class="item"><b>${totalNum }</b> 件 /</span> <span class="price"><b>&yen; ${totalPrice }</b></span> </a>
     </div>
</nav>