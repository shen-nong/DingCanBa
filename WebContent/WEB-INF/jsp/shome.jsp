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
<!--[if lt IE 9]>
    <script src="js/html5.js"></script>
<![endif]-->
<!-- mobile setting -->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-cache" />
</head>
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
                    <img src="${shop.signboardUrl }" alt="Img">
                    <h1>${shop.shopName }</h1>
                    <c:import url="/recommend.html" charEncoding="UTF-8">
                    	<c:param name="id" value="${shop.shopId }"></c:param>
                    </c:import>
                </div>
            </div>
            <!--SIDE NAV ENDS-->
            <!--MAIN CONTENT STARTS-->
            <div id="main_content">
                <div class="category_banner"> <img src="images/promo_cat_banner.jpg" /> </div>
                <ul class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li><a href="leisure_listing.html">蛋糕名录</a></li>
                    <li class="active"><a href="#">奶油蛋糕</a></li>
                </ul>
                <!--Toolbar-->
                <div class="toolbar">
                    <div class="sortby">
                        <label>排序方式 :</label>
                        <select>
                            <option />价格
                            <option />名称
                        </select>
                    </div>
                    <div class="show_no">
                        <label>每页显示 :</label>
                        <select>
                            <option />12 件
                            <option />24 件
                        </select>
                    </div>
                </div>
                <!--Toolbar-->
                <!--Product List Starts-->
                <div class="products_list products_slider">
                    <ul>
                    <c:forEach items="${goodsList}" var="goods">
                    	<li> <a class="product_image" href="product.html?id=${goods.goodsId }"><img src="${goods.goodsUrl }" /></a>
							<div class="product_info">
								<h3><a href="product.html?id=${goods.goodsId }">${goods.goodsName }</a></h3>
								<small>${goods.shortDescription }</small> </div>
							<div class="price_info"> <a href="#">+ 立即购买</a>
								<button class="price_add" title="" type="button"><span class="pr_price">￥${goods.priceDiscount }</span><span class="pr_add">加入餐车</span></button>
							</div>
						</li>
                    </c:forEach>
                    </ul>
                </div>
                <!--Product List Ends-->
                <!--Toolbar-->
                <div class="toolbar">
                    <div class="sortby">
                        <label>跳转到 :</label>
                        <select>
                            <option />首页
                            <option />尾页
                        </select>
                    </div>
                    <div class="viewby">
                        <a class="list" href="#"></a> <a class="grid" href="#"></a> </div>
                    <div class="show_no">
                        <label>第 </label>
                        <select>
                            <option />1
                            <option />2
                        </select>
						<label> 页</label>
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
