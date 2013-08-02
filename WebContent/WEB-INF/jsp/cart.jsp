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
		<c:import url="/navigation.html" charEncoding="UTF-8"></c:import>
        <!--Navigation Ends-->
    </div>
  <div class="section_container"> 
    <!--Mid Section Starts-->
    <section> 
      <!--CART STARTS-->
      <div id="shopping_cart" class="full_page">
        <h1>我的餐车</h1>
        <div class="message success">客官您登陆之后,您的购物信息将永久保留,订餐吧为你记录每一笔开支.</div>
        <div class="action_buttonbar">
          <button type="button" title="" class="continue" onclick="parent.location='category.html'">继续购物</button>
          <button type="button" onclick="document.cartForm.submit()" title="" class="checkout">现在下单</button>
        </div>
        <div class="cart_table">
          <form action="checkout.html" method="post" name="cartForm">
          <table class="data-table cart-table" id="shopping-cart-table" cellpadding="0" cellspacing="0">
            <tr>
              <th colspan="2">物品</th>
              <th class="align_center" width="6%"></th>
              <th class="align_center" width="12%">单价</th>
              <th class="align_center" width="10%">数量</th>
              <th class="align_center" width="12%">小计</th>
              <th class="align_center" width="6%"></th>
            </tr>
            
            <c:forEach items="${GoodsList }" var="goods" varStatus="status">
            <input type="hidden" name="GoodsId" value="${goods.goodsId }">
            <tr>
              <td width="10%"><img src="${goods.goodsUrl }"  width="80px" height="80px"/></td>
              <td class="align_left" width="44%"><a class="pr_name" href="product.html?id=${goods.goodsId }">${goods.goodsName }</a><span class="pr_info"> </span></td>
              <td class="align_center"><a href="#" class="edit">备注</a></td>
              <td class="align_center vline"><span class="price">&yen; <i data-item-id="${status.index }" class="xprice">${goods.priceDiscount }</i></span></td>
              <td class="align_center vline"><input name="GoodsCount" data-item-id="${status.index }" class="qty_box xcount" type="text" value="${goods.count }"/></td>
              <td class="align_center vline"><span class="price">&yen; <i data-item-id="${status.index }" class="xallprice">${goods.priceDiscount*goods.count}</i></span></td>
              <td class="align_center vline"><a href="delCart.html?id=${goods.goodsId }" class="remove"></a></td>
            </tr>
            </c:forEach>
          </table>
          <div class="totals">
            <table id="totals-table">
             	<tr>
                  <td width="60%" colspan="1" class="align_left total">物品</td>
                  <td class="align_right" style=""><span class="total">共 <i class="xallcount">0</i> 件</span></td>
                </tr>  
                <tr>
                  <td width="60%" colspan="1" class="align_left total">总计</td>
                  <td class="align_right" style=""><span class="total">&yen; <i class="xall">0.00</i></span></td>
                </tr>                
            </table>
          </div>
          </form>
        </div>
        <div class="action_buttonbar">
          <button type="button" title="" class="continue" onclick="parent.location='category.html'">继续购物</button>
          <button type="button" onclick="document.cartForm.submit()" title="" class="checkout">现在下单</button>
        </div>
      </div>
      <!--CART ENDS--> 
      
      
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

<script type="text/javascript">
	(function() {
		var priceChange = function(obj) {
			if (obj) {
				var id = $(obj).attr('data-item-id');
				var count = parseInt($(obj).val());
				if (isNaN(count)) {
					count = 1;
				}
				$(obj).val(count);

				var price = parseFloat($('i.xprice[data-item-id=' + id + ']').html()).toFixed(2);
				var allprice = (count * price).toFixed(2);
				$('i.xallprice[data-item-id=' + id + ']').html(allprice);
			}
			var all = 0;
			$('i.xallprice').each(function() {
				var node = parseFloat($(this).html());
				all += node;
			});
			var allcount = 0;
			$('input.xcount').each(function() {
				var node = parseInt($(this).val());
				allcount += node;
			});

			$('i.xallcount').html(allcount);
			$('i.xall').html(all.toFixed(2));
		};

		//绑定事件
		$('input.xcount').keyup(function() {
			priceChange(this);
		});

		//初始化
		priceChange();
	})()
</script>
</body>
</html>