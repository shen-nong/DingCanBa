package com.dingcan.shop.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dingcan.goods.model.Goods;

public class RecommendRowMapper extends Goods implements RowMapper{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Recommend recommend = new Recommend();
		recommend.setGoodsId(rs.getInt("goodsId"));
		recommend.setId(rs.getInt("id"));
		recommend.setShopId(rs.getInt("shopId"));
		return recommend;
	}
}
