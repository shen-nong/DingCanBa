<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<script type="text/javascript">
var reg = /^1[3|4|5|8][0-9]\d{4,8}$/;
function register(){
	with(document.getElementById("registerForm")){
		
		if($("#userMailTip").text() == "客官,该已经被注册了!"){
			return;
		}else if(!reg.test($("#userMailInput").val())){
			$("#userMailTip").text("客官,手机号貌似不对哦,请认真填写!");
			return;
		}
		method="post";
		action="register.html";
		submit();
	}
}
function login(){
	with(document.getElementById("loginForm")){
		method="post";
		action="doLogin.html";
		submit();
	}
}
function qqlogin(){
	location="https://graph.qq.com/oauth2.0/authorize?"+
			"response_type=code&client_id=100327823&redirect_uri=http://www.xn--4qrz40kyoi.com/qqLogin.html&"+
			"scope=get_user_info,add_topic,add_one_blog,add_album,upload_pic,list_album,add_share,check_page_fans,add_t,add_pic_t,"+
			"del_t,get_repost_list,get_info,get_other_info,get_fanslist,get_idollist,add_idol,del_ido,get_tenpay_addr";
}
function renrenlogin(){
	location="https://graph.renren.com/oauth/authorize?"+
	    "client_id=60b4c781db34439f886475b891523510&redirect_uri=http://www.xn--4qrz40kyoi.com/renrenLogin.html&response_type=code";
}
$(document).ready(function(){
	  $("#userMailInput").blur(function(){
		  if(!reg.test($("#userMailInput").val())){
				$("#userMailTip").text("客官,手机号貌似不对哦,请认真填写!");
				return;
		  }
		  $.post("existedUser.html",
			  {
			    UserPhone:$("#userMailInput").val()
			  },
			  function(data,status){
			  	if(data){
			  		$("#userMailTip").text("客官,该手机已经被注册了!");
			  	}else {
			  		$("#userMailTip").text("");
			  	}
			  });
	  });
});
</script>
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
            <div class="full_page">
                <h1>
	                <c:choose>
	               		<c:when test="${message != null}">${message }
	               		</c:when>
	               		<c:otherwise>用3秒注册为订餐吧会员
	               		</c:otherwise>
	               	</c:choose>
                </h1>
                <!--CHECKOUT STEPS STARTS-->
                <div class="checkout_steps">
                    <ol id="checkoutSteps">
                        <li class="section allow active" id="opc-login">
                            <div class="step-title">
                                <h2>注册为会员,享受积分优惠,自己开店当老板.</h2>
                            </div>
                            <div id="checkout-step-login">
                                <div class="col2-set">
                                    <div class="col-1">
                                        <h3>开始注册</h3>
                                        <ul class="form-list">
                                        <form:form id="registerForm">
                                            <li>
                                                <label class="required" for="login-email"><em>*</em>手机号</label>
                                                <div class="input-box">
                                                	<form:input path="phone" id="userMailInput"/><span id="userMailTip" style="color:red"></span>
                                                </div>
                                            </li>
                                            <li>
                                                <label class="required" for="login-password"><em>*</em>密码</label>
                                                <div class="input-box">
                                                	<form:input type="password" path="UserPwd" />
                                                </div>
                                            </li>
                                            <li>
                                                <label class="required" for="login-password"><em>*</em>确认密码</label>
                                                <div class="input-box">
                                                    <input type="password" class="input-text" />
                                                </div>
                                            </li>
                                        </form:form>
                                        </ul>
                                    </div>
                                    <div class="col-2">
                                        <h3>开始登录</h3>
                                        <form:form id="loginForm">
                                        	<input type="hidden" name="home" value="${home }"/>
                                            <fieldset>
                                                <h4>已经注册过?</h4>
                                                <p>请登录:</p>
                                                <ul class="form-list">
                                                    <li>
                                                        <label class="required" for="login-email"><em>*</em>手机号</label>
                                                        <div class="input-box">
                                                        	<form:input path="phone" class="input-text"/>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <label class="required" for="login-password"><em>*</em>密码</label>
                                                        <div class="input-box">
                                                            <form:input path="UserPwd" type="password" class="input-text" />
                                                        </div>
                                                    </li>
                                                </ul>
                                                <br />
                                                <br />
                                            </fieldset>
                                        </form:form>
                                    </div>
                                </div>
                                <div class="col2-set">
                                    <div class="col-1">
                                        <div class="buttons-set">
                                            <button class="button brown_btn" type="button" onclick="register()">注册</button>
                                        </div>
                                    </div>
                                    <div class="col-2">
                                        <div class="buttons-set"> <a class="fl_right" href="#">忘记密码?</a>
                                            <button class="button brown_btn" type="button" onclick="login()">登录</button>
                                            <button class="button brown_btn" type="button" onclick="qqlogin()">QQ一键登陆</button>
                                            <button class="button brown_btn" type="button" onclick="renrenlogin()">人人账号登陆</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ol>
                </div>
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