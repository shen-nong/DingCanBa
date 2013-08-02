package com.dingcan.base;

import javax.servlet.http.HttpServletRequest;

import com.dingcan.cons.Cons;
import com.dingcan.shop.model.Shop;
import com.dingcan.user.model.User;

public class BaseController {
	
	protected Shop getSessionShop(HttpServletRequest request){
		return (Shop)request.getSession().getAttribute(Cons.SHOP_CONTEXT);
	}
	
	protected void setSessionShop(HttpServletRequest request,Shop shop){
		if(shop!=null){
			request.getSession().setAttribute(Cons.SHOP_CONTEXT, shop);
		}
	}
	
	protected User getSessionUser(HttpServletRequest request){
		return (User)request.getSession().getAttribute(Cons.USER_CONTEXT);
	}
	
	protected void setSessionUser(HttpServletRequest request,User user){
		if(user!=null){
			request.getSession().setAttribute(Cons.USER_CONTEXT, user);
		}
	}
}
