package com.dingcan.cons;

/**
 * 常用的常量
 * @author Administrator
 * 2013-4-29 下午3:48:09 2013
 *
 */
public class Cons {
	public static final String USER_CONTEXT = "USER_CONTEXT";   //用户在Session中的name
	public static final String SHOP_CONTEXT = "SHOP_CONTEXT";   //商家在Session中的name
	
	//第三方登录
	public static final int RENREN_LOGIN = 2;
	public static final int SINA_LOGIN = 1;
	public static final int QQ_LOGIN = 3;
	public static final int VPN_LOGIN = 4;
	
	//短信或者站内信
	public static final int ADD_MESSAGE_MESSAGE = 1; //发送了一条短信
	public static final int ADD_COMMENT_MESSAGE = 2; //添加了一条评论
	public static final int ADD_GOODS_MESSAGE = 3;   //添加了一件商品
	public static final int ADD_SHARE_MESSAGE = 4;   //添加了一条分享到第三方
	public static final int ADD_LOGIN_MESSAGE = 5;   //第三方登录
	
	//如果买家未登录,则在cookie中保存的值
	public static final String BUYER_PHONE = "BUYER_PHONE";   //买家的电话
	public static final String BUYER_LOCATION = "BUYER_LOCATION";   //买家的地址
	
	//自动登录
	public static final String AUTOLOGIN_USERID = "AUTOLOGIN_USERID";   //自动登录保存用户
	public static final String SESSIONID = "SESSIONID";   
}
