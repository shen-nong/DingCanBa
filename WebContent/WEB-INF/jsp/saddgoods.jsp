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
function check(){
	if($(":radio:checked").length ==0){
		alert("请选择产品分类!");
		return false;
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
		<c:import url="/snavigation.html" charEncoding="UTF-8">
			<c:param name="type" value="add"></c:param>
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
                    <li class="active"><a href="#">发布新品</a></li>
                </ul>
                <!--Toolbar-->
                <form:form onsubmit="return check()" method="post" id="goodsFromId" action="addGoodsShop.html" modelAttribute="goodsForm" enctype="multipart/form-data">
                <div class="toolbar">
                    <div class="sortby">
                        <label>产品分类 :</label>
                       	<c:forEach items="${pcate }" var="vate" varStatus="status">
                       	 <input type="radio" name="cate" value="${status.index }" />${vate }
                       	</c:forEach>
                    </div>
                </div>
                <div class="toolbar">
                    <div class="sortby">
                        <label>产品名称:</label>
                        <input type="text" name="goodsName">
                    </div>
                </div>
                <div class="toolbar">
                    <div class="sortby">
                        <label>产品价格:</label>
                        <input type="text" name="goodsPrice">
                    </div>
                </div>
                 <div class="toolbar">
                    <div class="sortby">
                        <label>市场价:</label>
                        <input type="text" name="price">
                    </div>
                </div>
                <div class="toolbar">
                    <div class="sortby">
                        <label>产品描述:</label>
                        <input type="text" name="goodsDescription">
                    </div>
                </div>
                <div class="toolbar">
                    <div class="sortby">
                        <label>产品照片:</label>
                        <input type="file" name="files[0]">
                    </div>
                </div>
                <div class="toolbar">
                    <div class="sortby">
                    	<button class="button brown_btn" type="submit">提交</button>
                    </div>
                </div>
                </form:form>
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
