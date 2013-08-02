package com.dingcan.user.model;

/**
 * 我收藏的商家
 * @author Administrator
 * 2013-4-29 下午10:52:30 2013
 *
 */
public class Collection {

	private int id;
	private int userId;
	private int shopId;
	private String Date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
}
