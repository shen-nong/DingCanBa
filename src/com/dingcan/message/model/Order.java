package com.dingcan.message.model;

/**
 * 订购人的信息
 * @author Green Lei
 * 2012-12-8 下午4:01:52 2012
 */
public class Order {
	private String userName;
	private int userId;
	private int goodsId;
	private String userPhoto;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
}
