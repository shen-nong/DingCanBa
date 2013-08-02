package com.dingcan.goods.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class SalesRowMapper  implements RowMapper{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Sales sale = new Sales();
		sale.setBuyerId(rs.getInt("buyerId"));
		sale.setDate(rs.getString("date"));
		sale.setGoodsId(rs.getInt("goodsId"));
		sale.setId(rs.getInt("id"));
		sale.setQuantity(rs.getInt("quantity"));
		sale.setShopId(rs.getInt("shopId"));
		return sale;
	}
}
