<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String ua=request.getHeader("User-Agent").toLowerCase();
if(ua.matches("(?i).*((android|bb\\d+|meego).+mobile|avantgo|bada\\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino).*")||ua.substring(0,4).matches("(?i)1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\\-|your|zeto|zte\\-")) {
  response.sendRedirect("http://m.xn--4qrz40kyoi.com");
  return;
}
%>
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
			<c:param name="type" value="cate"></c:param>
		</c:import>
        <!--Navigation Ends-->
    </div>
    <div class="section_container">
        <!--Mid Section Starts-->
        <section>
            <!--Product List Starts-->
            <div class="products_list products_slider">
                <h2 class="sub_title"><a href="find.html?id=0">外卖快餐 更多>></a></h2>
                <ul id="first-carousel" class="first-and-second-carousel jcarousel-skin-tango">
                	<c:forEach items="${goodslist0 }" var="goods">
                	<li> <a class="product_image" href="product.html?id=${goods.goodsId }"><img src="${goods.goodsUrl }" /></a>
                        <div class="product_info">
                            <h3><a href="product.html?id=${goods.goodsId }" >${goods.goodsName }</a></h3>
                            <small>${goods.shortDescription }</small> </div>
                        <div class="price_info"> <a href="#" rel="${goods.shopId }" onclick="buyNow(${goods.goodsId})">+ 立即购买</a>
                            <button class="price_add" title="" type="button" onclick="addCart(${goods.goodsId })">
                            <span class="pr_price">&yen;  ${goods.priceDiscount }</span><span class="pr_add">加入餐车</span>
                            </button>
                        </div>
                    </li>
                	</c:forEach>
                </ul>
            </div>
            <!--Product List Ends-->
            <!--Product List Starts-->
            <div class="products_list products_slider">
                <h2 class="sub_title"><a href="find.html?id=1">糕点西餐  更多>></a></h2>
                <ul id="first-carousel" class="first-and-second-carousel jcarousel-skin-tango">
                    <c:forEach items="${goodslist1 }" var="goods">
                	<li> <a class="product_image" href="product.html?id=${goods.goodsId }"><img src="${goods.goodsUrl }" /></a>
                        <div class="product_info">
                            <h3><a href="product.html?id=${goods.goodsId }" >${goods.goodsName }</a></h3>
                            <small>${goods.shortDescription }</small> </div>
                        <div class="price_info"> <a href="#" rel="${goods.shopId }" onclick="buyNow(${goods.goodsId})">+ 立即购买</a>
                            <button class="price_add" title="" type="button" onclick="addCart(${goods.goodsId })">
                            <span class="pr_price">&yen;  ${goods.priceDiscount }</span><span class="pr_add" >加入餐车</span>
                            </button>
                        </div>
                    </li>
                	</c:forEach>
                </ul>
            </div>
            <!--Product List Ends-->
            <!--Product List Starts-->
            <div class="products_list products_slider">
                <h2 class="sub_title"><a href="find.html?id=2">摊饼煎饺  更多>></a></h2>
                <ul id="first-carousel" class="first-and-second-carousel jcarousel-skin-tango">
                    <c:forEach items="${goodslist2 }" var="goods">
                	<li> <a class="product_image" href="product.html?id=${goods.goodsId }"><img src="${goods.goodsUrl }" /></a>
                        <div class="product_info">
                            <h3><a href="product.html?id=${goods.goodsId }" >${goods.goodsName }</a></h3>
                            <small>${goods.shortDescription }</small> </div>
                        <div class="price_info"> <a href="#" rel="${goods.shopId }" onclick="buyNow(${goods.goodsId})">+ 立即购买</a>
                            <button class="price_add" title="" type="button" onclick="addCart(${goods.goodsId })">
                            <span class="pr_price">&yen;  ${goods.priceDiscount }</span>
                            <span class="pr_add" >加入餐车</span>
                            </button>
                        </div>
                    </li>
                	</c:forEach>
                </ul>
            </div>
            <!--Product List Ends-->
            <!--Product List Starts-->
            <div class="products_list products_slider">
                <h2 class="sub_title"><a href="find.html?id=3">水果零食  更多>></a></h2>
                <ul id="first-carousel" class="first-and-second-carousel jcarousel-skin-tango">
                    <c:forEach items="${goodslist3 }" var="goods">
                	<li> <a class="product_image" href="product.html?id=${goods.goodsId }"><img src="${goods.goodsUrl }" /></a>
                        <div class="product_info">
                            <h3><a href="product.html?id=${goods.goodsId }" >${goods.goodsName }</a></h3>
                            <small>${goods.shortDescription }</small> </div>
                        <div class="price_info"> <a href="#" rel="${goods.shopId }" onclick="buyNow(${goods.goodsId})">+ 立即购买</a>
                            <button class="price_add" title="" type="button" onclick="addCart(${goods.goodsId })">
                            <span class="pr_price">&yen;  ${goods.priceDiscount }</span>
                            <span class="pr_add">加入餐车</span>
                            </button>
                        </div>
                    </li>
                	</c:forEach>
                </ul>
            </div>
            <!--Product List Ends-->
            <!--Product List Starts-->
            <div class="products_list products_slider">
                <h2 class="sub_title"><a href="find.html?id=4">饮料酒水  更多>></a></h2>
                <ul id="first-carousel" class="first-and-second-carousel jcarousel-skin-tango">
                    <c:forEach items="${goodslist4 }" var="goods">
                	<li> <a class="product_image" href="product.html?id=${goods.goodsId }"><img src="${goods.goodsUrl }" /></a>
                        <div class="product_info">
                            <h3><a href="product.html?id=${goods.goodsId }" >${goods.goodsName }</a></h3>
                            <small>${goods.shortDescription }</small> </div>
                        <div class="price_info"> <a href="#" rel="${goods.shopId }" onclick="buyNow(${goods.goodsId})">+ 立即购买</a>
                            <button class="price_add" title="" type="button" onclick="addCart(${goods.goodsId })">
                            <span class="pr_price">&yen;  ${goods.priceDiscount }</span>
                            <span class="pr_add">加入餐车</span>
                            </button>
                        </div>
                    </li>
                	</c:forEach>
                </ul>
            </div>
            <!--Product List Ends-->
            <!--Product List Starts-->
            <div class="products_list products_slider">
                <h2 class="sub_title"><a href="find.html?id=5">生活用品  更多>></a></h2>
                <ul id="first-carousel" class="first-and-second-carousel jcarousel-skin-tango">
                    <c:forEach items="${goodslist5 }" var="goods">
                	<li> <a class="product_image" href="product.html?id=${goods.goodsId }"><img src="${goods.goodsUrl }" /></a>
                        <div class="product_info">
                            <h3><a href="product.html?id=${goods.goodsId }" >${goods.goodsName }</a></h3>
                            <small>${goods.shortDescription }</small> </div>
                        <div class="price_info"> <a href="#" rel="${goods.shopId }" onclick="buyNow(${goods.goodsId})">+ 立即购买</a>
                            <button class="price_add" title="" type="button" onclick="addCart(${goods.goodsId })">
                            <span class="pr_price">&yen;  ${goods.priceDiscount }</span>
                            <span class="pr_add">加入餐车</span>
                            </button>
                        </div>
                    </li>
                	</c:forEach>
                </ul>
            </div>
            <!--Product List Ends-->
            <!--Product List Starts-->
            <div class="products_list products_slider">
                <h2 class="sub_title"><a href="find.html?id=6">手工饰品  更多>></a></h2>
                <ul id="first-carousel" class="first-and-second-carousel jcarousel-skin-tango">
                    <c:forEach items="${goodslist6 }" var="goods">
                	<li> <a class="product_image" href="product.html?id=${goods.goodsId }"><img src="${goods.goodsUrl }" /></a>
                        <div class="product_info">
                            <h3><a href="product.html?id=${goods.goodsId }" >${goods.goodsName }</a></h3>
                            <small>${goods.shortDescription }</small> </div>
                        <div class="price_info"> <a href="#" rel="${goods.shopId }" onclick="buyNow(${goods.goodsId})">+ 立即购买</a>
                            <button class="price_add" title="" type="button" onclick="addCart(${goods.goodsId })">
                            <span class="pr_price">&yen;  ${goods.priceDiscount }</span>
                            <span class="pr_add">加入餐车</span>
                            </button>
                        </div>
                    </li>
                	</c:forEach>
                </ul>
            </div>
            <!--Product List Ends-->
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