package com.dingcan.goods.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GoodsVoRowMapper  implements RowMapper{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		GoodsVo goodsVo = new GoodsVo();
		goodsVo.setGoodsDescription(rs.getString("goodsDescription"));
		goodsVo.setGoodsId(rs.getInt("goodsId"));
		goodsVo.setGoodsName(rs.getString("goodsName"));
		goodsVo.setGoodsUrl(rs.getString("goodsUrl"));
		goodsVo.setCount(rs.getInt("count"));
		goodsVo.setPriceDiscount(rs.getDouble("priceDiscount"));
		goodsVo.setPriceOrganal(rs.getDouble("priceOrganal"));
		goodsVo.setShopId(rs.getInt("shopId"));
		
		goodsVo.setShopName(rs.getString("shopName"));
		return goodsVo;
	}
}
