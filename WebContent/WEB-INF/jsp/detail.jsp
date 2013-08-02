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
<script>
$(function(){
	var t = $("#text_box");
	var s = $("#total");
	$("#add").click(function(){		
		t.val(parseInt(t.val())+1)
		setTotal();
	})
	$("#min").click(function(){
		t.val(parseInt(t.val())-1)
		setTotal();
	})
	function setTotal(){
		$("#total").html('共计 : '+(parseInt(t.val())*parseInt(s.attr("title"))).toFixed(2)+' &yen;');
	}
	setTotal();
})
</script>

<!-- 打分 -->
<style type="text/css">
/* starbox */
.starbox{width:480px;margin:20px auto;height:30px;}
.fl{float:left;display:inline;}
.s_name{float:left;display:block;width:60px;text-align:right;}

.square_ul{background:url(images/star.png) no-repeat 0 -222px;width:146px;z-index:10;position:relative;height:20px;}
.square_ul li{float:left;margin-right:1px;width:29px;height:20px;}
.square_ul li a{display:block;height:20px;position:absolute;left:0;top:0;text-indent:-999em;}
.square_ul li .active-square{background:url(images/star.png) no-repeat;}
.square_ul li .square-1{width:29px;background-position:0 -243px;z-index:50;}
.square_ul li .square-2{width:58px;background-position:0 -264px;z-index:40;}
.square_ul li .square-3{width:87px;background-position:0 -285px;z-index:30;}
.square_ul li .square-4{width:116px;background-position:0 -306px;z-index:20;}
.square_ul li .square-5{width:145px;margin-right:0;background-position:0 -327px;z-index:10;}
.s_result_square{padding:4px 0 0 9px;}
</style>
<script type="text/javascript">
function addCart(field){
	  result=$.ajax({url:"addCart.html?id="+field+"&count="+$('#text_box').val(),async:false});
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
$(function(){
	$('.square_ul a').hover(function(){
		$(this).addClass('active-square');
		$(this).parents('.starbox').find('.s_result_square').css('color','#c00').html($(this).attr('title'))
	},function(){
		$(this).removeClass('active-square');
		$(this).parents('.starbox').find('.s_result_square').css('color','#333').html('请打分')
	});
	
	$('.square_ul a').click(function(){
		alert($(this).parents('.starbox').find('.s_result_square').html());
	});
})
</script>
<!-- 打分结束 -->
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
    <ul class="breadcrumb">
      <li><a href="#">首页</a></li>
      <li><a href="category.html">产品分类</a></li>
      <li class="active"><a href="shop.html?id=${product.shopId }">${product.shopName }</a></li>
    </ul>
    <!--PRODUCT DETAIL STARTS-->
    <div id="product_detail"> 
      <!--Product Left Starts-->
      <div class="product_leftcol"> <img src="${product.bigImage }" /><span class="pr_info">最近订购过的人</span>
        <ul class="pr_gallery">
        	<c:forEach items="${newBuyer }" var="user">
        		<li><a href="user.html?id=${user.userId }"><img src="${user.photo65 }"/></a></li>
        	</c:forEach>
        </ul>
      </div>
      <!--Product Left Ends--> 
      <!--Product Right Starts-->
      <div class="product_rightcol"> <small class="pr_type">已售出: ${product.orderCount } 件</small>
        <h1>${product.goodsName}</h1>
        <p class="short_dc" /> 
		${product.goodsDescription }	
        <div class="pr_price"> <big>&yen;${product.priceDiscount }</big> <small>&yen;${product.priceDiscount }</small> </div>
        <div class="size_info">
          <div class="size_sel">
            <label>数量 :</label>
            <input id="min" name="" type="button" value="-" />
			<input id="text_box" name="" type="text" value="1" size="1px" style="text-align:center" />
			<input id="add" name="" type="button" value="+" />
          </div>
          <div class="color_sel">
            <label id="total" title="${product.priceDiscount }">共计 : &yen;</label>
          </div>
        </div>
        <div class="add_to_buttons">
          <button onclick="addCart(${product.goodsId})" class="add_cart">加 入 购 物 车</button>
          <span>or</span>
          <ul>
            <li><a href="#" onclick="buyNow(${product.goodsId})">立即购买</a></li>
          </ul>
        </div>
        <div >
<div class="starbox">
	<span class="s_name">总体：</span>
	<ul class="square_ul fl">
		<li><a class="square-1" title="差" href="#"></a></li>
		<li><a class="square-2" title="一般" href="#"></a></li>
		<li><a class="square-3 active-square" title="好" href="#"></a></li>
		<li><a class="square-4" title="很好" href="#"></a></li>
		<li><a class="square-5" title="非常好" href="#"></a></li>
	</ul>
	<span class="s_result_square fl">请打分</span>
</div>

<div class="starbox">
	<span class="s_name">口味：</span>
	<ul class="square_ul fl">
		<li><a class="square-1" title="差" href="#"></a></li>
		<li><a class="square-2 active-square" title="一般" href="#"></a></li>
		<li><a class="square-3" title="好" href="#"></a></li>
		<li><a class="square-4" title="很好" href="#"></a></li>
		<li><a class="square-5" title="非常好" href="#"></a></li>
	</ul>
	<span class="s_result_square fl">请打分</span>
</div>

<div class="starbox">
	<span class="s_name">环境：</span>
	<ul class="square_ul fl">
		<li><a class="square-1" title="差" href="#"></a></li>
		<li><a class="square-2" title="一般" href="#"></a></li>
		<li><a class="square-3" title="好" href="#"></a></li>
		<li><a class="square-4 active-square" title="很好" href="#"></a></li>
		<li><a class="square-5" title="非常好" href="#"></a></li>
	</ul>
	<span class="s_result_square fl">请打分</span>
</div>

        </div>
      </div>
    <!--Product Right Ends--> 
  </div>
  <!--PRODUCT DETAIL ENDS--> 
  <!--Product List Starts-->
  <div class="products_list products_slider">
	<!-- UY BEGIN -->
	<div id="uyan_frame"></div>
	<script type="text/javascript" id="UYScript" src="http://v1.uyan.cc/js/iframe.js?UYUserId=0" async=""></script>
	<!-- UY END -->
  </div>
  <div class="products_list products_slider">
    <h2 class="sub_title">你可能也喜欢</h2>
    <ul id="first-carousel" class="first-and-second-carousel jcarousel-skin-tango">
    	<c:forEach items="${recommend }" var="goods">
    	<li> <a class="product_image" href="product.html?id=${goods.goodsId }"><img src="${goods.goodsUrl }" /></a>
			<div class="product_info">
				<h3><a href="product.html?id=${goods.goodsId }">${goods.goodsName }</a></h3>
				<small>${goods.shortDescription }</small> </div>
			<div class="price_info"> <a href="#" onclick="buyNow(${goods.goodsId})">+ 立即购买</a>
				<button class="price_add" title="" type="button"><span class="pr_price">&yen; ${goods.priceDiscount }</span><span class="pr_add" onclick="addCart(${goods.goodsId})">加入餐车</span></button>
			</div>
		</li>
    	</c:forEach>
    </ul>
  </div>
  <!--Product List Ends--> 
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
