package com.dingcan.user.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class FriendsRowMapper implements RowMapper{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Friends friends = new Friends();
		friends.setFriendId(rs.getInt("friendId"));
		friends.setMyId(rs.getInt("myId"));
		friends.setState(rs.getInt("state"));
		friends.setId(rs.getInt("id"));
		return friends;
	}
}
