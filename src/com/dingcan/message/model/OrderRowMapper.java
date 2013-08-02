package com.dingcan.message.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OrderRowMapper implements RowMapper{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order order = new Order();
		order.setGoodsId(rs.getInt("goodsId"));
		order.setUserId(rs.getInt("userId"));
		order.setUserName(rs.getString("userName"));
		order.setUserPhoto(rs.getString("photo"));
		return order;
	}
}
