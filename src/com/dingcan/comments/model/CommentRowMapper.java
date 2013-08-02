package com.dingcan.comments.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CommentRowMapper implements RowMapper{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Comment comment = new Comment();
		comment.setCommenterId(rs.getInt("commenterId"));
		comment.setCommentsId(rs.getInt("commentsId"));
		comment.setContent(rs.getString("content"));
		comment.setCreateDate(rs.getString("createDate"));
		comment.setGoodsId(rs.getInt("createDate"));
		comment.setReplyId(rs.getInt("replyId"));
		return comment;
	}
}
