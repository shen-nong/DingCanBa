package com.dingcan.shop.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ShopRowMapper implements RowMapper{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Shop shop = new Shop();
		shop.setFeixin(rs.getString("feixin"));
		shop.setPasswordAnswer(rs.getString("passwordAnswer"));
		shop.setPasswordAsk(rs.getString("passwordAsk"));
		shop.setShopAccount(rs.getString("shopAccount"));
		shop.setShopDescription(rs.getString("shopDescription"));
		shop.setShopId(rs.getInt("shopId"));
		shop.setShopName(rs.getString("shopName"));
		shop.setShopPassword(rs.getString("shopPassword"));
		shop.setSignboardUrl(rs.getString("signboardUrl"));
		shop.setCreateDate(rs.getString("createDate"));
		shop.setIsAuth(rs.getInt("isAuth"));
		shop.setUserId(rs.getInt("userId"));
		return shop;
	}
}
