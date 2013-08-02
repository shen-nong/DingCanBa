package com.dingcan.user.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setBirthday(rs.getString("birthday"));
		user.setLocation(rs.getString("location"));
		user.setPhone(rs.getString("phone"));
		user.setPhoto(rs.getString("photo"));
		user.setRegisterTime(rs.getString("registerTime"));
		user.setUserId(rs.getInt("userId"));
		user.setUserMail(rs.getString("userMail"));
		user.setUserName(rs.getString("userName"));
		user.setUserPwd(rs.getString("userPwd"));
		user.setUserSex(rs.getString("userSex"));
		user.setIntro(rs.getString("intro"));
		return user;
	}
}
