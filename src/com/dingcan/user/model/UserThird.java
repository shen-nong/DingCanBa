package com.dingcan.user.model;

public class UserThird {

	private int UserId;
	private String ThirdUserId;
	private int LoginType;      //1:新浪登录,2,人人登录,3,qq登录
	private String AccessToken;  
	private String invalid;     //失效日期
	
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getThirdUserId() {
		return ThirdUserId;
	}
	public void setThirdUserId(String thirdUserId) {
		ThirdUserId = thirdUserId;
	}
	public int getLoginType() {
		return LoginType;
	}
	public void setLoginType(int loginType) {
		LoginType = loginType;
	}
	public String getAccessToken() {
		return AccessToken;
	}
	public void setAccessToken(String accessToken) {
		AccessToken = accessToken;
	}
	public String getInvalid() {
		return invalid;
	}
	public void setInvalid(String invalid) {
		this.invalid = invalid;
	}
}
