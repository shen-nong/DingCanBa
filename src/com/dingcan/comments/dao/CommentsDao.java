package com.dingcan.comments.dao;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.dingcan.comments.model.Comment;
import com.dingcan.comments.model.CommentRowMapper;
import com.dingcan.comments.model.CommentVo;
import com.dingcan.comments.model.CommentVoRowMapper;

public class CommentsDao extends JdbcDaoSupport{
	public int addComment(Comment comment){
		String sql = "INSERT INTO `t_comments` (`ReplyId`, `GoodsId`, `Content`, `CommenterId`, "+ 
				"`CreateDate`) VALUES "+
				"(?, ?, ?, ?, ?);";
		Object[] param = new Object[] {comment.getReplyId(),comment.getGoodsId(),
				comment.getContent(),comment.getCommenterId(),comment.getCreateDate()};
		return getJdbcTemplate().update(sql, param);
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> getCommentListByGoodsId(int goodsId){
		String sql = "SELECT * FROM t_comments WHERE GoodsId = ? order by CommentsId desc";
		List<Comment> listComments ;
		
		try{
			listComments = getJdbcTemplate().query(
					sql, new Object[] { goodsId }, 
					new CommentRowMapper());
		}catch(EmptyResultDataAccessException e){
			listComments = null;
		}
		return listComments;
	}
	
	@SuppressWarnings("unchecked")
	public List<CommentVo> getCommentVoListByGoodsId(int goodsId){
		String sql = "select t_comments.* ,t_user_info.UserName,t_user_info.Photo " +
				"from t_comments inner join t_user_info " +
				"on t_comments.CommenterId=t_user_info.Userid " +
				"where t_comments.GoodsId=? " +
				"order by CommentsId desc ;";
		List<CommentVo> listComments ;
		
		try{
			listComments = getJdbcTemplate().query(
					sql, new Object[] { goodsId }, 
					new CommentVoRowMapper());
		}catch(EmptyResultDataAccessException e){
			listComments = null;
		}
		return listComments;
	}
}
