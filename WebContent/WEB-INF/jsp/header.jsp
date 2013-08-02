<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
    <div class="top_bar clear">
        <!--Top Links Starts-->
        <ul class="top_links">
            <c:if test="${sessionScope.USER_CONTEXT != null }">
            	<li><a href="#">${sessionScope.USER_CONTEXT.userName}</a></li>
            </c:if>
            <li><a href="index.html">首页</a></li>
            <li><a href="myhome.html">个人中心</a></li>
            <!-- 
            <li><a href="myshop.html">我的店铺</a></li>
             -->
            <c:choose>
            	<c:when test="${sessionScope.USER_CONTEXT != null }">
            		<li><a href="logout.html">安全退出</a></li>
            	</c:when>
            	<c:otherwise>
            		<li class="highlight"><a href="login.html">登录或注册</a></li>
            	</c:otherwise>
            </c:choose>
        </ul>
        <!--Top Links Ends-->
    </div>
    <!--Logo Starts-->
    <h1 class="logo"> <a href="index.html"><img src="images/logo.png" /></a> </h1>
    <!--Logo Ends-->
   
    <!--Search Starts-->
    <form class="header_search" action="search.html" method="post"/>
        <div class="form-search">
            <input id="search" type="text" name="keyword" value="" class="input-text" autocomplete="on" placeholder="搜 商品/店名" />
            <button type="submit" title="搜索"></button>
        </div>
    </form>
    <!--Search Ends-->
</header>