package com.dingcan.user.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserThirdRowMapper implements RowMapper{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserThird userThird = new UserThird();
		userThird.setAccessToken(rs.getString("accessToken"));
		userThird.setInvalid(rs.getString("invalid"));
		userThird.setLoginType(rs.getInt("loginType"));
		userThird.setThirdUserId(rs.getString("thirdUserId"));
		userThird.setUserId(rs.getInt("userId"));
		return userThird;
	}
}
