<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>订餐吧</title> 
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta property="qc:admins" content="53643766676065541224031716375" />
<!--CSS-->
<link rel="stylesheet" href="css/styles.css" />
<!--Javascript-->
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.masonry.js"></script>
<script type="text/javascript" src="js/jquery.infinitescroll.js"></script>


<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
em{font-style:normal;}
a{text-decoration:none;cursor:pointer;color:#666666;}
a:hover{color:#FF6699;}
body{background:url("images/bodybg.jpg") repeat #f5f6f7;color:#666666;font-family:Arial;font-size:12px;}
.fl{float:left;}.fr{float:right;}
.clearfix:after{content:".";display:block;height:0;clear:both;visibility:hidden;}
.clearfix{display:inline-table;}
*html .clearfix{height:1%;}
.clearfix{display:block;}
*+html .clearfix{min-height:1%;}
.demo{width:950px;margin:0 auto;}

/* item_list */
.item_list{position:relative;padding:0 0 50px;}
.item{
	width:226px;background:#fff;overflow:hidden;margin:15px 0 0 0;
	border-radius:4px 4px 4px 4px;
	box-shadow:0 1px 3px rgba(34, 25, 25, 0.2);	
}
.item_t{padding:10px 8px 0;}
.item_t .img{background-color:#FFFFFF;margin:0 auto;position:relative;width:210px;min-height:210px;}
.item_t .img a{display:block;}
.item_t .img a:hover{background:#000;}
.item_t .img a:hover img{filter:alpha(opacity=80);-khtml-opacity:0.8;opacity:0.8;-webkit-transition:all 0.3s ease-out;-khtml-transition:all 0.3s ease-out;}
.item_t .price{
	position:absolute;bottom:10px;right:0px;background-color:rgba(0, 0, 0, 0.2);color:#FFF;
	filter:progid:DXImageTransform.Microsoft.gradient(startcolorstr=#33000000, endcolorstr=#33000000);
}
.item .btns{display:none;}
.img_album_btn{top:0px;right:0px;position:absolute;background:#ff6fa6;color:#ffffff;height:20px;line-height:20px;width:56px;border-radius:3px;}
.img_album_btn:hover{color:#fff;}
.item_t .title{padding:8px 0;line-height:18px;}
.item_b{padding:10px 8px;}
.item_b .items_likes .like_btn{background:url("images/fav_icon_word_new_1220.png") no-repeat;display:block;float:left;height:23px;width:59px;margin-right:5px;}
.item_b .items_likes em{line-height:23px;display:block;float:left;padding:0px 6px;color:#FF6699;font-weight:800;border:1px solid #ff6fa6;border-radius:3px;}

/* more */
#more{display:block;margin:10px auto 20px;}

/* infscr-loading */
#infscr-loading{bottom:-10px;left:45%;position:absolute;text-align:center;height:20px;line-height:20px;z-index:100;width:120px;}

/* page */
.page{display:none;font-size:18px;height:60px;text-align:center;margin:20px 0 0 0;}
.page_num a,.page_num span{margin:0 2px;background:url("images/page.png") no-repeat;display:inline-block;width:30px;height:28px;line-height:26px;overflow:hidden;}
.page_num a{background-position:-65px 0;color:#FF3333;overflow:hidden;}
.page_num .prev{background-position:1px -33px;}
.page_num .unprev{background-position:1px 0;cursor:default;}
.page_num .next{background-position:-32px 0;}
.page_num .unnext{background-position:-32px -33px;cursor:default;}
.page_num .current{background-position:-99px 0;color:#FFFFFF;}
.page_num .etc{background-position:-172px 8px;}

/* to_top */
.to_top a,.to_top a:hover{background:url("images/gotop.png") no-repeat}
.to_top a{
	background-position:0 0;float:left;height:50px;overflow:hidden;width:50px;position:fixed;bottom:35px;cursor:pointer;right:20px;
	_position:absolute;
	_right:auto;
	_left:expression(eval(document.documentElement.scrollLeft+document.documentElement.clientWidth-this.offsetWidth)-(parseInt(this.currentStyle.marginLeft, 10)||0)-(parseInt(this.currentStyle.marginRight, 10)||20));
	_top:expression(eval(document.documentElement.scrollTop+document.documentElement.clientHeight-this.offsetHeight-(parseInt(this.currentStyle.marginTop, 10)||20)-(parseInt(this.currentStyle.marginBottom, 10)||20)));
}
.to_top a:hover{background-position:-51px 0px;}
.pull-left
{
	float:left;
}
.add_follow
{
	margin-left:5px;
	width:71px;
	height:18px;
	background:url(images/add_follow.png) top left;
}
.added_follow
{
	width:71px;
	height:18px;
	background:url(images/add_follow.png) 0px -18px;
}
.titext
{
	margin-top:10px;
	line-height:20px;
	margin-left:5px;
	width:150px;
}
</style>

<script type="text/javascript">
var isWidescreen=screen.width>=1280; 
if(isWidescreen){document.write("<style type='text/css'>.demo{width:950px;}</style>");}
</script>

<script type="text/javascript">
function addCart(field){
	  result=$.ajax({url:"addCart.html?id="+field+"&temp="+Math.random(),async:false});
	  if(result){
		  alert("添加成功!");
	  }else{
		  alert(result);
	  }
}
function buyNow(field){
	  result=$.ajax({url:"addCart.html?id="+field+"&temp="+Math.random(),async:false});
	  if(result){
		  location="checkout.html";
	  }else{
		  alert("添加失败,请重试!");
	  }
}
function addLike(field){
	  result=$.ajax({url:"addLikeGoods.html?id="+field+"&temp="+Math.random(),async:false});
	  if(result){
		  $("#like"+field).html(parseInt($("#like"+field).html())+1);
	  }else{
		  alert("添加失败,请重试!");
	  }
}
function addFriends(field){
	  result=$.ajax({url:"addfriends.html?id="+field+"&temp="+Math.random(),async:false});
	  if(result.responseText == "1"){
		  $('.add_follow').toggleClass("added_follow");
	  }else if(result.responseText == "2"){
		  alert("抱歉,您还未登录,无法添加好友!");
		  location="login.html";
	  }else if(result.responseText == "0"){
		  alert("你们已经是朋友了!");
	  }else{
		  alert("添加失败,请重试!");
	  }
}
function item_masonry(){ 
	$('.item img').load(function(){ 
		$('.infinite_scroll').masonry({ 
			itemSelector: '.masonry_brick',
			columnWidth:226,
			gutterWidth:15								
		});		
	});
		
	$('.infinite_scroll').masonry({ 
		itemSelector: '.masonry_brick',
		columnWidth:226,
		gutterWidth:15								
	});	
}

$(function(){

	function item_callback(){ 
		
		$('.item').mouseover(function(){
			$(this).css('box-shadow', '0 1px 5px rgba(35,25,25,0.5)');
			$('.btns',this).show();
		}).mouseout(function(){
			$(this).css('box-shadow', '0 1px 3px rgba(34,25,25,0.2)');
			$('.btns',this).hide();		 	
		});
		
		item_masonry();	

	}

	item_callback();  

	$('.item').fadeIn();

	var sp = 1
	
	$(".infinite_scroll").infinitescroll({
		navSelector  	: "#more",
		nextSelector 	: "#more a",
		itemSelector 	: ".item",
		
		loading:{
			img: "images/masonry_loading_1.gif",
			msgText: ' ',
			finishedMsg: '木有了',
			finished: function(){
				sp++;
				if(sp>=10){ //到第10页结束事件
					$("#more").remove();
					$("#infscr-loading").hide();
					$("#page").show();
					$(window).unbind('.infscr');
				}
			}	
		},errorCallback:function(){ 
			$("#page").show();
		}
		
	},function(newElements){
		var $newElems = $(newElements);
		$('.infinite_scroll').masonry('appended', $newElems, false);
		$newElems.fadeIn();
		item_callback();
		return;
	});

});
</script>
<!--[if lt IE 9]>
    <script src="js/html5.js"></script>
<![endif]-->
<!-- mobile setting -->
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
		<c:import url="/navigation.html" charEncoding="UTF-8">
			<c:param name="type" value="index"></c:param>
		</c:import>
        <!--Navigation Ends-->
    </div>
    <div class="section_container">
        <!--Mid Section Starts-->
        <section>
        <section>
            <ul class="breadcrumb">
                <li><a>外卖快餐</a></li>
                <li class="active"><a href="goodsList.html?cate=9" >快餐面条</a></li>
                <li class="active"><a href="goodsList.html?cate=10">煎饺热汤</a></li>
            </ul>
            <ul class="breadcrumb">
                <li><a>零食饮料</a></li>
                <li class="active"><a href="goodsList.html?cate=0">肉类食品</a></li>
                <li class="active"><a href="goodsList.html?cate=1">面包饼干</a></li>
                <li class="active"><a href="goodsList.html?cate=2">方便面</a></li>
                <li class="active"><a href="goodsList.html?cate=3">瓜子干果</a></li>
                <li class="active"><a href="goodsList.html?cate=4">休闲零食</a></li>
                <li class="active"><a href="goodsList.html?cate=5">饮料酒水</a></li>
            </ul>
            <ul class="breadcrumb">
                <li><a>生活用品</a></li>
                <li class="active"><a href="goodsList.html?cate=7">洗发沐浴</a></li>
                <li class="active"><a href="goodsList.html?cate=8">个人护理</a></li>
            </ul>
<div class="demo clearfix">
	<div class="item_list infinite_scroll">
		<c:forEach items="${stroll}" var="message">
		<div class="item masonry_brick">
			<div class="item_t">
				<div class="img">
					<a href="product.html?id=${message.goods.goodsId }"><img width="210" height="240" alt="${message.goods.goodsName }" src="${message.goods.goodsUrl }" /></a>
					<span class="price">￥ ${message.goods.priceDiscount }</span>
					<div class="btns">
						<a onclick="addCart(${message.goods.goodsId })" class="img_album_btn">加入餐车</a>
					</div>
				</div>
				<div class="title">
                	<div class="title1"> 
                	<div class="tiimg pull-left">
                		<a href="user.html?id=${message.user.userId }">
                			<img width="50" height="50" alt="${message.user.photo }" src="${message.user.photo }" />
               			</a>
                	</div>
                    <div class="titext pull-left">
                    	<div class="titext_1">
                        	<div class="tname pull-left"><a href="user.html?id=${message.user.userId }">${message.user.userName }</a></div>
                            <div class="add_follow pull-left" onclick="addFriends(${message.user.userId })"></div> 
                            <div class="clear"></div>
                        </div>
                        <div class="titext_2">
                        	${message.date } 买了
                        </div>
                    </div>
                    <div class="clear"></div>
                    </div>
                    <div class="title2">
                    	<h2>${message.goods.goodsName }</h2>
                    </div>
                </div>
			</div>
			<div class="item_b clearfix">
				<div class="items_likes fl">
					<a class="like_btn"  onclick="addLike(${message.goods.goodsId })"></a>
					<em class="bold" id="like${message.goods.goodsId }">${message.like }</em>
				</div>
				<div class="items_comment fr"><a onclick="buyNow(${message.goods.goodsId })">立即购买</a><em class="bold">(${message.sale})</em></div>
			</div>
		</div><!--item end-->			
		</c:forEach>
	</div>
					
					
	<div id="more"><a href="index/2.html"></a></div>
					
</div>
<script type="text/javascript">
$(function(){

	$(window).scroll(function(){
		$(window).scrollTop()>1000 ? $("#gotopbtn").css('display','').click(function(){
			$(window).scrollTop(0);
		}):$("#gotopbtn").css('display','none');	
	});
	
});
</script>
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