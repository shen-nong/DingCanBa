package com.dingcan.user.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AutoLoginRowMapper implements RowMapper{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		AutoLogin autoLogin = new AutoLogin();
		autoLogin.setId(rs.getInt("id"));
		autoLogin.setSessionId(rs.getString("sessionId"));
		autoLogin.setUserId(rs.getString("userId"));
		return autoLogin;
	}
}
