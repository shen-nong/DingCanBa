<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav>
     <ul class="primary_nav">
		<c:choose>
			<c:when test="${type == 'home'}">
			 <li class="active"><a href="myhome.html">个人信息</a></li>
	         <li><a href="mycollect.html">我的收藏</a></li>
	         <li><a href="myfriends.html">我的好友</a></li>
	         <li><a href="ubill.html">买家账单</a></li>
			</c:when>
			<c:when test="${type == 'bill' }">
			 <li><a href="myhome.html">个人信息</a></li>
	         <li><a href="mycollect.html">我的收藏</a></li>
	         <li><a href="myfriends.html">我的好友</a></li>
	         <li class="active"><a href="ubill.html">买家账单</a></li>
			</c:when>
			<c:when test="${type == 'collect' }">
			 <li><a href="myhome.html">个人信息</a></li>
	         <li class="active"><a href="mycollect.html">我的收藏</a></li>
	         <li><a href="myfriends.html">我的好友</a></li>
	         <li><a href="ubill.html">买家账单</a></li>
			</c:when>
			<c:when test="${type == 'friend' }">
			 <li><a href="myhome.html">个人信息</a></li>
	         <li><a href="mycollect.html">我的收藏</a></li>
	         <li class="active"><a href="myfriends.html">我的好友</a></li>
	         <li><a href="ubill.html">买家账单</a></li>
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