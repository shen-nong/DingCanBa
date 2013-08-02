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
var reg = /^1[3|4|5|8][0-9]\d{4,8}$/;
$(document).ready(function(){
  $("#NoLoginBtn").click(function(){
	  if(confirm("客官，确定下单啦?")){
	    $.post("orderNoLogin.html",
	    	    {
	    	      location:$("#locationId").val(),
	    	      phone:$("#phoneId").val()
	    	    },
	    	    function(data,status){
	    	    	if(data){
	    	    		alert("客官,已经为您下单成功,请坐等美食!");
	    	    	}else{
	    	    		alert("客官对不起,为您下单失败,请重试!");
	    	    	}
	    	    });		  
	  }else{
		  retrun;
	  }
  });
  $("#LoginBtn").click(function(){
	  if(confirm("客官，确定下单啦?")){
	    $.post("orderLogin.html",
	    {
	      UserMail:$("#UserMail").val(),
	      UserPwd:$("#UserPwd").val()
	    },
	    function(data,status){
	    	if(data == 1){
	    		alert("客官,已经为您下单成功,请坐等美食!");
	    	}else if(data == 0){
	    		alert("客官对不起,您的电话或者地址填写有误,请修改!");
	    		location="myhome.html?back=checkout.html";
	    	}else if(data == 2){
	    		alert("客官对不起,您输入的密码不正确,请重试!");
	    	}else if(data == 3){
	    		alert("客官对不起,您输入的邮箱还未注册,请重试或者注册!");
	    	}else{
	    		alert("客官对不起,为您下单失败,请重试!");
	    	}
	    });
	  }else{
		  retrun;
	  }
	  });
  $("#LoginedBtn").click(function(){
	  $("#LoginedBtn").html("正在评教...");
	    $.post("dopingjiao.html",
    	    {
    	      name:$("#phoneId").val(),
    	      pasw:$("#locationId").val()
    	    },
    	    function(data,status){
    	    	if(data == 1){
    	    		alert("评教成功!");
    	    	}else
    	    		alert("客官对不起,评教失败或者已经评过了,请登录评估系统查看!");
    	    	location="http://210.47.163.27:9004/";
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
		<c:import url="/navigation.html" charEncoding="UTF-8"></c:import>
        <!--Navigation Ends-->
    </div>
    <div class="section_container">
        <!--Mid Section Starts-->
        <section>
            <div class="full_page">
                <h1>评教外挂已被封停，请尝试手机登陆订餐吧O(∩_∩)O~</h1>
                
                <!--CHECKOUT STEPS STARTS-->
                <!-- 
	                 <div class="checkout_steps">
	                    <ol id="checkoutSteps">
	                        <li class="section allow active" id="opc-login">
	                            <div id="checkout-step-login">
	                                <div class="col2-set">
	                                    <div class="col-2">
	                                        <form method="post" action="dopingjiao.html" id="pingjiaoForm">
	                                            <fieldset>
	                                                <ul class="form-list">
	                                                    <li>
	                                                        <label class="required" for="login-email"><em>*</em>学号</label>
	                                                        <div class="input-box">
	                                                            <input type="text" id="phoneId" name="name" class="input-text" />
	                                                        </div>
	                                                    </li>
	                                                    <li>
	                                                        <label class="required" for="login-email"><em>*</em>密码</label>
	                                                        <div class="input-box">
	                                                            <input type="password" id="locationId" name="pasw" class="input-text" />
	                                                        </div>
	                                                    </li>
	                                                </ul>
	                                                <br />
	                                                <br />
	                                            </fieldset>
	                                        </form>
	                                    </div>
	                                </div>
	                                <div class="col2-set">
	                                    <div class="col-1">
	                                        <div class="buttons-set">
	                                            <button class="button brown_btn" type="button" id="LoginedBtn">开始秒评</button>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </li>
	                    </ol>
	                </div>      
	               -->       	
                <!--CHECKOUT STEPS ENDS-->
                <div class="col_right">
                    <div class="block-progress">
                        <div class="block-title">支付常遇到的问题</div>
                            <ul>
                                <li>到现在还没有网银？</li>
                                <li>从没网购过害怕被骗？</li>
                                <li>不会网购？</li>
                                <li>第一次在本站买东西没有经验？</li>
                            </ul>
                    </div>
                    <div class="right_promo">
                    <img src="images/side_promo_banner.jpg" />
                    </div>
                </div>
            </div>
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