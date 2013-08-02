package com.dingcan.base;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.DispatcherServlet;

import com.dingcan.cons.Cons;
import com.dingcan.user.model.User;
import com.dingcan.user.service.UserService;
import com.dingcan.util.CookieManager;
/**
 * filter统一编码,和实现自动登录
 * @author Green Lei
 * 2012-11-29 下午2:13:33 2012
 */
public class CustomDispatcherServlet extends DispatcherServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	
	 protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		 request.setCharacterEncoding("utf-8");   //filter统一编码
		 
		 HttpSession session = request.getSession(true);
		 String userid;
		 String sessionid;   // 此sessionid是上次用户登录时保存于用户端的识别码，用于用户后续访问的自动登录。不是本次访问的session id。
		 Cookie[] cookies;
		 boolean isAutoLogin;
		 
		 User user = (User) session.getAttribute(Cons.USER_CONTEXT);
	 	 if (user == null) { 
	 		 //user = new User(); // 此时user中的username属性为""，表示用户未登录。
		     cookies = request.getCookies();
		     if(cookies != null){
		    	 userid = CookieManager.getCookieValue(cookies, Cons.AUTOLOGIN_USERID);
			     sessionid = CookieManager.getCookieValue(cookies, Cons.SESSIONID);
			     isAutoLogin = userService.isAutoLoginBySessionId(userid, sessionid); // 如果在数据库中找到了相应记录，则说明可以自动登录。
			     if (isAutoLogin) {
			    	 user = userService.getUserByUserId(userid);
			         session.setAttribute(Cons.USER_CONTEXT, user); // 将user bean添加到session中。
			     } 
		     }
		 }
		 super.doService(request, response);  
	 }  
}
