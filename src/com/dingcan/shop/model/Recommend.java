package com.dingcan.shop.model;

import com.dingcan.goods.model.Goods;

/**
 * 商家推荐商品
 * @author Administrator
 *
 */
public class Recommend extends Goods{
	int id;
	int shopId;
	int goodsId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
}
