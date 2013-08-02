package com.dingcan.shop.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 商家实体类
 * @author LiHJam
 *
 */
public class Shop implements Serializable{
	int ShopId;
	int UserId;
	String ShopName;           //商家名
	String Feixin; 
	String ShopDescription;
	String ShopAccount;        //商家掌柜的账号
	String ShopPassword;
	String SignboardUrl;       //商家招招牌的url
	String PasswordAsk;
	String PasswordAnswer;
	String CreateDate;
	int IsAuth;           //是否是认证商家  0:非认证,1,认证
	
	public Shop(){}

	public Shop(int shopId, String shopName, String feixin,
			String shopDescription, String shopAccount, String shopPassword,
			String signboardUrl, String passwordAsk, String passwordAnswer, String createDate,
			int isAuth) {
		this.ShopId = shopId;
		this.ShopName = shopName;
		this.Feixin = feixin;
		this.ShopDescription = shopDescription;
		this.ShopAccount = shopAccount;
		this.ShopPassword = shopPassword;
		this.SignboardUrl = signboardUrl;
		this.PasswordAsk = passwordAsk;
		this.PasswordAnswer = passwordAnswer;
		this.CreateDate = createDate;
		this.IsAuth = isAuth;
	}

	public int getShopId() {
		return ShopId;
	}

	public void setShopId(int shopId) {
		ShopId = shopId;
	}

	public String getShopName() {
		return ShopName;
	}

	public void setShopName(String shopName) {
		ShopName = shopName;
	}
	
	public String getFeixin() {
		return Feixin;
	}

	public void setFeixin(String feixin) {
		Feixin = feixin;
	}

	public String getShopDescription() {
		return ShopDescription;
	}

	public void setShopDescription(String shopDescription) {
		ShopDescription = shopDescription;
	}

	public String getShopAccount() {
		return ShopAccount;
	}

	public void setShopAccount(String shopAccount) {
		ShopAccount = shopAccount;
	}

	public String getShopPassword() {
		return ShopPassword;
	}

	public void setShopPassword(String shopPassword) {
		ShopPassword = shopPassword;
	}

	public String getSignboardUrl() {
		return SignboardUrl;
	}

	public void setSignboardUrl(String signboardUrl) {
		SignboardUrl = signboardUrl;
	}

	public String getPasswordAsk() {
		return PasswordAsk;
	}

	public void setPasswordAsk(String passwordAsk) {
		PasswordAsk = passwordAsk;
	}

	public String getPasswordAnswer() {
		return PasswordAnswer;
	}

	public void setPasswordAnswer(String passwordAnswer) {
		PasswordAnswer = passwordAnswer;
	}

	public String getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}

	public int getIsAuth() {
		return IsAuth;
	}

	public void setIsAuth(int isAuth) {
		IsAuth = isAuth;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

}
