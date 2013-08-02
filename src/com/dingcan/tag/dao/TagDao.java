package com.dingcan.tag.dao;



import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.dingcan.tag.model.Tag;
import com.dingcan.tag.model.TagRowMapper;

public class TagDao extends JdbcDaoSupport{
	public int addTag(Tag tag){
		String sql = "INSERT INTO `t_tag` (`TagType`, `TagName`,`CreateDate`) VALUES "+
				"(?, ?);";
		Object[] param = new Object[] {tag.getTagType(),tag.getTagName(),tag.getCreateDate()};
		return getJdbcTemplate().update(sql, param);
	}
	
	/**
	 * 根据一个关键字,通过模糊查询,看是已经存在这样的关键字,
	 * 如果存在返回list,如果没有返回null
	 * @param key
	 * @return
	 * Green Lei
	 * 2012-12-5 下午5:58:13 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Tag> getTagByKey(String key){
		String sql = "select * from t_tag where TagName like '"+key+"%' ";
		return getJdbcTemplate().query(sql, new TagRowMapper());
	}
	
	/**
	 * 倒序查询tag,限制数量
	 * @param count
	 * @return
	 * Green Lei
	 * 2012-12-5 下午6:17:45 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Tag> getTagByCount(int count){
		String sql = "select * from t_tag order by TagId desc limit 0,"+count;
		return getJdbcTemplate().query(sql, new TagRowMapper());
	}
}
