package com.dingcan.user.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class CollectionRowMapper implements RowMapper{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Collection collection = new Collection();
		collection.setDate(rs.getString("date"));
		collection.setId(rs.getInt("id"));
		collection.setShopId(rs.getInt("shopId"));
		collection.setUserId(rs.getInt("userId"));
		return collection;
	}
}
