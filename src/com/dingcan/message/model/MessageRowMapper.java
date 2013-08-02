package com.dingcan.message.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MessageRowMapper implements RowMapper{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Message message = new Message();
		message.setContentText(rs.getString("contentText"));
		message.setFromId(rs.getInt("fromId"));
		message.setMessagesId(rs.getInt("messagesId"));
		message.setToId(rs.getInt("toId"));
		message.setType(rs.getInt("type"));
		message.setCreateDate(rs.getString("createDate"));
		message.setPictureUrl(rs.getString("pictureUrl"));
		message.setCommentNum(rs.getInt("commentNum"));
		message.setShareNum(rs.getInt("shareNum"));
		return message;
	}
}
