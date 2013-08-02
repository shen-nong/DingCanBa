package com.dingcan.goods.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class SalesVoRowMapper extends SalesRowMapper implements RowMapper{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		SalesVo saleVo = new SalesVo();
		
		saleVo.setBuyerId(rs.getInt("buyerId"));
		saleVo.setDate(rs.getString("date"));
		saleVo.setGoodsId(rs.getInt("goodsId"));
		saleVo.setId(rs.getInt("id"));
		saleVo.setQuantity(rs.getInt("quantity"));
		saleVo.setShopId(rs.getInt("shopId"));
		
		saleVo.setPrice(rs.getInt("PriceDiscount"));
		saleVo.setGoodsName(rs.getString("goodsName"));
		saleVo.setShopName(rs.getString("shopName"));
		saleVo.setUserName(rs.getString("userName"));
		return saleVo;
	}
}
