package com.dingcan.goods.model;

/**
 * Goods子类,用于在首页显示
 * @author Green Lei
 * 2012-12-6 下午1:41:48 2012
 */
@SuppressWarnings("serial")
public class GoodsVo extends Goods {

	private String shopName;
	private int orderCount;      //订购数
	private int commentsCount;
	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	public int getCommentsCount() {
		return commentsCount;
	}
	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}
}
