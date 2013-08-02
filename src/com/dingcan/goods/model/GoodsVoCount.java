package com.dingcan.goods.model;

public class GoodsVoCount {
	private Goods goods;
	private int like; //被喜欢次数
	private int sale;  //被售出多少件
	
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
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
}
