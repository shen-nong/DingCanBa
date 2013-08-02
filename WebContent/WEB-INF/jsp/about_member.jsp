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
function addCart(field){
	  result=$.ajax({url:"addCart.html?id="+field,async:false});
	  if(result){
		  alert("添加成功!");
	  }else{
		  alert(result);
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
function gotoPage(field){
	alert("");
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
		</c:import>
        <!--Navigation Ends-->
    </div>
    <div class="section_container">
        <!--Mid Section Starts-->
        <section>
            <!--SIDE NAV STARTS-->
            <div id="side_nav">
                <div class="sideNavCategories">
					<c:import url="/about_side.html"  charEncoding="UTF-8"></c:import>
                </div>
            </div>
            <!--SIDE NAV ENDS-->
            <!--MAIN CONTENT STARTS-->
            <div id="main_content">
                <ul class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li class="active"><a>团队成员</a></li>
                </ul>
                <!--Toolbar-->
                <div class="toolbar">
                	<div class="show_no">
                        <label>第  ${goodsPage.pageNo }/${goodsPage.totalPage} 页</label>
                    </div>
                </div>
                <!--Toolbar-->
                <!--Product List Starts-->
                <div class="products_list products_slider">
                    <ul>
						 <c:forEach items="${goodsPage.list }" var="item">
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
                <!--Toolbar-->
                <div class="toolbar">
                    <div class="viewby">
                        <a class="list" href="find.html?id=${cateId}&pageNo=${goodsPage.previousPageNo}"></a>
                        <a class="grid" href="find.html?id=${cateId}&pageNo=${goodsPage.nextPageNo}"></a> 
                    </div>
                    <div class="show_no">
                        <label>第  ${goodsPage.pageNo }/${goodsPage.totalPage} 页</label>
                    </div>
                </div>
                <!--Toolbar-->
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
    <div class="/footer_container">
        <!--Footer Starts-->
		<footer>
		<c:import url="/footer.html" charEncoding="UTF-8"></c:import>
		</footer>
        <!--Footer Ends-->
    </div>
</div>

</body>
</html>
