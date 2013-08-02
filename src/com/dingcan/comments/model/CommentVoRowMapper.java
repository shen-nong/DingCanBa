package com.dingcan.comments.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CommentVoRowMapper implements RowMapper{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		CommentVo comment = new CommentVo();
		comment.setCommenterId(rs.getInt("commenterId"));
		comment.setCommentsId(rs.getInt("commentsId"));
		comment.setContent(rs.getString("content"));
		comment.setCreateDate(rs.getString("createDate"));
		comment.setGoodsId(rs.getInt("createDate"));
		comment.setReplyId(rs.getInt("replyId"));
		
		comment.setUserName(rs.getString("userName"));
		comment.setUserPhoto(rs.getString("photo"));
		return comment;
	}
}
