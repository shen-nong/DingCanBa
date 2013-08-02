package com.dingcan.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

import com.dingcan.api.service.QqService;
import com.dingcan.api.service.RenrenService;
import com.dingcan.api.service.SinaService;
import com.dingcan.api.service.VpnService;
import com.dingcan.base.BaseController;
import com.dingcan.cons.Cons;
import com.dingcan.message.model.Message;
import com.dingcan.message.service.MessageService;
import com.dingcan.shop.model.Shop;
import com.dingcan.shop.service.ShopService;
import com.dingcan.user.model.AutoLogin;
import com.dingcan.user.model.User;
import com.dingcan.user.model.UserThird;
import com.dingcan.user.service.UserService;
import com.dingcan.util.ConfigReader;
import com.dingcan.util.CustomDate;
import com.dingcan.util.FileUploadUtil;
import com.dingcan.util.RandomInt;

@Controller
public class ThirdUserLoginController extends BaseController{
	@Autowired
	private SinaService sinaService;
	@Autowired
	private RenrenService renrenService;
	@Autowired
	private UserService userService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private QqService qqService;
	@Autowired
	private VpnService vpnService;
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value = "/sinaLogin", method = RequestMethod.GET)
	public ModelAndView sinaLogin(HttpServletRequest request, HttpServletResponse response) throws WeiboException, JSONException {
		String code = request.getParameter("code");
		JSONObject tokenJson = null;
        if (code == null || code.length() == 0) {
            //缺乏有效参数，跳转到登录页去
            return new ModelAndView("shome");
        }
		
        tokenJson = sinaService.getTokenAndUidByCode(code);
        
		if (tokenJson != null) {
			String uid = tokenJson.getString("uid");
			UserThird userThird =  userService.getUserThirdByThirdIdAndLoginType(uid, Cons.SINA_LOGIN);
			if(userThird != null){       //如果第三方登录表中有记录,表明已经登录过,直接在user表中取信息
				String userId = userThird.getUserId()+"";
				User user = userService.getUserByUserId(userId);
				setSessionUser(request, user);      //添加到session
				
				AutoLogin autoLogin = new AutoLogin();
				autoLogin.setSessionId(request.getSession().getId());
				autoLogin.setUserId(userId);
				userService.addAutoLogin(autoLogin, response);     //添加到自动登录的关联
				
				ModelAndView mav = new ModelAndView("mprofile");
				return mav;
			}else{
				String accessToken = tokenJson.getString("access_token");
				JSONObject userJson = sinaService.showUser(uid, accessToken);
			
				User user = new User();
				user.setUserName(userJson.getString("name"));
				user.setUserSex(userJson.getString("gender"));
				user.setPhoto(userJson.getString("profile_image_url"));
				int userId = userService.addUser(user);    //添加一个新用户 
				userThird = new UserThird();
				userThird.setAccessToken(accessToken);
				userThird.setInvalid("");
				userThird.setLoginType(Cons.SINA_LOGIN);
				userThird.setThirdUserId(uid);
				userThird.setUserId(userId);
				userService.addThirdUser(userThird);       //保存第三方的关联
				
				setSessionUser(request, user);      //添加到session
				AutoLogin autoLogin = new AutoLogin();
				autoLogin.setSessionId(request.getSession().getId());
				autoLogin.setUserId(userId+"");
				userService.addAutoLogin(autoLogin, response);     //添加到自动登录的关联
				
				ModelAndView mav = new ModelAndView("mprofile");
				return mav;
			}
		}
		ModelAndView mav = new ModelAndView("mhome");
		return mav;
	}
	
	@RequestMapping(value = "/renrenLogin", method = RequestMethod.GET)
	public ModelAndView renrenLogin(HttpServletRequest request, HttpServletResponse response) throws WeiboException, JSONException {
		String code = request.getParameter("code");
		org.json.simple.JSONObject tokenJson = null;
        if (code == null || code.length() == 0) {
            //缺乏有效参数，跳转到登录页去
        	return new ModelAndView("login", "message", "缺乏有效参数,请重试!");
        }
		
        tokenJson = renrenService.getTokenAndUidByCode(code);
        
		if (tokenJson != null) {
			String uid = ((org.json.simple.JSONObject)tokenJson.get("user")).get("id").toString();
			UserThird userThird =  userService.getUserThirdByThirdIdAndLoginType(uid, Cons.RENREN_LOGIN);
			if(userThird != null){       //如果第三方登录表中有记录,表明已经登录过,直接在user表中取信息
				String userId = userThird.getUserId()+"";
				User user = userService.getUserByUserId(userId);
				setSessionUser(request, user);      //添加到session
				
				AutoLogin autoLogin = new AutoLogin();
				autoLogin.setSessionId(request.getSession().getId());
				autoLogin.setUserId(userId);
				userService.addAutoLogin(autoLogin, response);     //添加到自动登录的关联
				
				ModelAndView mav = new ModelAndView("redirect:myhome.html");
				return mav;
			}else{
				JSONArray userArray = renrenService.showUser((String) tokenJson.get("access_token"));
				org.json.simple.JSONObject userJson = (org.json.simple.JSONObject) userArray.get(0);
				User user = new User();
				user.setUserName((String)userJson.get("name"));
				user.setUserSex("1".equals(userJson.get("sex").toString())?"m":"f");
				user.setPhoto((String)userJson.get("headurl"));
				int userId = userService.addUser(user);    //添加一个新用户 
				user.setUserId(userId);
				
				userThird = new UserThird();
				userThird.setAccessToken((String)tokenJson.get("access_token"));
				userThird.setInvalid("");
				userThird.setLoginType(Cons.RENREN_LOGIN);
				userThird.setThirdUserId(uid);
				userThird.setUserId(userId);
				userService.addThirdUser(userThird);       //保存第三方的关联
				
				//创建商家
				Shop shop = new Shop();
				shop.setUserId(user.getUserId());
				shop.setShopName(user.getUserName()+"的店铺");
				shop.setSignboardUrl("default/logo/w200_"+RandomInt.getRandomInt(0, 10)+".jpg");
				shopService.addshop(shop);
				
				setSessionUser(request, user);      //添加到session
				AutoLogin autoLogin = new AutoLogin();
				autoLogin.setSessionId(request.getSession().getId());
				autoLogin.setUserId(userId+"");
				userService.addAutoLogin(autoLogin, response);     //添加到自动登录的关联
				
				ModelAndView mav = new ModelAndView("redirect:myhome.html");
				return mav;
			}
		}
        
		ModelAndView mav = new ModelAndView("redirect:myhome.html");
		return mav;
	}
	
	@RequestMapping(value = "/qqLogin", method = RequestMethod.GET)
	public ModelAndView qqLogin(HttpServletRequest request, HttpServletResponse response) throws WeiboException, JSONException {
		String code = request.getParameter("code");
		String tokenStr = null;
        if (code == null || code.length() == 0) {
            //缺乏有效参数，跳转到登录页去
            return new ModelAndView("login", "message", "缺乏有效参数,请重试!");
        }
		
        tokenStr = qqService.getAccessToken(code);
        
		if (tokenStr != null) {
			String uid = qqService.getOpenId(tokenStr);
			UserThird userThird =  userService.getUserThirdByThirdIdAndLoginType(uid, Cons.QQ_LOGIN);
			if(userThird != null){       //如果第三方登录表中有记录,表明已经登录过,直接在user表中取信息
				String userId = userThird.getUserId()+"";
				User user = userService.getUserByUserId(userId);
				setSessionUser(request, user);      //添加到session
				
				AutoLogin autoLogin = new AutoLogin();
				autoLogin.setSessionId(request.getSession().getId());
				autoLogin.setUserId(userId);
				userService.addAutoLogin(autoLogin, response);     //添加到自动登录的关联
				
				ModelAndView mav = new ModelAndView("redirect:myhome.html");
				return mav;
			}else{
				JSONObject userJson = qqService.getUserInfo(tokenStr, uid);
				User user = new User();
				user.setUserName(userJson.getString("nickname"));
				user.setUserSex("男".equals(userJson.getString("gender"))?"m":"f");
				user.setPhoto(userJson.getString("figureurl_2"));
				int userId = userService.addUser(user);    //添加一个新用户 
				user.setUserId(userId);
				userThird = new UserThird();
				userThird.setAccessToken(tokenStr);
				userThird.setInvalid("");
				userThird.setLoginType(Cons.QQ_LOGIN);
				userThird.setThirdUserId(uid);
				userThird.setUserId(userId);
				userService.addThirdUser(userThird);       //保存第三方的关联
				
				setSessionUser(request, user);      //添加到session
				
				AutoLogin autoLogin = new AutoLogin();
				autoLogin.setSessionId(request.getSession().getId());
				autoLogin.setUserId(userId+"");
				userService.addAutoLogin(autoLogin, response);     //添加到自动登录的关联
				
				//更新token
//				qqService.updateStatus(tokenStr, uid, "0", 
//						"0", "0",
//						"0", "0");
				
				//创建商家
				Shop shop = new Shop();
				shop.setUserId(user.getUserId());
				shop.setShopName(user.getUserName()+"的店铺");
				shop.setSignboardUrl("default/logo/w200_"+RandomInt.getRandomInt(0, 10)+".jpg");
				shopService.addshop(shop);
				
				ModelAndView mav = new ModelAndView("redirect:myhome.html");
				return mav;
			}
		}
        
		ModelAndView mav = new ModelAndView("redirect:myhome.html");
		return mav;
	}
	
	@RequestMapping(value = "/vpnLogin", method = RequestMethod.POST)
	public String vpnLogin(HttpServletRequest request, HttpServletResponse response) throws WeiboException, JSONException {
		String savePath = FileUploadUtil.genetateUserimgPath(request);
		String userName = request.getParameter("name");
		String userPasw = request.getParameter("pasw");
		UserThird userThird =  userService.getUserThirdByThirdIdAndLoginType(userName, Cons.VPN_LOGIN);
		if(userThird != null){       //如果第三方登录表中有记录,表明已经登录过,直接在user表中取信息
			String userId = userThird.getUserId()+"";
			User user = userService.getUserByUserId(userId);
			setSessionUser(request, user);      //添加到session
			
			AutoLogin autoLogin = new AutoLogin();
			autoLogin.setSessionId(request.getSession().getId());
			autoLogin.setUserId(userId);
			userService.addAutoLogin(autoLogin, response);     //添加到自动登录的关联
			
			return "redirect:user.html";
		}else{
			User user = new User();
			try {
				user = vpnService.getUserInfo(userName, userPasw, savePath);
			} catch (Exception e) {
				e.printStackTrace();
				return "error.jsp?userName="+userName+"&userPasw="+userPasw;
			}
			user.setUserPwd(userPasw);
			int userId = userService.addUser(user);    //添加一个新用户 
			userThird = new UserThird();
			userThird.setAccessToken("");
			userThird.setInvalid("");
			userThird.setLoginType(Cons.VPN_LOGIN);
			userThird.setThirdUserId(userName);
			userThird.setUserId(userId);
			userService.addThirdUser(userThird);       //保存第三方的关联
			
			user.setUserId(userId);
			setSessionUser(request, user);      //添加到session
			AutoLogin autoLogin = new AutoLogin();
			autoLogin.setSessionId(request.getSession().getId());
			autoLogin.setUserId(userId+"");
			userService.addAutoLogin(autoLogin, response);     //添加到自动登录的关联
			
			Message messageObj = new Message();
			messageObj.setContentText(user.getUserName()+"使用vpn登陆了订餐吧");
			messageObj.setPictureUrl(user.getPhoto());
			messageObj.setFromId(user.getUserId());
			messageObj.setType(Cons.ADD_LOGIN_MESSAGE);
			messageService.addMessage(messageObj);
			
			return "redirect:user.html";
		}
	}
	
	/**
	 * 评教
	 * @param request
	 * @param response
	 * @return
	 * @throws WeiboException
	 * @throws JSONException
	 * 2013-6-22 上午6:59:43 2013
	 */
	@RequestMapping(value = "/dopingjiao")
	public @ResponseBody boolean dopingjiao(HttpServletRequest request, HttpServletResponse response) throws WeiboException, JSONException {
		String savePath = FileUploadUtil.genetateUserimgPath(request);
		String userName = request.getParameter("name");
		String userPasw = request.getParameter("pasw");
		UserThird userThird =  userService.getUserThirdByThirdIdAndLoginType(userName, Cons.VPN_LOGIN);
		if(userThird != null){       //如果第三方登录表中有记录,表明已经登录过,直接在user表中取信息
			String userId = userThird.getUserId()+"";
			User user = userService.getUserByUserId(userId);
			setSessionUser(request, user);      //添加到session
			
			AutoLogin autoLogin = new AutoLogin();
			autoLogin.setSessionId(request.getSession().getId());
			autoLogin.setUserId(userId);
			userService.addAutoLogin(autoLogin, response);     //添加到自动登录的关联
			
			return false;
		}else{
			User user = new User();
			try {
				user = vpnService.getUserInfo(userName, userPasw, savePath);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			user.setUserPwd(userPasw);
			int userId = userService.addUser(user);    //添加一个新用户 
			userThird = new UserThird();
			userThird.setAccessToken("");
			userThird.setInvalid("");
			userThird.setLoginType(Cons.VPN_LOGIN);
			userThird.setThirdUserId(userName);
			userThird.setUserId(userId);
			userService.addThirdUser(userThird);       //保存第三方的关联
			
			return true;
		}
	}
	
	@RequestMapping(value = "/pingjiao")
	public ModelAndView pingjiao(HttpServletRequest request, HttpServletResponse response) throws WeiboException, JSONException {
		return new ModelAndView("pingjiao");
	}
	
	@RequestMapping(value = "/renpageLogin", method = RequestMethod.GET)
	public String renpageLogin(HttpServletRequest request, HttpServletResponse response) throws WeiboException, JSONException {
        System.out.println(request.getParameter("access_token"));
		return request.getParameter("access_token");
	}
}
