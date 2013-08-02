<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>订餐吧_不仅吃省点,而且吃好点</title> 
<!--CSS-->
<link rel="stylesheet" href="css/styles.css" />
<!--Javascript-->
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.flexslider.js"></script>
<script type="text/javascript" src="js/jquery.easing.js"></script>
<script type="text/javascript" src="js/jquery.jcarousel.js"></script>
<script type="text/javascript" src="js/form_elements.js"></script>
<script type="text/javascript" src="js/custom.js"></script>
<script type="text/javascript">
function addFriends(field){
	  result=$.ajax({url:"addfriends.html?id="+field,async:false});
	  if(result.responseText == "1"){
		  location="user.html?id="+field;
	  }else if(result.responseText == "2"){
		  alert("抱歉,您还未登录,无法添加好友!");
		  location="login.html";
	  }else if(result.responseText == "0"){
		  alert("你们已经是朋友了!");
	  }else{
		  alert("添加失败,请重试!");
	  }
}
</script>
<!--[if lt IE 9]>
    <script src="js/html5.js"></script>
<![endif]-->
<!-- mobile setting -->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>
<div class="wrapper">
    <div class="header_container">
        <!--Header Starts-->
        <c:import url="/header.html" charEncoding="UTF-8"></c:import>
        <!--Header Ends-->
    </div>
    <div class="navigation_container">
        <!--Navigation Starts-->
		<c:import url="/navigation.html" charEncoding="UTF-8">
			<c:param name="type" value="home"></c:param>
		</c:import>
        <!--Navigation Ends-->
    </div>
    <div class="section_container">
        <!--Mid Section Starts-->
        <section>
            <!--SIDE NAV STARTS-->
            <div id="side_nav">
                <div class="sideNavCategories">
                	<img src="${userPhoto}" alt="Img" width="200px" height="280px">
                </div>
            </div>
            <!--SIDE NAV ENDS-->
            <!--MAIN CONTENT STARTS-->
            <div id="main_content">
                <ul class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li><a href="leisure_listing.html">用户</a></li>
                    <li class="active"><a href="#">${userName}</a></li>
                </ul>
                <!--Toolbar-->
                <div class="toolbar">
                    <div class="sortby">
                        <label>店铺: <c:if test="${empty user}">客官,你俩还不是好友,赶快加为好友交换名片把!</c:if>${user.userName}</label>
                    </div>
                </div>
                <div class="toolbar">
                    <div class="sortby">
                        <label>手机: <c:if test="${empty user}">客官,你俩还不是好友,赶快加为好友交换名片把!</c:if>${user.location}</label>
                    </div>
                </div>
                <div class="toolbar">
                    <div class="sortby">
                        <label>地址: <c:if test="${empty user}">客官,你俩还不是好友,赶快加为好友交换名片把!</c:if>${user.location}</label>
                    </div>
                </div>
                <c:if test="${empty user}">
                <div class="toolbar" >
                    <div class="sortby">
                        <button name="submit" type="submit" class="subbutton brown_btn" onclick="addFriends(${userId})">加好友</button></div>
                </div>
                </c:if>
                
                <!--Toolbar ENDS-->
            </div>
            <!--MAIN CONTENT -->
            <!--Newsletter_subscribe Starts-->
            <div class="subscribe_block">
			<c:import url="/newsletter.html" charEncoding="UTF-8"></c:import>
			</div>
            <!--Newsletter_subscribe Ends-->
        </section>
        <!--Mid Section Ends-->
    </div>
    <div class="footer_container">
        <!--Footer Starts-->
		<footer>
		<c:import url="/footer.html" charEncoding="UTF-8"></c:import>
		</footer>
        <!--Footer Ends-->
    </div>
</div>

</body>
</html>
