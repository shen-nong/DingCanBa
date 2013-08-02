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
			<c:param name="type" value="bill"></c:param>
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
                <ul class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li><a href="leisure_listing.html">卖家中心</a></li>
                    <li class="active"><a href="#">商品管理</a></li>
                </ul>
                <!--Toolbar-->
                <div class="toolbar">
                    <div class="sortby">
                        <label>排序 :</label>
                        <select>
                            <option />价格
                            <option />名称
                        </select>
                    </div>
                </div>
                <!--Toolbar-->
                <!--Product List Starts-->
                <div class="products_list products_slider">
        <div class="cart_table">
          <table class="data-table cart-table" id="shopping-cart-table" cellpadding="0" cellspacing="0">
			<tr>
              <th class="align_center" width="6%">物品</th>
              <th class="align_center" width="6%">购买的人</th>
              <th class="align_center" width="12%">单价</th>
              <th class="align_center" width="10%">购入</th>
              <th class="align_center" width="12%">时间</th>
            </tr>
			<c:forEach items="${bill}" var="sale">
				<tr>
	              <td width="30%"><a class="pr_name" href="product.html?id=${sale.goodsId }">${sale.goodsName }</a></td>
	              <td class="align_left" width="20%"> <a class="pr_name" href="user.html?id=${sale.buyerId }">${sale.userName }</a></td>
	              <td class="align_center vline"><span class="price">￥ ${sale.price }</span></td>
	              <td class="align_center vline"><span class="price">${sale.quantity }</span></td>
	              <td class="align_center vline"><span class="price">${sale.date }</span></td>
	            </tr>
			</c:forEach>            
          </table>
        </div>
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
