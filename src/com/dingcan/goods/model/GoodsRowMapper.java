package com.dingcan.goods.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GoodsRowMapper implements RowMapper{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Goods goods = new Goods();
		goods.setGoodsDescription(rs.getString("goodsDescription"));
		goods.setGoodsId(rs.getInt("goodsId"));
		goods.setGoodsName(rs.getString("goodsName"));
		goods.setGoodsUrl(rs.getString("goodsUrl"));
		goods.setCount(rs.getInt("count"));
		goods.setPriceDiscount(rs.getDouble("priceDiscount"));
		goods.setPriceOrganal(rs.getDouble("priceOrganal"));
		goods.setShopId(rs.getInt("shopId"));
		goods.setCate(rs.getInt("cate"));
		return goods;
	}
}
