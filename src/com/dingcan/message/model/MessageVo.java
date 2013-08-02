package com.dingcan.message.model;

import com.dingcan.goods.model.Goods;
import com.dingcan.user.model.User;

/**
 * 首页新鲜事
 * @author Administrator
 * 2013-6-6 下午1:11:35 2013
 *
 */
public class MessageVo {
	private Goods goods;
	private User user;
	private int like;  //被喜欢次数
	private int sale;   //被出售次数
	private String date;
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getSale() {
		return sale;
	}
	public void setSale(int sale) {
		this.sale = sale;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
