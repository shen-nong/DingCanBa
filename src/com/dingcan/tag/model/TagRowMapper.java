package com.dingcan.tag.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TagRowMapper implements RowMapper{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Tag tag = new Tag();
		tag.setTagId(rs.getInt("tagId"));
		tag.setTagName(rs.getString("tagName"));
		tag.setTagType(rs.getInt("tagType"));
		tag.setCreateDate(rs.getString("createDate"));
		return tag;
	}
}
