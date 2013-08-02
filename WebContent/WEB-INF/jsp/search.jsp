<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
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
		  alert("添加成功!");
	  }else if(result.responseText == "2"){
		  alert("抱歉,您还未登录,无法添加好友!");
		  location="login.html";
	  }else if(result.responseText == "0"){
		  alert("你们已经是朋友了!");
	  }else{
		  alert("添加失败,请重试!");
	  }
}
function addCollection(field){
	  result=$.ajax({url:"addCollection.html?id="+field,async:false});
	  if(result.responseText == "1"){
		  alert("添加成功!");
	  }else if(result.responseText == "2"){
		  alert("抱歉,您还未登录,无法加入收藏!");
		  location="login.html";
	  }else if(result.responseText == "0"){
		  alert("您已经收藏了该商家!");
	  }else{
		  alert("添加失败,请重试!");
	  }
}
function buyNow(field){
	  result=$.ajax({url:"addCart.html?id="+field,async:false});
	  if(result){
		  location="checkout.html";
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
			<c:param name="type"></c:param>
		</c:import>
        <!--Navigation Ends-->
    </div>
    <div class="section_container">
        <!--Mid Section Starts-->
        <section>
        <section>
            <!--SIDE NAV STARTS-->
            <div id="side_nav">
                <div class="sideNavCategories">
					<c:import url="/goodsBillboard.html"  charEncoding="UTF-8"></c:import>
					<c:import url="/shopBillboard.html"  charEncoding="UTF-8"></c:import>
                </div>
            </div>
            <!--SIDE NAV ENDS-->
            <!--MAIN CONTENT STARTS-->
            <div id="main_content">
                <ul class="breadcrumb">
                    <li><a>产品查询到(<span style="color:red">${fn:length(goodslist)}</span>)条结果</a></li>
                </ul>
                <hr/>
                <!--Product List Starts-->
                <div class="products_list products_slider">
                    <ul>
						 <c:forEach items="${goodslist }" var="item">
	                        <li> <a class="product_image" href="product.html?id=${item.goodsId }"><img src="${item.goodsUrl }" /></a>
								<div class="product_info">
									<h3><a href="product.html?id=${item.goodsId }">${item.goodsName }</a></h3>
									<small>${item.shortDescription }</small> </div>
								<div class="price_info"> <a href="#" onclick="buyNow(${goods.goodsId})">+ 立即购买</a>
									<button class="price_add" title="" type="button"><span class="pr_price">&yen; ${item.priceDiscount }</span><span class="pr_add" onclick="addCart(${item.goodsId })">加入餐车</span></button>
								</div>
							</li>
						 </c:forEach>                    
                    </ul>
                </div>
                <!--Product List Ends-->
                <ul class="breadcrumb">
                    <li><a>商家查询到(<span style="color:red">${fn:length(shoplist)}</span>)条结果</a></li>
                </ul>
                <hr/>
                <!--Product List Starts-->
                <div class="products_list products_slider">
                    <ul>
						 <c:forEach items="${shoplist }" var="item">
	                        <li> <a class="product_image" href="shop.html?id=${item.shopId }"><img src="${item.signboardUrl }" /></a>
								<div class="product_info">
									<h3><a href="shop.html?id=${item.shopId }">${item.shopName }</a></h3>
									<small>${item.shopDescription }</small> </div>
								<div class="price_info"> <a>被收藏</a>
                            <button class="price_add" title="" type="button"><span class="pr_price">次 153</span><span class="pr_add" onclick="addCollection(${item.shopId})">加入收藏</span></button>
                        </div>
							</li>
						 </c:forEach>                    
                    </ul>
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

<!--CUSTOMIZE侧栏-->
<c:import url="/slide_nav.html" charEncoding="UTF-8"></c:import>
<!--CUSTOMIZE-->
</body>
</html>