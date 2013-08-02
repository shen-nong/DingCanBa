<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
$(document).ready(function(){
	  $("#updateInfoId").click(function(){
		  $.post("setShopInfo.html",
			  {
			    feixin:$("#feixinId").val(),
			    shopName:$("#shopNameId").val(),
			    description:$("#descriptionId").val()
			  },
			  function(data,status){
			  	if(data){
			  		alert("保存成功!");
			  	}else {
			  		alert("保存失败,请重试!");
			  	}
			  });
	  });
});
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
		<c:import url="/snavigation.html" charEncoding="UTF-8">
			<c:param name="type" value="set"></c:param>
		</c:import>
        <!--Navigation Ends-->
    </div>
    <div class="section_container">
        <!--Mid Section Starts-->
        <section>
            <!--SIDE NAV STARTS-->
            <div id="side_nav">
                <div class="sideNavCategories">
					<img src="${shop.signboardUrl }" alt="Img">
                	<form:form name="shoplogo" method="post" action="setLogo.html" modelAttribute="goodsForm" enctype="multipart/form-data">
					<input type="button" value="一键修改商标Logo" onclick="path.click()" class="subbutton brown_btn">  
					<input type="file" id="path" style="display:none" name="files[0]" onchange="document.shoplogo.submit()">
                	</form:form>
                </div>
            </div>
            <!--SIDE NAV ENDS-->
            <!--MAIN CONTENT STARTS-->
            <div id="main_content">
                <ul class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li><a href="leisure_listing.html">卖家中心</a></li>
                    <li class="active"><a href="#">商铺装修</a></li>
                </ul>
                <!--Toolbar-->
	                <div class="toolbar">
	                    <div class="sortby">
	                        <label>我的店名:</label>
	                        <input type="text" name="contactName" id="shopNameId" value="${shop.shopName }" placeholder="掌柜的,来一个霸气的吧" />
	                    </div>
	                </div>
	                <div class="toolbar">
	                    <div class="sortby">
	                        <label>送餐电话:</label>
	                        <input type="text" name="contactName" id="feixinId" value="${shop.feixin }" placeholder="请填写您的手机号" />
	                    </div>
	                </div>
	                <div class="toolbar">
	                    <div class="sortby">
	                        <label>营业时间:</label>
	                        <input type="text" name="contactName" id="descriptionId" value="${shop.shopDescription }" placeholder="这个可以不用填哦" />
	                    </div>
	                </div>
	                <div class="toolbar">
	                    <div class="sortby">
	                        <button name="submit" type="submit" class="subbutton brown_btn" id="updateInfoId">确定 !</button>
	                    </div>
	                </div>
                <!--Toolbar-->
                <!--Product List Starts-->
                <div class="products_list products_slider">
                </div>
                <!--Product List Ends-->
            </div>
            <!--MAIN CONTENT ENDS-->
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
