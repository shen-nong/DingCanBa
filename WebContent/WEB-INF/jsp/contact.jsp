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
        <c:import url="/header.jsp" charEncoding="UTF-8"></c:import>
        <!--Header Ends-->
    </div>
    <div class="navigation_container">
        <!--Navigation Starts-->
		<c:import url="/navigation.jsp" charEncoding="UTF-8"></c:import>
        <!--Navigation Ends-->
    </div>
    <div class="section_container">
        <!--Mid Section Starts-->
        <section>
            <div class="full_page">
                <h1>联系我们</h1>
				<div class="col_left_main contact_page">
              <!--Google Maps Start-->
<iframe width="325" height="300" style="float:right;" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="http://maps.google.com/maps?f=q&amp;source=embed&amp;hl=en&amp;q=Gap&amp;aq=&amp;sll=40.755458,-73.986346&amp;sspn=0.000437,0.001032&amp;ie=UTF8&amp;t=m&amp;st=115968771510351694523&amp;rq=1&amp;ev=p&amp;split=1&amp;radius=0.03&amp;hq=Gap&amp;hnear=&amp;ll=40.75535,-73.98646&amp;spn=0.000425,0.001032&amp;layer=c&amp;cbll=40.753398,-73.984663&amp;panoid=RRWXtBJKotx-EcvV7gY_vg&amp;cbp=12,180.48,,0,-10.81&amp;output=svembed"></iframe><br />
            <!--Google Maps Ends-->                   
                	<!--Contact Starts-->
                        <address>
                            <b>神农软件</b><br />
                            沈阳农业大学<br />
                            辽宁省<br />
                            1389817673<br />
                        </address>
                    
                    <!--Block Ends-->
        <!--Form Starts-->
        <div class="block">
                        <form id="contact-us" action="leisure_contact.php" method="post" />
                <h3>留言咨询</h3>
                
			  
                    
                <ul id="contact_form">
                    <li>
                        <input type="text" name="contactName" id="contactName" value="" class="txt requiredField" placeholder="姓名:" />
                                            </li>
                    <li>
                        <input type="text" name="email" id="email" value="" class="txt requiredField email" placeholder="邮件:" />
                                            </li>
                    <li>
                        <textarea name="comments" id="commentsText" class="txtarea requiredField" placeholder="信息:"></textarea>
                                            </li>
                    <li>
                        <button name="submit" type="submit" class="subbutton brown_btn">发送邮件!</button>
                        <input type="hidden" name="submitted" id="submitted" value="true" />
                    </li>
                </ul>
            </form>
        </div>
        <!--Form Ends-->
                      
                    <!--Contact Ends-->
                </div>
                <div class="col_right">
                    <div class="block-progress">
                        <div class="block-title">我们的服务</div>
                            <ul>
                                <li>问答系统</li>
                                <li>安全支付</li>
                                <li>订单支付</li>
                                <li>网购经验</li>
                                <li>不良举报</li>
                            </ul>
                    </div>
                    <div class="right_promo">
                    <img src="images/side_promo_banner.jpg" />
                    </div>
                </div>
            </div>
            <!--Newsletter_subscribe Starts-->
            <div class="subscribe_block">
            <c:import url="newsletter.jsp" charEncoding="UTF-8"></c:import>
            </div>
            <!--Newsletter_subscribe Ends-->
        </section>
        <!--Mid Section Ends-->
    </div>
    <div class="footer_container">
        <!--Footer Starts-->
        <footer>
        <c:import url="footer.jsp" charEncoding="UTF-8"></c:import>
        </footer>
        <!--Footer Ends-->
    </div>
</div>
<!--FORM SUBMIT-->
<c:import url="slide_nav.jsp" charEncoding="UTF-8"></c:import>
<!--CUSTOMIZE-->
</body>
</html>
