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
		  $.post("setInfo.html",
			  {
			    location:$("#locationId").val(),
			    phone:$("#phoneId").val(),
			    userName:$("#userNameId").val()
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
		<c:import url="/unavigation.html" charEncoding="UTF-8">
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
                	<img src="${user.photo }" alt="Img">
                	<form:form name="avatar" method="post" action="avatar.html" modelAttribute="goodsForm" enctype="multipart/form-data">
					<input type="button" value="一键修改头像" onclick="path.click()" class="subbutton brown_btn">  
					<input type="file" id="path" style="display:none" name="files[0]" onchange="document.avatar.submit()">
                	</form:form>
                </div>
            </div>
            <!--SIDE NAV ENDS-->
            <!--MAIN CONTENT STARTS-->
            <div id="main_content">
                <!--Toolbar-->
                <div class="toolbar">
                    <div class="sortby">
                        <label>昵称:</label>
                        <input type="text" id="userNameId" placeholder="客官,来一个霸气的吧" value="${user.userName }"/>
                    </div>
                </div>
                <div class="toolbar">
                    <div class="sortby">
                        <label>手机:</label>
                        <input type="text" id="phoneId" placeholder="请填写您的联系方式" value="${user.phone }"/>
                    </div>
                </div>
                <div class="toolbar">
                    <div class="sortby">
                        <label>地址:</label>
                        <input type="text" id="locationId" value="${user.location }"/>
                        <c:if test="${empty user.location}">
                        <span style="color:red">(*填写如:2A舍554)</span>
                        </c:if>
                    </div>
                </div>
                <div class="toolbar">
                    <div class="sortby">
                        <label>我的购物动态发布到:</label>
                        <input type="checkbox">QQ空间</input>
                        <input type="checkbox">人人网</input>
                    </div>
                </div>
                
                <div class="toolbar">
                    <div class="sortby">
                        <button name="submit" type="submit" class="subbutton brown_btn" id="updateInfoId">保存信息</button>                    
                    </div>
                    <c:if test="${! empty back}">
                    <div class="sortby">
                        <button name="submit" type="submit" class="subbutton brown_btn" onclick="location='${back}'">返回</button>                    
                    </div>
                    </c:if>
                </div>
                
                <!--Toolbar ENDS-->
            </div>
            <!--MAIN CONTENT -->
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
